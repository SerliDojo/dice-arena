package com.serli.dojo.arena.dice;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Dealer {

	public static final Logger LOGGER = LoggerFactory.getLogger(Dealer.class);

	public static <A extends Action, M extends Match<A>, P extends Player<A, M>, G extends Game<A, M>> M play(G game, List<P> players) {
		Map<String, P> playersByName = players.stream().collect(Collectors.toMap(Player::getName, Function.identity()));
		M match = game.init(playersByName.keySet().stream().collect(Collectors.toList()));
		while (!match.isFinished()) {
			LOGGER.info("Current state: {}", match);
			A action = playersByName.get(match.getCurrentPlayer()).play(match);
			LOGGER.info("{} choose to {}", match.getCurrentPlayer(), action);
			match = game.apply(action , match);
		}
		LOGGER.info("Winners: {}", match.getWinners());
		LOGGER.info("Final state: {}", match);
		return match;
	}

	private Dealer() {
	}
}
