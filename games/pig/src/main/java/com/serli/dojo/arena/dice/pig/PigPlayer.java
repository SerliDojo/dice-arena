package com.serli.dojo.arena.dice.pig;

import com.serli.dojo.arena.dice.Player;

public abstract class PigPlayer implements Player<PigAction, PigMatch> {

	private final String name;

	public PigPlayer(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public abstract PigAction play(PigMatch match);

	@Override
	public String toString() {
		return name;
	}
}
