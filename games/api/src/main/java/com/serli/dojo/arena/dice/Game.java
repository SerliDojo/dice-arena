package com.serli.dojo.arena.dice;

import java.util.List;

public interface Game<A extends Action, M extends Match<A>> {

	String getName();

	M init(List<String> players);

	M apply(A action, M match);

}