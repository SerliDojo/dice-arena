package com.serli.dojo.arena.dice.pig;

import com.serli.dojo.arena.dice.Player;

public abstract class PigPlayer implements Player<PigAction, PigState> {

	public final String name;

	public PigPlayer(String name) {
		this.name = name;
	}

	@Override
	public abstract PigAction play(PigState state);

	@Override
	public String toString() {
		return name;
	}
}
