package com.serli.dojo.arena.dice.poker;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.serli.dojo.arena.dice.Game;

public class PokerEngine implements Game<PokerAction, PokerState, PokerPlayer> {

	public static final Logger LOGGER = LoggerFactory.getLogger(PokerEngine.class);

	@Override
	public PokerState init(List<PokerPlayer> players) {
		LinkedHashMap<PokerPlayer, Integer> scoreTable = new LinkedHashMap<>();
		players.forEach(player -> scoreTable.put(player, 0));
		PokerTurn turnState = PokerTurn.playing(getNextPlayer(null, players));
		return new PokerState(scoreTable, turnState);
	}

	@Override
	public boolean isFinished(PokerState state) {
		return state.scores.values().stream().filter(score -> score >= 100).findAny().isPresent();
	}

	@Override
	public PokerState step(final PokerState state) {
		final LinkedHashMap<PokerPlayer, Integer> scores = state.scores;
		final PokerTurn turn = state.turn;

		LOGGER.info("{} rolled {}", turn.player.name, turn.dice);

		PokerState nextState = state;
		int totalScore = PokerHand.bestScore(turn.dice);
		PokerAction action = null;
		if(turn.turnCount<= 2 && (action = turn.player.play(state)).diceIndex.isPresent()) {
			LOGGER.info("{} choose to {}", turn.player.name, action);
			LOGGER.info("{} continues with {} points", turn.player.name, totalScore);
			nextState = new PokerState(scores, turn.rolling(action.diceIndex.get()));
		} else {
			LOGGER.info("{} scores {} points", turn.player.name, totalScore);
			scores.put(turn.player, scores.get(turn.player) + totalScore);
			nextState = new PokerState(scores, PokerTurn.playing(getNextPlayer(turn.player, scores.keySet())));
		}

		return nextState;
	}

	private PokerPlayer getNextPlayer(PokerPlayer player, Collection<PokerPlayer> players) {
		Iterator<PokerPlayer> iterator = players.iterator();
		while (iterator.hasNext() && !iterator.next().equals(player)) {
		}
		return iterator.hasNext() ? iterator.next() : players.iterator().next();
	}

}
