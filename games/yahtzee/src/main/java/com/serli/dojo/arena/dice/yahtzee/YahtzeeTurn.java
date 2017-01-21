package com.serli.dojo.arena.dice.yahtzee;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class YahtzeeTurn {

	public final String player;
	public final Integer turnCount;
	public final YahtzeeDice dice;

	public static YahtzeeTurn playing(String player) {
		return new YahtzeeTurn(player, YahtzeeDice.roll(), 1);
	}

	public YahtzeeTurn rolling(Integer... indexes) {
		return new YahtzeeTurn(player, dice.rolling(indexes), turnCount + 1);
	}

	@JsonCreator
	public YahtzeeTurn(
			@JsonProperty("player") String player,
			@JsonProperty("dice") YahtzeeDice dice,
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
