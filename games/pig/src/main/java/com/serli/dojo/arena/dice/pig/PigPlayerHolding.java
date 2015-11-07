package com.serli.dojo.arena.dice.pig;

public class PigPlayerHolding extends PigPlayer {

	public PigPlayerHolding(String name) {
		super(name);
	}

	@Override
	public PigGameAction play(PigGameState state) {
		return PigGameAction.HOLD;
	}

}
