package com.serli.dojo.dicearena.stats.model;

public class Score {

	private String player;
	private Integer score;

	public Score() {
	}

	public Score(String player, Integer score) {
		super();
		this.player = player;
		this.score = score;
	}

	public String getPlayer() {
		return player;
	}

	public void setPlayer(String player) {
		this.player = player;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

}
