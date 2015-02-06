package com.serli.dojo.dicearena.data;

/**
 * Class dedicated to carry game information like description and players count.
 * 
 * @author Laurent
 */
public class Game implements Entity {

	public static final String TYPE = "game";

	public String name, description;
	public Integer minPlayers, maxPlayers;

	public Game(String name, Integer minPlayers, Integer maxPlayers, String description) {
		this.name = name;
		this.minPlayers = minPlayers;
		this.maxPlayers = maxPlayers;
		this.description = description;
	}

	@Override
	public String toIndexString() {
		return String.format("{ \"index\" : { \"_index\" : \"%s\", \"_type\" : \"%s\", \"_id\" : \"%s\" } }", INDEX, TYPE, name);
	}

	public String toJsonString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{ \"name\" : \"").append(name).append("\"");
		if (minPlayers != null) {
			builder.append(", \"minPlayers\" : ").append(minPlayers);
		}
		if (maxPlayers != null) {
			builder.append(", \"maxPlayers\" : ").append(maxPlayers);
		}
		if (description != null && !description.isEmpty()) {
			builder.append(", \"description\" : \"").append(description).append("\"");
		}
		builder.append(" }");
		return builder.toString();
	}
}