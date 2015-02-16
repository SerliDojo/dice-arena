package com.serli.dojo.dicearena.stats.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Class dedicated to carry match informations like players and their scores.
 * 
 * @author Laurent
 */
public class Match implements Stat {

	public static final String TYPE = "match";

	private Long id;
	private String game;
	private LocalDateTime startTime, endTime;
	private List<Score> scores = new ArrayList<>();

	public Match() {
	}

	public Match(Long id, String game, LocalDateTime startTime, LocalDateTime endTime) {
		this.id = id;
		this.game = game;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	@Override
	public String getType() {
		return TYPE;
	}

	@Override
	public String getId() {
		return id.toString();
	}

	@Override
	public String toJsonString() {
		Optional<Score> winner = scores.stream().collect(Collectors.maxBy((o1, o2) -> {
			return o1.getScore().compareTo(o2.getScore());
		}));

		String winnerString = "";
		if (winner.isPresent()) {
			winnerString = winner.get().getPlayer();
		}

		String scoresString = scores.stream().map(score -> {
			return String.format("{ \"player\" : \"%s\", \"score\" : %d }", score.getPlayer(), score.getScore());
		}).collect(Collectors.joining(", "));

		return String.format("{ \"_timestamp\": \"%s\", \"id\" : \"%d\", \"game\" : \"%s\", \"startTime\" : \"%s\", \"endTime\" : \"%s\", \"scores\" : [ %s ], \"winner\" : \"%s\" }",
				startTime, id, game, startTime, endTime, scoresString, winnerString);
	}

	public String getGame() {
		return game;
	}

	public void setGame(String game) {
		this.game = game;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

	public List<Score> getScores() {
		return scores;
	}

	public void setScores(List<Score> scores) {
		this.scores = scores;
	}

	public void setId(Long id) {
		this.id = id;
	}

}