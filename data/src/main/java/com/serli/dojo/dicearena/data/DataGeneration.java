package com.serli.dojo.dicearena.data;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collection;
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
	private static final int FILES_NUMBER = 5;
	private static final int LINES_NUMBER = 1000;
	private static final String INDEX = "engine";
	private static final String TYPE_GAME = "game";
	private static final String TYPE_ACCOUNT = "account";
	private static final String TYPE_MATCH = "match";

	public static void main(String[] args) throws IOException {
		List<Game> games = readGamesIn("games");
		List<Account> accounts = readAccountsIn("names", "domains", "locations");

		LongStream.range(0L, FILES_NUMBER * LINES_NUMBER).forEach(id -> {
			LocalDateTime startTime = pickDateTime();
			Match match = new Match(id, pickIn(games), startTime, pickDateTime(startTime, 6));
			match.scores = pickScoresIn(accounts, match);

			appendInAs(String.format("data-%03d.json", id / LINES_NUMBER), match.toJsonString(), TYPE_MATCH);

			System.out.print(".");
			if (id % LINES_NUMBER == 0) {
				System.out.println();
			}
		});

		appendInAs("data-games.json", games.stream().map(Game::toJsonString).collect(Collectors.toList()), TYPE_GAME);
		appendInAs("data-accounts.json", accounts.stream().map(Account::toJsonString).collect(Collectors.toList()), TYPE_ACCOUNT);
	}

	private static String formatName(Account account, Game game) {
		return account.name + "-" + game.name.toLowerCase().replaceAll("\\s", "");
	}

	private static void appendInAs(String fileName, Collection<String> objects, String type) throws IOException {
		objects.stream().forEach(object -> appendInAs(fileName, object, type));
	}

	private static void appendInAs(String fileName, String object, String type) {
		appendIn(fileName, String.format("{ \"index\" : { \"_index\" : \"%s\", \"_type\" : \"%s\" } }", INDEX, type));
		appendIn(fileName, object);
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

	private static List<String> readIn(String name) throws IOException {
		return Files.readLines(new File("./src/main/resources/" + name + ".txt"), Charsets.UTF_8);
	}

	private static Map<String, Integer> pickScoresIn(List<Account> accounts, Match match) {
		Random random = new Random();

		int minPlayers = match.game.minPlayers != null ? match.game.minPlayers : 1;
		int maxPlayers = match.game.maxPlayers != null ? match.game.maxPlayers : accounts.size();
		int playersCount = Math.min(random.nextInt(maxPlayers) + minPlayers, accounts.size());

		Collections.shuffle(accounts);
		Set<Account> players = accounts.stream().limit(playersCount).collect(Collectors.toSet());

		return players.stream().collect(Collectors.toMap(player -> formatName(player, match.game), player -> random.nextInt(MAX_SCORE)));
	}

	private static <T> T pickIn(List<T> objects) {
		return objects.get(new Random().nextInt(objects.size()));
	}

	private static LocalDateTime pickDateTime() {
		return pickDateTime(LocalDateTime.of(2014, 1, 1, 0, 0), 365 * 24 * 60);
	}

	private static LocalDateTime pickDateTime(LocalDateTime from, int minutesRange) {
		return from.plusMinutes(new Random().nextInt(minutesRange));
	}
}
