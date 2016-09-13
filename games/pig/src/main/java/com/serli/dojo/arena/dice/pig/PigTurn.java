package com.serli.dojo.arena.dice.pig;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.serli.dojo.arena.dice.Die;

public class PigTurn {

	public final String player;
	public final Integer turnScore;
	public final Integer turnCount;
	public final Integer dieScore;

	public static PigTurn playing(String player) {
		return new PigTurn(player, 0, 1, Die.roll());
	}
	
	public PigTurn scoring() {
		return new PigTurn(player, turnScore + dieScore, turnCount + 1, Die.roll());
	}

	@JsonCreator
	public PigTurn(
			@JsonProperty("player") String player,
			@JsonProperty("turnScore") Integer turnScore,
			@JsonProperty("turnCount") Integer turnCount,
			@JsonProperty("dieScore") Integer dieScore) {
		this.player = player;
		this.turnScore = turnScore;
		this.turnCount = turnCount;
		this.dieScore = dieScore;
	}

	@Override
	public String toString() {
		return String.format("%s currently holds %d points at turn %d and rolled %d", player, turnScore, turnCount, dieScore);
	}
}
