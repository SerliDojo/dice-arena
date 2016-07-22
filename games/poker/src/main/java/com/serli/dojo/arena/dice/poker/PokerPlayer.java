package com.serli.dojo.arena.dice.poker;

import com.serli.dojo.arena.dice.Player;

public abstract class PokerPlayer implements Player<PokerAction, PokerMatch> {

	public final String name;

	public PokerPlayer(String name) {
		this.name = name;
	}

	@Override
	public abstract PokerAction play(PokerMatch match);

	@Override
	public String toString() {
		return name;
	}
}
