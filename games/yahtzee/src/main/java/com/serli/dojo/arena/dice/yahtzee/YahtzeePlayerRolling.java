package com.serli.dojo.arena.dice.yahtzee;

public class YahtzeePlayerRolling extends YahtzeePlayer {

	public YahtzeePlayerRolling(String name) {
		super(name);
	}

	@Override
	public YahtzeeAction play(YahtzeeMatch match) {
		return YahtzeeAction.roll(0, 1, 2, 3, 4);
	}

}
