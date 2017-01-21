package com.serli.dojo.arena.dice.yahtzee;

import com.serli.dojo.arena.dice.Game;
import java.util.*;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class YahtzeeGame implements Game<YahtzeeAction, YahtzeeMatch> {

	public static final Logger LOGGER = LoggerFactory.getLogger(YahtzeeGame.class);

	private final String name;

	public YahtzeeGame(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public YahtzeeMatch init(List<String> players) {
		LinkedHashMap<String, Map<YahtzeeLine, Integer>> scoreTable = new LinkedHashMap<>();
		players.forEach(player -> scoreTable.put(player, Collections.emptyMap()));
		YahtzeeTurn turn = YahtzeeTurn.playing(getNextPlayer(null, players));
		return new YahtzeeMatch(scoreTable, turn);
	}

	@Override
	public YahtzeeMatch apply(final YahtzeeAction action, final YahtzeeMatch match) {
		YahtzeeMatch nextMatch = match;

		final LinkedHashMap<String, Map<YahtzeeLine, Integer>> scores = new LinkedHashMap<>(match.scores);
		final YahtzeeTurn turn = match.turn;
		final Map<YahtzeeLine, Integer> playerScore = new HashMap<>(scores.get(turn.player));

		LOGGER.info("{} rolled {} and choose to {}", turn.player, turn.dice, action);

		if(turn.turnCount<= 2 && action instanceof YahtzeeAction.YahtzeeRollAction) {
			YahtzeeAction.YahtzeeRollAction rollAction = (YahtzeeAction.YahtzeeRollAction) action;
			LOGGER.info("{} rolls {}", turn.player, rollAction.diceIndex);
			nextMatch = new YahtzeeMatch(scores, turn.rolling(rollAction.diceIndex));
		} else {
			YahtzeeLine line = Optional.ofNullable(action)
					.filter(YahtzeeAction.YahtzeeScoreAction.class::isInstance)
					.map(YahtzeeAction.YahtzeeScoreAction.class::cast)
					.map(scoreAction -> scoreAction.line)
					.filter(actionLine -> !playerScore.containsKey(actionLine))
					.orElse(Arrays.stream(YahtzeeLine.values())
							.filter(otherLine -> !playerScore.containsKey(otherLine))
							.findFirst()
							.orElse(null));

			if(line != null) {
				LOGGER.info("{} scores {} points on {} line", turn.player, line.score.apply(turn.dice), line);
				playerScore.put(line, line.score.apply(turn.dice));
			}
			scores.put(turn.player, playerScore);
			Function<Map.Entry<String, Map<YahtzeeLine, Integer>>, Integer> sum = entry -> entry.getValue().values().stream().mapToInt(i -> i).sum();
			Function<Map.Entry<String, Map<YahtzeeLine, Integer>>, String> name = entry -> entry.getKey();

			Map<Integer, List<String>> collect = scores.entrySet().stream()
					.collect(Collectors.groupingBy(sum, Collectors.mapping(name, Collectors.toList())));

			Comparator<Map.Entry<Integer, List<String>>> comparator = Comparator.comparing(Map.Entry<Integer, List<String>>::getKey).reversed();
			nextMatch = new YahtzeeMatch(scores, YahtzeeTurn.playing(getNextPlayer(turn.player, scores.keySet())),
					scores.values().stream().map(Map::keySet).map(Collection::size).filter(lineCount -> lineCount != YahtzeeLine.values().length).findAny().equals(Optional.empty()),
					collect.entrySet().stream().sorted(comparator).map(Entry::getValue).findFirst().orElse(Collections.emptyList()));
		}

		return nextMatch;
	}

	private String getNextPlayer(String player, Collection<String> players) {
		Iterator<String> iterator = players.iterator();
		while (iterator.hasNext() && !iterator.next().equals(player)) {
		}
		return iterator.hasNext() ? iterator.next() : players.iterator().next();
	}

}
