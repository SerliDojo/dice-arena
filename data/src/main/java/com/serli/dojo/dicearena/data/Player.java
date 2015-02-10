package com.serli.dojo.dicearena.data;

/**
 * Class dedicated to carry player information.
 * 
 * @author Laurent
 */
public class Player implements Entity {

	public static final String TYPE = "player";
	public static final String MAPPING = "{ \"player\": { \"_timestamp\" : { \"enabled\": \"true\", \"store\": \"yes\" }, \"properties\": { \"name\": { \"type\": \"string\" } \"game\": { \"type\": \"string\" }, \"account\": { \"type\": \"string\" } } }}";

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