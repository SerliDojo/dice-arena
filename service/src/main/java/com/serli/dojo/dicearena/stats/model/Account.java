package com.serli.dojo.dicearena.stats.model;

import java.time.LocalDate;

/**
 * Class dedicated to carry account information, including the of players
 * registered by this account.
 * 
 * @author Laurent
 */
public class Account implements Stat {

	public static final String TYPE = "account";

	public String email, locations;
	public LocalDate subscription;

	public Account(String email, String locations, LocalDate subscription) {
		this.email = email;
		this.locations = locations;
		this.subscription = subscription;
	}

	@Override
	public String getType() {
		return TYPE;
	}

	@Override
	public String getId() {
		return email;
	}

	@Override
	public String toJsonString() {
		return String.format("{ \"_timestamp\": \"%s\", \"email\" : \"%s\", \"location\" : [%s], \"subscription\" : \"%s\" }", 
				subscription, email, locations, subscription);
	}
}