package com.serli.dojo.arena.dice.poker;

public class PokerPlayerRolling extends PokerPlayer {

	public PokerPlayerRolling(String name) {
		super(name);
	}

	@Override
	public PokerAction play(PokerState state) {
		return PokerAction.roll(0, 1, 2, 3, 4);
	}

}
