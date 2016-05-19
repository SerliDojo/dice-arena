package com.serli.dojo.arena.dice;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Match {

	public static final Logger LOGGER = LoggerFactory.getLogger(Match.class);

	public static <A extends Action, S extends State, P extends Player<A, S>, E extends Engine<A, S, P>> S play(E engine, List<P> players) {
		S state = engine.init(players);
		while (!engine.isFinished(state)) {
			LOGGER.debug("Stepping");
			LOGGER.info("Current state: {}", state);
			state = engine.step(state);
		}
		LOGGER.info("Final state: {}", state);
		return state;
	}

	private Match() {
	}
}
