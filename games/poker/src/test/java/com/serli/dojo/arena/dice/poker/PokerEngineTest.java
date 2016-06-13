package com.serli.dojo.arena.dice.poker;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.serli.dojo.arena.dice.Match;

public class PokerEngineTest {

	@Test
	public void testRun() {
		List<PokerPlayer> players = Arrays.asList(
				new PokerPlayerHolding("John"),
				new PokerPlayerRolling("Bill"),
				new PokerPlayerHoldingOverOnePair("Gary"));
		PokerEngine engine = new PokerEngine();

		PokerState state = Match.play(engine, players);

		Assert.assertNotNull(state);
		Assert.assertNotNull(state.scores);
		Assert.assertEquals(state.scores.size(), 3);
		Assert.assertTrue(state.scores.values().stream().anyMatch(v -> v >= 100));
	}
}
