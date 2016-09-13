package com.serli.dojo.arena.dice;

import java.util.Collection;

public interface Match<A extends Action> {

	boolean isFinished();

	String getCurrentPlayer();

	Collection<String> getWinners();

}