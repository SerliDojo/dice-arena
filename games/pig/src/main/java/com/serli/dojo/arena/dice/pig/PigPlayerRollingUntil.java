package com.serli.dojo.arena.dice.pig;

public abstract class PigPlayerRollingUntil extends PigPlayer {

	public PigPlayerRollingUntil(String name) {
		super(name);
	}

	@Override
	public PigAction play(PigMatch match) {
		if (shouldHold(match)) {
			return PigAction.HOLD;
		}
		return PigAction.ROLL;
	}

	protected abstract boolean shouldHold(PigMatch match);

}
