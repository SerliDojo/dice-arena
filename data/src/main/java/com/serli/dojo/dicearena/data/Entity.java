package com.serli.dojo.dicearena.data;

public interface Entity {

	final String INDEX = "engine";

	String toIndexString();

	String toJsonString();
	
	default String toBulkString() {
		return toIndexString() + "\n" + toJsonString();
	}
}