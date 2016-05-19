package com.serli.dojo.arena.dice.pig;

public abstract class PigPlayerRollingUntil extends PigPlayer {

	public PigPlayerRollingUntil(String name) {
		super(name);
	}

	@Override
	public PigAction play(PigState state) {
		if (shouldHold(state)) {
			return PigAction.HOLD;
		}
		return PigAction.ROLL;
	}

	protected abstract boolean shouldHold(PigState state);

}
