package com.serli.dojo.arena.dice;

import java.util.List;

public interface Game<A extends Action, M extends Match<A>, P extends Player<A, M>> {

	M init(List<P> players);

	M apply(A action, M match);

}