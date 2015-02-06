package com.serli.dojo.dicearena.data;

/**
 * Class dedicated to carry player information.
 * 
 * @author Laurent
 */
public class Player implements Entity {

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
	public String toIndexString() {
		return String.format("{ \"index\" : { \"_index\" : \"%s\", \"_type\" : \"%s\", \"_id\" : \"%s\" } }", INDEX, TYPE, name);
	}

	@Override
	public String toJsonString() {
		return String.format("{ \"name\" : \"%s\", \"game\" : \"%s\", \"account\" : \"%s\" }", name, game.name, account.email);
	}
}