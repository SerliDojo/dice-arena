package com.serli.dojo.arena.dice.pig;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.serli.dojo.arena.dice.Game;

public class PigEngine implements Game<PigAction, PigState, PigPlayer> {

	public static final Logger LOGGER = LoggerFactory.getLogger(PigEngine.class);

	@Override
	public PigState init(List<PigPlayer> players) {
		LinkedHashMap<PigPlayer, Integer> scoreTable = new LinkedHashMap<>();
		players.forEach(player -> scoreTable.put(player, 0));
		PigTurn turnState = PigTurn.playing(getNextPlayer(null, players));
		return new PigState(scoreTable, turnState);
	}

	@Override
	public boolean isFinished(PigState state) {
		return state.scores.values().stream().filter(score -> score >= 100).findAny().isPresent();
	}

	@Override
	public PigState step(final PigState state) {
		final LinkedHashMap<PigPlayer, Integer> scores = state.scores;
		final PigTurn turn = state.turn;

		LOGGER.info("{} rolled a {}", turn.player.name, turn.dieScore);

		PigState nextState = state;
		if (turn.dieScore == 1) {
			LOGGER.info("Too bad, next player");
			nextState = new PigState(scores, PigTurn.playing(getNextPlayer(turn.player, scores.keySet())));
		} else {
			PigAction action = turn.player.play(state);
			LOGGER.info("{} choose to {}", turn.player.name, action);

			int totalScore = turn.turnScore + turn.dieScore;
			switch(action) {
			case ROLL:
				LOGGER.info("{} continues with {} points", turn.player.name, totalScore);
				nextState = new PigState(scores, turn.scoring());
				break;
			case HOLD:
				LOGGER.info("{} scores {} points", turn.player.name, totalScore);
				scores.put(turn.player, scores.get(turn.player) + totalScore);
				nextState = new PigState(scores, PigTurn.playing(getNextPlayer(turn.player, scores.keySet())));
				break;
			default:
				LOGGER.info("{}'s choice not understood", turn.player.name, totalScore);
				nextState = new PigState(scores, PigTurn.playing(getNextPlayer(turn.player, scores.keySet())));
			}
		}

		return nextState;
	}

	private PigPlayer getNextPlayer(PigPlayer player, Collection<PigPlayer> players) {
		Iterator<PigPlayer> iterator = players.iterator();
		while (iterator.hasNext() && !iterator.next().equals(player)) {
		}
		return iterator.hasNext() ? iterator.next() : players.iterator().next();
	}

}
