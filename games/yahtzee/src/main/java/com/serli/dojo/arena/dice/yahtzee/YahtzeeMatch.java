package com.serli.dojo.arena.dice.yahtzee;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.serli.dojo.arena.dice.Match;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class YahtzeeMatch implements Match<YahtzeeAction> {

	public final LinkedHashMap<String, Map<YahtzeeLine, Integer>> scores;
	public final YahtzeeTurn turn;
	public final boolean finished;
	public final Collection<String> winners;

	@JsonCreator
	public YahtzeeMatch(
			@JsonProperty("name") final LinkedHashMap<String, Map<YahtzeeLine, Integer>> scores,
			@JsonProperty("turn") final YahtzeeTurn turn,
			@JsonProperty("finished") final boolean finished,
			@JsonProperty("winners") final Collection<String> winners) {
		this.scores = scores;
		this.turn = turn;
		this.finished = finished;
		this.winners = winners;
	}

	public YahtzeeMatch(final LinkedHashMap<String, Map<YahtzeeLine, Integer>> scores, final YahtzeeTurn turn) {
		this.scores = scores;
		this.turn = turn;
		this.finished = false;
		this.winners = Collections.emptyList();
	}

	@Override
	public String getCurrentPlayer() {
		return turn.player;
	}

	@Override
	public boolean isFinished() {
		return finished;
	}

	@Override
	public Collection<String> getWinners() {
		return winners;
	}

	@Override
	public String toString() {
		return String.format("Scores: %s, Turn: %s", scores, turn);
	}
}
