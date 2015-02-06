package com.serli.dojo.dicearena.data;

public interface Entity {

	final String INDEX = "arena-stats";

	default String getIndex() {
		return INDEX;
	}

	String getType();

	String getId();

	String toJsonString();
}