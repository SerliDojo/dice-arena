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
	public static final String MAPPING = "{ \"account\": { \"_timestamp\" : { \"enabled\": \"true\", \"store\": \"yes\" }, \"properties\": { \"email\": { \"analyzer\": \"uax_email_url\", \"type\": \"email\" }, \"location\": { \"type\": \"double\" }, \"subscription\": { \"type\": \"date\", \"format\": \"dateOptionalTime\" }, \"players\": { \"analyzer\": \"keyword\", \"type\": \"string\" } } }}";

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