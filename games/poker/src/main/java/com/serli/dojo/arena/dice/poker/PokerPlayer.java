package com.serli.dojo.arena.dice.poker;

import com.serli.dojo.arena.dice.Player;

public abstract class PokerPlayer implements Player<PokerAction, PokerMatch> {

	private final String name;

	public PokerPlayer(String name) {
		this.name = name;
	}

	@Override
	public abstract PokerAction play(PokerMatch match);

	@Override
	public String name() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}
}
