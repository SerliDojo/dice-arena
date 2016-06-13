package com.serli.dojo.arena.dice.poker;

import com.serli.dojo.arena.dice.Player;

public abstract class PokerPlayer implements Player<PokerAction, PokerState> {

	public final String name;

	public PokerPlayer(String name) {
		this.name = name;
	}

	@Override
	public abstract PokerAction play(PokerState state);

	@Override
	public String toString() {
		return name;
	}
}
