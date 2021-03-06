package com.serli.dojo.arena.dice.poker;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.serli.dojo.arena.dice.Game;

public class PokerGame implements Game<PokerAction, PokerMatch> {

	public static final Logger LOGGER = LoggerFactory.getLogger(PokerGame.class);

	private final String name;

	public PokerGame(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public PokerMatch init(List<String> players) {
		LinkedHashMap<String, Integer> scoreTable = new LinkedHashMap<>();
		players.forEach(player -> scoreTable.put(player, 0));
		PokerTurn turn = PokerTurn.playing(getNextPlayer(null, players));
		return new PokerMatch(scoreTable, turn);
	}

	@Override
	public PokerMatch apply(final PokerAction action, final PokerMatch match) {
		PokerMatch nextMatch = match;

		final LinkedHashMap<String, Integer> scores = match.scores;
		final PokerTurn turn = match.turn;

		LOGGER.info("{} rolled {} and choose to {}", turn.player, turn.dice, action);

		int totalScore = PokerHand.bestScore(turn.dice);
		if(turn.turnCount<= 2 && action.diceIndex.isPresent()) {
			LOGGER.info("{} continues with {} points", turn.player, totalScore);
			nextMatch = new PokerMatch(scores, turn.rolling(action.diceIndex.get()));
		} else {
			LOGGER.info("{} scores {} points", turn.player, totalScore);
			scores.put(turn.player, scores.get(turn.player) + totalScore);
			nextMatch = new PokerMatch(scores, PokerTurn.playing(getNextPlayer(turn.player, scores.keySet())),
					scores.values().stream().filter(score -> score >= 100).findAny().isPresent(),
					scores.entrySet().stream().filter(entry -> entry.getValue() >= 100).map(Entry::getKey).collect(Collectors.toSet()));
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
