package com.serli.dojo.arena.dice.pig;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.serli.dojo.arena.dice.State;

public class PigState implements State {

	public final LinkedHashMap<PigPlayer, Integer> scores;
	public final PigTurn turn;

	public PigState(final LinkedHashMap<PigPlayer, Integer> scoreTable, final PigTurn turnState) {
		this.scores = scoreTable;
		this.turn = turnState;
	}

	public PigState(final LinkedHashMap<PigPlayer, Integer> scoreTable) {
		this.scores = scoreTable;
		this.turn = null;
	}

	@Override
	public boolean isFinished() {
		return scores.values().stream().filter(score -> score >= 100).findAny().isPresent();
	}

	public Collection<PigPlayer> getWinners() {
		return scores.entrySet().stream().filter(entry -> entry.getValue() >= 100).map(Entry::getKey).collect(Collectors.toSet());
	}

	@Override
	public String toString() {
		return String.format("current scores: %s, current turn state: %s", scores, turn);
	}
}
