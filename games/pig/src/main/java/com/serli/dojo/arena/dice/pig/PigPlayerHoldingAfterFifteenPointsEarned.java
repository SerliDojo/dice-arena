package com.serli.dojo.arena.dice.pig;

public class PigPlayerHoldingAfterFifteenPointsEarned extends PigPlayerRollingUntil {

	public PigPlayerHoldingAfterFifteenPointsEarned(String name) {
		super(name);
	}

	@Override
	protected boolean shouldHold(PigState state) {
		return state.turn.turnScore + state.turn.dieScore >= 15;
	}

}
