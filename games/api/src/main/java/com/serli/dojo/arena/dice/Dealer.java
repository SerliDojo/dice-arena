package com.serli.dojo.arena.dice;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Dealer {

	public static final Logger LOGGER = LoggerFactory.getLogger(Dealer.class);

	public static <A extends Action, M extends Match<A>, P extends Player<A, M>, G extends Game<A, M, P>> M play(G game, List<P> players) {
		M match = game.init(players);
		while (!match.isFinished()) {
			LOGGER.info("Current state: {}", match);
			A action = match.currentPlayer().play(match);
			LOGGER.info("{} choose to {}", match.currentPlayer().name(), action);
			match = game.apply(action , match);
		}
		LOGGER.info("Final state: {}", match);
		return match;
	}

	private Dealer() {
	}
}
