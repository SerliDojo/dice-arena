package com.serli.dojo.arena.dice.yahtzee;

public class YahtzeePlayerScoringFirstLine extends YahtzeePlayer {

	public YahtzeePlayerScoringFirstLine(String name) {
		super(name);
	}

	@Override
	public YahtzeeAction play(YahtzeeMatch match) {
		return YahtzeeAction.score(YahtzeeLine.ACES);
	}

}
