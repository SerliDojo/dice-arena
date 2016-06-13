package com.serli.dojo.arena.dice;

import java.util.List;

public interface Engine<A extends Action, S extends State, P extends Player<A, S>> {

	S init(List<P> players);

	boolean isFinished(S state);

	S step(S state);

}