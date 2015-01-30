package com.serli.dojo.dicearena.data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

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
			match.scores = pickScores(accounts, match);

			try {
				appendInAs(String.format("data-%03d.json", id / LINES_NUMBER), match.toString(), TYPE_MATCH);
			} catch (Exception e) {
				e.printStackTrace();
			}

			System.out.print(".");
			if (id % LINES_NUMBER == 0) {
				System.out.println();
			}
		});;

		appendInAs("data-games.json", games.stream().map(Game::toString).collect(Collectors.toList()), TYPE_GAME);
		appendInAs("data-accounts.json", accounts.stream().map(Account::toString).collect(Collectors.toList()), TYPE_ACCOUNT);
	}

	private static Map<String, Integer> pickScores(List<Account> accounts, Match match) {
		Random random = new Random(); 

		int minPlayers = match.game.minPlayers != null ? match.game.minPlayers : 1;
		int maxPlayers = match.game.maxPlayers != null ? match.game.maxPlayers : accounts.size();
		int playersCount = Math.min(random.nextInt(maxPlayers) + minPlayers, accounts.size());

		Collections.shuffle(accounts);
		Set<Account> players = accounts.stream().limit(playersCount).collect(Collectors.toSet());
		
		return players.stream().collect(
				Collectors.toMap(player -> formatName(player, match.game), player -> random.nextInt(MAX_SCORE)));
	}

	private static String formatName(Account account, Game game) {
		return account.name + "-"
				+ game.name.toLowerCase().replaceAll("\\s", "");
	}

	private static void appendInAs(String fileName, Collection<String> objects,
			String type) throws IOException {
		for (String object : objects) {
			appendInAs(fileName, object, type);
		}
	}

	private static void appendInAs(String fileName, String object, String type)
			throws IOException {
		appendIn(fileName, String.format(
				"{ \"index\" : { \"_index\" : \"%s\", \"_type\" : \"%s\" } }",
				INDEX, type));
		appendIn(fileName, object);
	}

	private static void appendIn(String fileName, String object)
			throws IOException {
		File file = new File(fileName);
		Files.append(object, file, Charsets.UTF_8);
		Files.append("\n", file, Charsets.UTF_8);
	}

	private static List<String> readIn(String name) throws IOException {
		return Files.readLines(
				new File("./src/main/resources/" + name + ".txt"),
				Charsets.UTF_8);
	}

	private static List<Game> readGamesIn(String gamesFile) throws IOException {

		List<String> gameLines = readIn(gamesFile);

		List<Game> games = new ArrayList<Game>(gameLines.size());
		for (String line : gameLines) {
			List<String> strings = Lists.newArrayList(Splitter.on('|').split(
					line));

			Game game = new Game(strings.get(0), Ints.tryParse(strings.get(1)),
					Ints.tryParse(strings.get(2)), strings.get(3));
			games.add(game);
		}

		return games;
	}

	private static List<Account> readAccountsIn(String namesFile,
			String domainsFile, String locationsFile) throws IOException {

		List<String> names = readIn(namesFile);
		List<String> domains = readIn(domainsFile);
		List<String> locations = readIn(locationsFile);

		List<Account> accounts = new ArrayList<Account>();
		for (String name : names) {
			String email = String.format("%s@%s", name, pickIn(domains));
			accounts.add(new Account(name, email, pickIn(locations), pickDateTime().toLocalDate()));
		}
		return accounts;
	}

	private static <T> T pickIn(List<T> objects) {
		return objects.get(new Random().nextInt(objects.size()));
	}

	private static LocalDateTime pickDateTime() {
		return pickDateTime(new LocalDateTime(2014, 1, 1, 0, 0), 330 * 24 * 60);
	}

	private static LocalDateTime pickDateTime(LocalDateTime from,
			int minutesRange) {
		return from.plusMinutes(new Random().nextInt(minutesRange));
	}

	public static String getScoreString(String name, Integer score) {
		return String.format("{ \"name\" : \"%s\", \"score\" : %d }", name, score);
	}

	public static String getPlayerString(Map.Entry<String, Game> player) {
		return String.format("{ \"name\" : \"%s\", \"game\" : [%s] }", player.getKey(), player.getValue().name);
	}

	public static class Match {
		Long id;
		Game game;
		LocalDateTime startTime, endTime;
		Map<String, Integer> scores = new HashMap<>();
		
		public Match(Long id, Game game, LocalDateTime startTime,
				LocalDateTime endTime) {
			super();
			this.id = id;
			this.game = game;
			this.startTime = startTime;
			this.endTime = endTime;
		}

		@Override
		public String toString() {
			Optional<Entry<String, Integer>> winner = scores.entrySet().stream().collect(Collectors.maxBy(new Comparator<Map.Entry<String, Integer>>() {
				@Override
				public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
					return o1.getValue().compareTo(o2.getValue());
				}
			}));

			String winnerString = "";
			if(winner.isPresent()) {
				winnerString = winner.get().getKey();
			}

			String scoresString = scores.entrySet().stream().map(entry -> String.format("{ \"name\" : \"%s\", \"score\" : %d }",
							entry.getKey(), entry.getValue())).collect(Collectors.joining(", "));

			return String.format("{ \"id\" : \"%d\", \"game\" : %s, \"startTime\" : \"%s\", \"endTime\" : \"%s\", \"scores\" : [ %s ], \"winner\" : \"%s\" }",
							id, game.name, startTime, endTime, scoresString, winnerString);
		}
	}
	
	public static class Account {
		String name, email, locations;
		LocalDate subscription;
		Map<String, Game> players = new HashMap<>();

		public Account(String name, String email, String locations,
				LocalDate subscription) {
			super();
			this.name = name;
			this.email = email;
			this.locations = locations;
			this.subscription = subscription;
		}

		@Override
		public String toString() {
			String playersString = players.entrySet().stream().map(DataGeneration::getPlayerString).collect(Collectors.joining(", "));
			return String.format("{ \"email\" : \"%s\", \"location\" : [%s], \"subscription\" : \"%s\", \"players\" : [ %s ] }",
							email, locations, subscription, playersString);
		}
	}

	public static class Game {
		String name, description;
		Integer minPlayers, maxPlayers;

		public Game(String name, Integer minPlayers, Integer maxPlayers,
				String description) {
			super();
			this.name = name;
			this.minPlayers = minPlayers;
			this.maxPlayers = maxPlayers;
			this.description = description;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("{ \"name\" : \"").append(name).append("\"");
			if (minPlayers != null) {
				builder.append(", \"minPlayers\" : ").append(minPlayers);
			}
			if (maxPlayers != null) {
				builder.append(", \"maxPlayers\" : ").append(maxPlayers);
			}
			if (description != null && !description.isEmpty()) {
				builder.append(", \"description\" : \"").append(description)
						.append("\"");
			}
			builder.append(" }");
			return builder.toString();
		}
	}
}
