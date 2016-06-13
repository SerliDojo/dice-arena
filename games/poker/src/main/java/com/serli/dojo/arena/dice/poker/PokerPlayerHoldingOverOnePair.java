package com.serli.dojo.arena.dice.poker;

import java.util.Arrays;
import java.util.List;

public class PokerPlayerHoldingOverOnePair extends PokerPlayer {

	private static final List<PokerHand> NOT_ACCEPTABLES = Arrays.asList(PokerHand.HIGH_DIE, PokerHand.ONE_PAIR);

	public PokerPlayerHoldingOverOnePair(String name) {
		super(name);
	}

	@Override
	public PokerAction play(PokerState state) {
		if (NOT_ACCEPTABLES.contains(PokerHand.bestHand(state.turn.dice))) {
			return PokerAction.roll(0, 1, 2, 3, 4);
		}
		return PokerAction.hold();
	}

}
