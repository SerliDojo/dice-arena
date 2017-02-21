package com.serli.dojo.arena.dice.yahtzee;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.serli.dojo.arena.dice.Die;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class YahtzeeDice {

	public final Integer[] dice;

	public static YahtzeeDice roll() {
		return new YahtzeeDice(Die.roll(), Die.roll(), Die.roll(), Die.roll(), Die.roll());
	}

	@JsonCreator
	public YahtzeeDice(@JsonProperty("dice") Integer... dice) {
		this.dice = dice;
	}

	public YahtzeeDice rolling(Integer... dieIndexes) {
		List<Integer> currentDice = new ArrayList<>();
		currentDice.addAll(Arrays.asList(dice));
		Arrays.stream(dieIndexes)
				.filter(index -> index < currentDice.size())
				.filter(index -> index >= 0)
				.forEach(index -> currentDice.set(index, Die.roll()));
		return new YahtzeeDice(currentDice.toArray(new Integer[dice.length]));
	}

	@Override
	public String toString() {
		return Arrays.toString(dice);
	}
}
