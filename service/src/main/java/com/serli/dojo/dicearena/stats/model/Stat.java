package com.serli.dojo.dicearena.stats.model;


public interface Stat {

	String getType();

	String getId();

	String toJsonString();
}