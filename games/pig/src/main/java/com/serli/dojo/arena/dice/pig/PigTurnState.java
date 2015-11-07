package com.serli.dojo.arena.dice.pig;

public class PigTurnState {

	public final PigPlayer player;
	public final Integer turnScore;
	public final Integer turnCount;
	public final Integer dieScore;

	public PigTurnState(PigPlayer player, Integer turnScore, Integer turnCount, Integer dieScore) {
		this.player = player;
		this.turnScore = turnScore;
		this.turnCount = turnCount;
		this.dieScore = dieScore;
	}

	public PigTurnState(final PigPlayer player, final Integer dieScore) {
		this(player, 0, 1, dieScore);
	}

	@Override
	public String toString() {
		return String.format("%s currently holds %d points at turn %d and rolled %d", player.name, turnScore, turnCount, dieScore);
	}
}
