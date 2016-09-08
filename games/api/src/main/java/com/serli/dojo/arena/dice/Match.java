package com.serli.dojo.arena.dice;

public interface Match<A extends Action> {

	boolean isFinished();

	String currentPlayer();

}