package com.serli.dojo.arena.dice.pig;

public class PigPlayerHoldingAfterFifteenPointsEarned extends PigPlayerRollingUntil {

	public PigPlayerHoldingAfterFifteenPointsEarned(String name) {
		super(name);
	}

	@Override
	protected boolean shouldHold(PigMatch match) {
		return match.turn.turnScore + match.turn.dieScore >= 15;
	}

}
