package com.serli.dojo.arena.dice;

import java.util.List;

public interface Game<A extends Action, M extends Match, P extends Player<A, M>> {

	M init(List<P> players);

	boolean isFinished(M match);

	M step(M match);

}