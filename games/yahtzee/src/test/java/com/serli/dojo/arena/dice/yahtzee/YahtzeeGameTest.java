package com.serli.dojo.arena.dice.yahtzee;

import com.serli.dojo.arena.dice.Dealer;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class YahtzeeGameTest {

	@Test
	public void testRun() {
		List<YahtzeePlayer> players = Arrays.asList(
				new YahtzeePlayerRolling("John"),
				new YahtzeePlayerScoringFirstLine("Bill"),
				new YahtzeePlayerScoringHighestLine("Gary"));
		YahtzeeGame game = new YahtzeeGame("Test game");

		YahtzeeMatch match = Dealer.play(game, players);

		Assert.assertNotNull(match);
		Assert.assertNotNull(match.scores);
		Assert.assertEquals(match.scores.size(), 3);
		Assert.assertTrue(match.scores.values().stream().allMatch(YahtzeeCard::full));
	}
}
