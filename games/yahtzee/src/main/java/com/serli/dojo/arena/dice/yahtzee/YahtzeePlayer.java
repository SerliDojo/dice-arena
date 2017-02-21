package com.serli.dojo.arena.dice.yahtzee;

import com.serli.dojo.arena.dice.Player;

public abstract class YahtzeePlayer implements Player<YahtzeeAction, YahtzeeMatch> {

	private final String name;

	public YahtzeePlayer(String name) {
		this.name = name;
	}

	@Override
	public abstract YahtzeeAction play(YahtzeeMatch match);

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}
}
