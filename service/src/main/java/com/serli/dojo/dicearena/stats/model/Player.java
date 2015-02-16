package com.serli.dojo.dicearena.stats.model;

/**
 * Class dedicated to carry player information.
 * 
 * @author Laurent
 */
public class Player implements Stat {

	public static final String TYPE = "player";

	private String name;
	private String account;
	private String game;

	public Player() {
		super();
	}

	public Player(String name, String account, String game) {
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
		return String.format("{ \"name\" : \"%s\", \"game\" : \"%s\", \"account\" : \"%s\" }", name, game, account);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getGame() {
		return game;
	}

	public void setGame(String game) {
		this.game = game;
	}

}