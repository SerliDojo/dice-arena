package com.serli.dojo.dicearena.data;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import com.google.common.base.Charsets;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.io.Files;
import com.google.common.primitives.Ints;

public class DataGeneration {

	private static final int MAX_SCORE = 100;
	private static final int FILES_NUMBER = 1;
	private static final int LINES_NUMBER = 100;

	public static void main(String[] args) throws IOException {
		List<Game> games = readGamesIn("games");
		List<Account> accounts = readAccountsIn("names", "domains", "locations");
		List<Player> players = readPlayersIn("qualifiers", accounts, games);

		List<Match> matches = LongStream.range(1L, FILES_NUMBER * LINES_NUMBER).mapToObj(id -> {
			LocalDateTime startTime = pickDateTime();
			Game game = pickIn(games);
			List<Player> gamePlayers = players.stream().filter(player -> player.game.equals(game)).collect(Collectors.toList());

			Match match = new Match(id, game, startTime, pickDateTimeFrom(startTime, 6));
			match.scores = pickScoresIn(gamePlayers, match);
			return match;
		}).collect(Collectors.toList());

		try (TransportClient transportClient = new TransportClient()) {
			Client client = transportClient.addTransportAddress(new InetSocketTransportAddress("localhost", 9300));

			client.admin().indices().delete(new DeleteIndexRequest(Entity.INDEX));
			client.admin().indices().create(new CreateIndexRequest(Entity.INDEX));
			client.admin().indices().putMapping(new PutMappingRequest(Entity.INDEX).source(Game.MAPPING));
			client.admin().indices().putMapping(new PutMappingRequest(Entity.INDEX).source(Account.MAPPING));
			client.admin().indices().putMapping(new PutMappingRequest(Entity.INDEX).source(Player.MAPPING));
			client.admin().indices().putMapping(new PutMappingRequest(Entity.INDEX).source(Match.MAPPING));

			BulkRequestBuilder bulkRequest = client.prepareBulk();
			Function<Entity, IndexRequestBuilder> index = entity -> client.prepareIndex(Entity.INDEX, entity.getType(), entity.getId()).setSource(entity.toJsonString());

			games.stream().map(index).forEach(bulkRequest::add);
			accounts.stream().map(index).forEach(bulkRequest::add);
			players.stream().map(index).forEach(bulkRequest::add);
			matches.stream().map(index).forEach(bulkRequest::add);

			BulkResponse bulkResponse = bulkRequest.execute().actionGet();
			if (bulkResponse.hasFailures()) {
				System.out.println("Failures during index");
			}
		}
	}

	private static List<Game> readGamesIn(String gamesFile) throws IOException {
		List<String> gameLines = readIn(gamesFile);

		return gameLines.stream().map(line -> {
			List<String> strings = Lists.newArrayList(Splitter.on('|').split(line));
			return new Game(strings.get(0), Ints.tryParse(strings.get(1)), Ints.tryParse(strings.get(2)), strings.get(3));
		}).collect(Collectors.toList());
	}

	private static List<Account> readAccountsIn(String namesFile, String domainsFile, String locationsFile) throws IOException {
		List<String> names = readIn(namesFile);
		List<String> domains = readIn(domainsFile);
		List<String> locations = readIn(locationsFile);

		return names.stream().map(name -> {
			return new Account(String.format("%s@%s", name, pickIn(domains)), pickIn(locations), pickDateTime().toLocalDate());
		}).collect(Collectors.toList());
	}

	private static List<Player> readPlayersIn(String qualifiersFile, List<Account> accounts, List<Game> games) throws IOException {
		List<String> qualifiers = readIn(qualifiersFile);
		Random random = new Random();

		return accounts.stream().map(account -> {
			Collections.shuffle(qualifiers);
			int playersCount = random.nextInt(Math.min(qualifiers.size(), 6));
			return qualifiers.stream().limit(playersCount).map(qualifier ->
					pickPlayer(qualifier, account, games)).collect(Collectors.toList());
		}).flatMap(list -> list.stream()).collect(Collectors.toList());
	}

	private static Player pickPlayer(String qualifier, Account account, List<Game> games) {
		String name = String.format("%s %s", qualifier.substring(0, 1).toUpperCase() + qualifier.substring(1), account.email.substring(0, 1).toUpperCase() + account.email.substring(1));
		if (name.contains("@")) {
			name = name.substring(0, name.indexOf("@"));
		}
		return new Player(name, account, pickIn(games));
	}

	private static List<String> readIn(String name) throws IOException {
		return Files.readLines(new File("./src/main/resources/data/" + name + ".txt"), Charsets.UTF_8);
	}

	private static Map<String, Integer> pickScoresIn(List<Player> players, Match match) {
		Random random = new Random();

		int minPlayers = match.game.minPlayers != null ? match.game.minPlayers : 1;
		int maxPlayers = match.game.maxPlayers != null ? match.game.maxPlayers : players.size();
		maxPlayers = Math.min(maxPlayers, 10);
		int playersCount = random.nextInt(maxPlayers - minPlayers + 1) + minPlayers;

		Collections.shuffle(players);
		Set<Player> selectedPlayers = players.stream().limit(playersCount).collect(Collectors.toSet());

		return selectedPlayers.stream().collect(Collectors.toMap(player -> player.name, player -> random.nextInt(MAX_SCORE)));
	}

	private static <T> T pickIn(List<T> objects) {
		return objects.get(new Random().nextInt(objects.size()));
	}

	private static LocalDateTime pickDateTime() {
		return pickDateTimeFrom(LocalDateTime.of(2014, 1, 1, 0, 0), 365 * 24 * 60);
	}

	private static LocalDateTime pickDateTimeFrom(LocalDateTime from, int minutesRange) {
		return from.plusMinutes(new Random().nextInt(minutesRange));
	}
}
