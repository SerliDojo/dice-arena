package com.serli.dojo.arena.dice.yahtzee;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class YahtzeePlayerScoringHighestLine extends YahtzeePlayer {

	public YahtzeePlayerScoringHighestLine(String name) {
		super(name);
	}

	@Override
	public YahtzeeAction play(YahtzeeMatch match) {
		Map<YahtzeeLine, Integer> lines = match.scores.get(getName());
		Comparator<YahtzeeLine> lineComparator = Comparator.comparing((YahtzeeLine line) -> line.score.apply(match.turn.dice)).reversed();
		return YahtzeeAction.score(Arrays.stream(YahtzeeLine.values())
				.filter(line -> !lines.containsKey(line))
				.sorted(lineComparator)
				.findFirst()
				.orElse(YahtzeeLine.ACES));
	}

}
