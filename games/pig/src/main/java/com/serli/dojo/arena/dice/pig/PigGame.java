package com.serli.dojo.arena.dice.pig;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.serli.dojo.arena.dice.Game;

public class PigGame implements Game<PigAction, PigMatch, PigPlayer> {

	public static final Logger LOGGER = LoggerFactory.getLogger(PigGame.class);

	@Override
	public PigMatch init(List<PigPlayer> players) {
		LinkedHashMap<PigPlayer, Integer> scoreTable = new LinkedHashMap<>();
		players.forEach(player -> scoreTable.put(player, 0));
		PigTurn turn = PigTurn.playing(getNextPlayer(null, players));
		return new PigMatch(scoreTable, turn);
	}

	@Override
	public PigMatch apply(PigAction action, PigMatch match) {
		PigMatch nextMatch = match;

		final LinkedHashMap<PigPlayer, Integer> scores = match.scores;
		final PigTurn turn = match.turn;

		LOGGER.info("{} rolled a {} and choose to {}", turn.player.name(), turn.dieScore, action);
		if (turn.dieScore == 1) {
			LOGGER.info("Too bad, next player");
			nextMatch = new PigMatch(scores, PigTurn.playing(getNextPlayer(turn.player, scores.keySet())));
		} else {
			int totalScore = turn.turnScore + turn.dieScore;
			switch(action) {
			case ROLL:
				LOGGER.info("{} continues with {} points", turn.player.name(), totalScore);
				nextMatch = new PigMatch(scores, turn.scoring());
				break;
			case HOLD:
				LOGGER.info("{} scores {} points", turn.player.name(), totalScore);
				scores.put(turn.player, scores.get(turn.player) + totalScore);
				nextMatch = new PigMatch(scores, PigTurn.playing(getNextPlayer(turn.player, scores.keySet())));
				break;
			default:
				LOGGER.info("{}'s choice not understood", turn.player.name(), totalScore);
				nextMatch = new PigMatch(scores, PigTurn.playing(getNextPlayer(turn.player, scores.keySet())));
			}
		}

		return nextMatch;
	}

	private PigPlayer getNextPlayer(PigPlayer player, Collection<PigPlayer> players) {
		Iterator<PigPlayer> iterator = players.iterator();
		while (iterator.hasNext() && !iterator.next().equals(player)) {
		}
		return iterator.hasNext() ? iterator.next() : players.iterator().next();
	}

}
