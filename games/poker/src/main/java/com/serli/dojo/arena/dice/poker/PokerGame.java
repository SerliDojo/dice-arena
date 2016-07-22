package com.serli.dojo.arena.dice.poker;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.serli.dojo.arena.dice.Game;

public class PokerGame implements Game<PokerAction, PokerMatch, PokerPlayer> {

	public static final Logger LOGGER = LoggerFactory.getLogger(PokerGame.class);

	@Override
	public PokerMatch init(List<PokerPlayer> players) {
		LinkedHashMap<PokerPlayer, Integer> scoreTable = new LinkedHashMap<>();
		players.forEach(player -> scoreTable.put(player, 0));
		PokerTurn turn = PokerTurn.playing(getNextPlayer(null, players));
		return new PokerMatch(scoreTable, turn);
	}

	@Override
	public PokerMatch apply(final PokerAction action, final PokerMatch match) {
		PokerMatch nextMatch = match;

		final LinkedHashMap<PokerPlayer, Integer> scores = match.scores;
		final PokerTurn turn = match.turn;

		LOGGER.info("{} rolled {} and choose to {}", turn.player.name(), turn.dice, action);

		int totalScore = PokerHand.bestScore(turn.dice);
		if(turn.turnCount<= 2 && action.diceIndex.isPresent()) {
			LOGGER.info("{} continues with {} points", turn.player.name(), totalScore);
			nextMatch = new PokerMatch(scores, turn.rolling(action.diceIndex.get()));
		} else {
			LOGGER.info("{} scores {} points", turn.player.name(), totalScore);
			scores.put(turn.player, scores.get(turn.player) + totalScore);
			nextMatch = new PokerMatch(scores, PokerTurn.playing(getNextPlayer(turn.player, scores.keySet())));
		}

		return nextMatch;
	}

	private PokerPlayer getNextPlayer(PokerPlayer player, Collection<PokerPlayer> players) {
		Iterator<PokerPlayer> iterator = players.iterator();
		while (iterator.hasNext() && !iterator.next().equals(player)) {
		}
		return iterator.hasNext() ? iterator.next() : players.iterator().next();
	}

}
