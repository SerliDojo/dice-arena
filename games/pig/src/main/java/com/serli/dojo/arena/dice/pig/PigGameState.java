package com.serli.dojo.arena.dice.pig;

import java.util.Map;

public class PigGameState {

	public final Map<PigPlayer, Integer> scoreTable;
	public final PigTurnState turnState;

	public PigGameState(final Map<PigPlayer, Integer> scoreTable, final PigTurnState turnState) {
		this.scoreTable = scoreTable;
		this.turnState = turnState;
	}

	@Override
	public String toString() {
		return String.format("current scores: %s, current turn state: %s", scoreTable, turnState);
	}
}
