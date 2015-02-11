package com.serli.dojo.dicearena.data;

import java.io.File;
import java.io.IOException;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

public interface Entity {

	final String INDEX = "arena-stats";

	default String getIndex() {
		return INDEX;
	}

	static String readMapping(String type) throws IOException {
		return Files.toString(new File("./src/main/resources/mapping/" + type + ".json"), Charsets.UTF_8);
	}

	String getType();

	String getId();

	String toJsonString();
}