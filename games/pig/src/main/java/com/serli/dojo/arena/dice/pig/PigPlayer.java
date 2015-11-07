package com.serli.dojo.arena.dice.pig;

public abstract class PigPlayer {

	public final String name;

	public PigPlayer(String name) {
		this.name = name;
	}

	public abstract PigGameAction play(PigGameState state);
}
