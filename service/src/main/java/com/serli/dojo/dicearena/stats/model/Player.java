package com.serli.dojo.dicearena.stats.model;

/**
 * Class dedicated to carry player information.
 * 
 * @author Laurent
 */
public class Player implements Stat {

	public static final String TYPE = "player";

	public String name;
	public Account account;
	public Game game;

	public Player(String name, Account account, Game game) {
		this.name = name;
		this.account = account;
		this.game = game;
	}

	@Override
	public String getType() {
		return TYPE;
	}

	@Override
	public String getId() {
		return name;
	}

	@Override
	public String toJsonString() {
		return String.format("{ \"name\" : \"%s\", \"game\" : \"%s\", \"account\" : \"%s\" }", name, game.name, account.email);
	}
}