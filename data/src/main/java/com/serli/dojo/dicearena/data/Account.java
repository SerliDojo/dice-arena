package com.serli.dojo.dicearena.data;

import java.time.LocalDate;

/**
 * Class dedicated to carry account information, including the of players
 * registered by this account.
 * 
 * @author Laurent
 */
public class Account implements Entity {

	public static final String TYPE = "account";

	public String name, email, locations;
	public LocalDate subscription;

	public Account(String name, String email, String locations, LocalDate subscription) {
		this.name = name;
		this.email = email;
		this.locations = locations;
		this.subscription = subscription;
	}

	@Override
	public String toJsonString() {
		return String.format("{ \"index\" : { \"_index\" : \"%s\", \"_type\" : \"%s\", \"_id\" : \"%s\" } }\n{ \"email\" : \"%s\", \"location\" : [%s], \"subscription\" : \"%s\" }",
				INDEX, TYPE, email, email, locations, subscription);
	}
}