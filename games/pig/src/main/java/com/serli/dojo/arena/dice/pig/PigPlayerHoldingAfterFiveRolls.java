package com.serli.dojo.arena.dice.pig;

public class PigPlayerHoldingAfterFiveRolls extends PigPlayerRollingUntil {

	public PigPlayerHoldingAfterFiveRolls(String name) {
		super(name);
	}

	@Override
	protected boolean shouldHold(PigState state) {
		return state.turn.turnCount >= 5;
	}
}
