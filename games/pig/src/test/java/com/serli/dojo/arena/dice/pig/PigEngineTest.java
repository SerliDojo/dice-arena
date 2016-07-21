package com.serli.dojo.arena.dice.pig;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.serli.dojo.arena.dice.Dealer;

public class PigEngineTest {

	@Test
	public void testRun() {
		List<PigPlayer> players = Arrays.asList(
				new PigPlayerHolding("John"),
				new PigPlayerHoldingAfterFifteenPointsEarned("Bill"),
				new PigPlayerHoldingAfterFiveRolls("Gary"));
		PigEngine engine = new PigEngine();

		PigState state = Dealer.play(engine, players);

		Assert.assertNotNull(state);
		Assert.assertNotNull(state.scores);
		Assert.assertEquals(state.scores.size(), 3);
		Assert.assertTrue(state.scores.values().stream().anyMatch(v -> v >= 100));
	}
}
