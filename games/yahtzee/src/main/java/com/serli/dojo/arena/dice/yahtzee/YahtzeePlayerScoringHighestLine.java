package com.serli.dojo.arena.dice.yahtzee;

import java.util.Arrays;
import java.util.Comparator;

public class YahtzeePlayerScoringHighestLine extends YahtzeePlayer {

	public YahtzeePlayerScoringHighestLine(String name) {
		super(name);
	}

	@Override
	public YahtzeeAction play(YahtzeeMatch match) {
		YahtzeeCard card = match.scores.get(getName());
		Comparator<YahtzeeLine> lineComparator = Comparator.comparing((YahtzeeLine line) -> line.score.apply(match.turn.dice)).reversed();
		return YahtzeeAction.score(Arrays.stream(YahtzeeLine.values())
				.filter(card::lineFree)
				.sorted(lineComparator)
				.findFirst()
				.orElse(YahtzeeLine.ACES));
	}

}
