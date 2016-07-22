package com.serli.dojo.arena.dice.pig;

public class PigPlayerHolding extends PigPlayer {

	public PigPlayerHolding(String name) {
		super(name);
	}

	@Override
	public PigAction play(PigMatch match) {
		return PigAction.HOLD;
	}

}
