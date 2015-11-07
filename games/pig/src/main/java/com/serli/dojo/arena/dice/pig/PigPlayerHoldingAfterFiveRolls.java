package com.serli.dojo.arena.dice.pig;

public class PigPlayerHoldingAfterFiveRolls extends PigPlayer {

	public PigPlayerHoldingAfterFiveRolls(String name) {
		super(name);
	}

	@Override
	public PigGameAction play(PigGameState state) {
		if (state.turnState.turnCount < 5) {
			return PigGameAction.ROLL;
		}
		return PigGameAction.HOLD;
	}

}
