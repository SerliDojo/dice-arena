package com.serli.dojo.arena.dice.poker;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.serli.dojo.arena.dice.Match;

public class PokerMatch implements Match {

	public final LinkedHashMap<PokerPlayer, Integer> scores;
	public final PokerTurn turn;

	public PokerMatch(final LinkedHashMap<PokerPlayer, Integer> scoreTable, final PokerTurn turnState) {
		this.scores = scoreTable;
		this.turn = turnState;
	}

	public PokerMatch(final LinkedHashMap<PokerPlayer, Integer> scoreTable) {
		this.scores = scoreTable;
		this.turn = null;
	}

	@Override
	public boolean isFinished() {
		return scores.values().stream().filter(score -> score >= 100).findAny().isPresent();
	}

	public Collection<PokerPlayer> getWinners() {
		return scores.entrySet().stream().filter(entry -> entry.getValue() >= 100).map(Entry::getKey).collect(Collectors.toSet());
	}

	@Override
	public String toString() {
		return String.format("current scores: %s, current turn state: %s", scores, turn);
	}
}
