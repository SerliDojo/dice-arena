package com.serli.dojo.arena.dice.yahtzee;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.serli.dojo.arena.dice.Dice;

public class YahtzeeTurn {

	public final String player;
	public final Integer turnCount;
	public final Dice dice;

	public static YahtzeeTurn playing(String player) {
		return new YahtzeeTurn(player, Dice.roll(5), 1);
	}

	public YahtzeeTurn rolling(Integer... indexes) {
		return new YahtzeeTurn(player, dice.rolling(indexes), turnCount + 1);
	}

	@JsonCreator
	public YahtzeeTurn(
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
