package com.serli.dojo.arena.dice.poker;

import java.util.Arrays;
import java.util.Optional;

import com.serli.dojo.arena.dice.Action;

public class PokerAction implements Action {

	public final Optional<Integer[]> diceIndex;

	private PokerAction(Integer[] diceIndex) {
		this.diceIndex = Optional.ofNullable(diceIndex).filter(array -> array.length > 0);
	}

	public static PokerAction hold() {
		return new PokerAction(null);
	}

	public static PokerAction roll(Integer... diceIndex) {
		return new PokerAction(diceIndex);
	}

	@Override
	public String toString() {
		return diceIndex.map(dice -> "Roll " + Arrays.toString(dice)).orElse("Hold");
	}
}
