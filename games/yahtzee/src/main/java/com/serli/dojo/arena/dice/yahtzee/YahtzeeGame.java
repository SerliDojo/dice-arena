package com.serli.dojo.arena.dice.yahtzee;

import com.serli.dojo.arena.dice.Game;
import java.util.*;
import java.util.Map.Entry;
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
		LinkedHashMap<String, YahtzeeCard> scoreTable = new LinkedHashMap<>();
		players.forEach(player -> scoreTable.put(player, new YahtzeeCard()));
		YahtzeeTurn turn = YahtzeeTurn.playing(getNextPlayer(null, players));
		return new YahtzeeMatch(scoreTable, turn);
	}

	@Override
	public YahtzeeMatch apply(final YahtzeeAction action, final YahtzeeMatch match) {
		YahtzeeMatch nextMatch = match;

		final LinkedHashMap<String, YahtzeeCard> scores = new LinkedHashMap<>(match.scores);
		final YahtzeeTurn turn = match.turn;
		final YahtzeeCard playerCard = scores.get(turn.player);

		LOGGER.info("{} rolled {} and choose to {}", turn.player, turn.dice, action);
		return Optional.ofNullable(action)
				.filter(YahtzeeAction.YahtzeeRollAction.class::isInstance)
				.filter(any -> turn.turnCount <= 2)
				.map(YahtzeeAction.YahtzeeRollAction.class::cast)
				.map(rollAction -> rollAction.diceIndex)
				.map(diceIndex -> {
					LOGGER.info("{} rolls {}", turn.player, diceIndex);
					return new YahtzeeMatch(scores, turn.rolling(diceIndex));
				})
				.orElseGet(() -> {
					Optional.ofNullable(action)
							.filter(YahtzeeAction.YahtzeeScoreAction.class::isInstance)
							.map(YahtzeeAction.YahtzeeScoreAction.class::cast)
							.map(scoreAction -> scoreAction.line)
							.map(playerCard::lineOrNext)
							.orElseGet(playerCard::nextEmptyLine)
							.map(line -> {
								LOGGER.info("{} scores {} points on {} line", turn.player, line.score.apply(turn.dice), line);
								return playerCard.scoring(line, line.score.apply(turn.dice));
							})
							.ifPresent(card -> scores.put(turn.player, card));
					return new YahtzeeMatch(
							scores, YahtzeeTurn.playing(getNextPlayer(turn.player, scores.keySet())),
							isFinished(scores), getWinners(scores));
				});
	}

	private boolean isFinished(LinkedHashMap<String, YahtzeeCard> scores) {
		return scores.values().stream().allMatch(YahtzeeCard::full);
	}

	private List<String> getWinners(LinkedHashMap<String, YahtzeeCard> scores) {
		Comparator<Map.Entry<Integer, List<String>>> comparator = Comparator.comparing(Map.Entry<Integer, List<String>>::getKey).reversed();
		return scores.entrySet().stream()
				.collect(Collectors.groupingBy(this::scoreForEntry, Collectors.mapping(Entry::getKey, Collectors.toList())))
				.entrySet()
				.stream()
				.sorted(comparator)
				.map(Entry::getValue)
				.findFirst()
				.orElse(Collections.emptyList());
	}

	private Integer scoreForEntry(Entry<String, YahtzeeCard> entry) {
		return entry.getValue().score();
	}

	private String getNextPlayer(String player, Collection<String> players) {
		Iterator<String> iterator = players.iterator();
		while (iterator.hasNext() && !iterator.next().equals(player)) {
		}
		return iterator.hasNext() ? iterator.next() : players.iterator().next();
	}

}
