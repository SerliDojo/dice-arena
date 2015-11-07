package com.serli.dojo.arena.dice.pig;

public class PigPlayerHoldingAfterFifteenPointsEarned extends PigPlayer {

	public PigPlayerHoldingAfterFifteenPointsEarned(String name) {
		super(name);
	}

	@Override
	public PigGameAction play(PigGameState state) {
		if (state.turnState.turnScore + state.turnState.dieScore < 15) {
			return PigGameAction.ROLL;
		}
		return PigGameAction.HOLD;
	}

}
