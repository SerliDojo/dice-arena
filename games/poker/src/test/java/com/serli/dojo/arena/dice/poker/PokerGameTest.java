package com.serli.dojo.arena.dice.poker;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.serli.dojo.arena.dice.Dealer;

public class PokerGameTest {

	@Test
	public void testRun() {
		List<PokerPlayer> players = Arrays.asList(
				new PokerPlayerHolding("John"),
				new PokerPlayerRolling("Bill"),
				new PokerPlayerHoldingOverOnePair("Gary"));
		PokerGame game = new PokerGame("Test game");

		PokerMatch match = Dealer.play(game, players);

		Assert.assertNotNull(match);
		Assert.assertNotNull(match.scores);
		Assert.assertEquals(match.scores.size(), 3);
		Assert.assertTrue(match.scores.values().stream().anyMatch(v -> v >= 100));
	}
}
