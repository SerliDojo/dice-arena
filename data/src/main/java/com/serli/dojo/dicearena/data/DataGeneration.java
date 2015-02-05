package com.serli.dojo.dicearena.data;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

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

		LongStream.range(1L, FILES_NUMBER * LINES_NUMBER).forEach(id -> {
			LocalDateTime startTime = pickDateTime();
			Game game = pickIn(games);
			List<Player> gamePlayers = players.stream().filter(player -> player.game.equals(game)).collect(Collectors.toList());

			Match match = new Match(id, game, startTime, pickDateTimeFrom(startTime, 6));
			match.scores = pickScoresIn(gamePlayers, match);

			appendIn(String.format("data.json", id / LINES_NUMBER), match.toJsonString());

			System.out.print(".");
			if (id % LINES_NUMBER == 0) {
				System.out.println();
			}
		});

		games.stream().map(Entity::toJsonString).forEach(entity -> appendIn("data.json", entity));
		accounts.stream().map(Entity::toJsonString).forEach(entity -> appendIn("data.json", entity));
		players.stream().map(Entity::toJsonString).forEach(entity -> appendIn("data.json", entity));
	}

	private static void appendIn(String fileName, String object) {
		File file = new File(fileName);
		try {
			Files.append(object, file, Charsets.UTF_8);
			Files.append("\n", file, Charsets.UTF_8);
		} catch (IOException e) {
			throw new RuntimeException(e);
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
			return new Account(name, String.format("%s@%s", name, pickIn(domains)), pickIn(locations), pickDateTime().toLocalDate());
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
		return new Player(String.format("%s-%s", qualifier, account.name), account, pickIn(games));
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
