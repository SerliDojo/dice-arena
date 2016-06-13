package com.serli.dojo.arena.dice.poker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.serli.dojo.arena.dice.Die;

public class PokerDice {

	public final Integer[] dice;

	public static PokerDice roll() {
		return new PokerDice(Die.roll(), Die.roll(), Die.roll(), Die.roll(), Die.roll());
	}

	public PokerDice(Integer... dice) {
		this.dice = dice;
	}

	public PokerDice rolling(Integer... dieIndexes) {
		List<Integer> currentDice = new ArrayList<>();
		currentDice.addAll(Arrays.asList(dice));
		Arrays.stream(dieIndexes)
				.filter(index -> index < currentDice.size())
				.filter(index -> index >= 0)
				.forEach(index -> currentDice.set(index, Die.roll()));
		return new PokerDice(currentDice.toArray(new Integer[dice.length]));
	}

	@Override
	public String toString() {
		return Arrays.toString(dice);
	}
}
