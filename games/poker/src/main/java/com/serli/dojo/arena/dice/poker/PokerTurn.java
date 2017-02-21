package com.serli.dojo.arena.dice.poker;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.serli.dojo.arena.dice.Dice;

public class PokerTurn {

	public final String player;
	public final Integer turnCount;
	public final Dice dice;

	public static PokerTurn playing(String player) {
		return new PokerTurn(player, Dice.roll(5), 1);
	}

	public PokerTurn rolling(Integer... indexes) {
		return new PokerTurn(player, dice.rolling(indexes), turnCount + 1);
	}

	@JsonCreator
	public PokerTurn(
			@JsonProperty("player") String player,
			@JsonProperty("dice") Dice dice,
			@JsonProperty("turnCount") Integer turnCount) {
		this.player = player;
		this.turnCount = turnCount;
		this.dice = dice;
	}

	@Override
	public String toString() {
		return String.format("%s currently rolled %s at turn %d", player, dice, turnCount);
	}
}
