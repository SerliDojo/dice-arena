package com.serli.dojo.dicearena.data;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Class dedicated to carry account information, including the of players
 * registered by this account.
 * 
 * @author Laurent
 */
public class Account {

	public String name, email, locations;
	public LocalDate subscription;
	public Map<String, Game> players = new HashMap<>();

	public Account(String name, String email, String locations, LocalDate subscription) {
		this.name = name;
		this.email = email;
		this.locations = locations;
		this.subscription = subscription;
	}

	public String toJsonString() {
		String playersString = players.entrySet().stream().map(player -> {
			return String.format("{ \"name\" : \"%s\", \"game\" : [%s] }", player.getKey(), player.getValue().name);
		}).collect(Collectors.joining(", "));

		return String.format("{ \"email\" : \"%s\", \"location\" : [%s], \"subscription\" : \"%s\", \"players\" : [ %s ] }",
				email, locations, subscription, playersString);
	}
}