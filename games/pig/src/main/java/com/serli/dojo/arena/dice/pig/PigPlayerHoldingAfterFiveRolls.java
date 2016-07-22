package com.serli.dojo.arena.dice.pig;

public class PigPlayerHoldingAfterFiveRolls extends PigPlayerRollingUntil {

	public PigPlayerHoldingAfterFiveRolls(String name) {
		super(name);
	}

	@Override
	protected boolean shouldHold(PigMatch match) {
		return match.turn.turnCount >= 5;
	}
}
