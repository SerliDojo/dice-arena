package com.serli.dojo.arena.dice.pig;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.serli.dojo.arena.dice.Dealer;

public class PigGameTest {

	@Test
	public void testRun() {
		List<PigPlayer> players = Arrays.asList(
				new PigPlayerHolding("John"),
				new PigPlayerHoldingAfterFifteenPointsEarned("Bill"),
				new PigPlayerHoldingAfterFiveRolls("Gary"));
		PigGame game = new PigGame("Test Game");

		PigMatch match = Dealer.play(game, players);

		Assert.assertNotNull(match);
		Assert.assertNotNull(match.scores);
		Assert.assertEquals(match.scores.size(), 3);
		Assert.assertTrue(match.scores.values().stream().anyMatch(v -> v >= 100));
	}
}
