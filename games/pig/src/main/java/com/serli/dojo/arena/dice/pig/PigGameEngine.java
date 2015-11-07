package com.serli.dojo.arena.dice.pig;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.serli.dojo.arena.dice.Die;

public class PigGameEngine {

	public static final Logger LOGGER = LoggerFactory.getLogger(PigGameEngine.class);

	public static void main(String[] args) {
		PigGameEngine engine = new PigGameEngine(Arrays.asList(
				new PigPlayerHolding("John"),
				new PigPlayerHoldingAfterFifteenPointsEarned("Bill"),
				new PigPlayerHoldingAfterFiveRolls("Gary")));

		while (!engine.step()) {
			LOGGER.debug("Stepping");
		}
	}

	private final Map<PigPlayer, Integer> scoreTable = new LinkedHashMap<>();
	private Iterator<PigPlayer> turnPlayer;
	private PigTurnState turnState;

	public PigGameEngine(List<PigPlayer> players) {
		players.stream().forEach(player -> scoreTable.put(player, 0));
	}

	private boolean step() {
		if (turnPlayer == null || !turnPlayer.hasNext()) {
			turnPlayer = scoreTable.keySet().iterator();
		}

		if (turnState == null) {
			PigPlayer currentPlayer = turnPlayer.next();
			LOGGER.info("Next player is " + currentPlayer.name);
			turnState = new PigTurnState(currentPlayer, Die.roll());
		}

		LOGGER.info(turnState.player.name + " rolled a " + turnState.dieScore);

		if (turnState.dieScore == 1) {
			LOGGER.info("Too bad, next player");
			turnState = null;
		} else {
			PigGameAction action = turnState.player.play(new PigGameState(scoreTable, turnState));
			LOGGER.info(turnState.player.name + " choose to " + action);
			int totalScore = turnState.turnScore + turnState.dieScore;
			if (action == PigGameAction.ROLL) {
				LOGGER.info(turnState.player.name + " continues with " + totalScore + " points");
				turnState = new PigTurnState(turnState.player,
						totalScore, turnState.turnCount + 1, Die.roll());
			} else {
				LOGGER.info(turnState.player.name + " scores " + totalScore + " points");
				Integer playerScore = scoreTable.get(turnState.player);
				playerScore += +totalScore;
				scoreTable.put(turnState.player, playerScore);
				if (playerScore >= 100) {
					LOGGER.info(turnState.player.name + " won the game with " + playerScore + " points");
					LOGGER.info("Final scores");
					scoreTable.keySet().stream().forEach(player ->
							LOGGER.info(player.name + ": " + scoreTable.get(player)));
					return true;
				}
				turnState = null;
			}
		}

		return false;
	}
}
