package com.serli.dojo.arena.dice.poker;

public class PokerTurn {

	public final String player;
	public final Integer turnCount;
	public final PokerDice dice;

	public static PokerTurn playing(String player) {
		return new PokerTurn(player, PokerDice.roll(), 1);
	}

	public PokerTurn rolling(Integer... indexes) {
		return new PokerTurn(player, dice.rolling(indexes), turnCount + 1);
	}

	private PokerTurn(String player, PokerDice dice, Integer turnCount) {
		this.player = player;
		this.turnCount = turnCount;
		this.dice = dice;
	}

	@Override
	public String toString() {
		return String.format("%s currently rolled %s at turn %d", player, dice, turnCount);
	}
}
