package com.serli.dojo.arena.dice.poker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class PokerDiceTest {

	@Test
	public void testRoll() {
		PokerDice dice = PokerDice.roll();
		assertNotNull(dice);
		assertNotNull(dice.dice);
		assertEquals(5, dice.dice.length);
	}

	@Test
	public void testRollAndRoll() {
		PokerDice dice1 = PokerDice.roll();
		assertNotNull(dice1);

		PokerDice dice2 = dice1.rolling(0, 1, 2, 3, 4);
		assertNotNull(dice2);

		assertNotEquals(PokerHand.bestScore(dice1), PokerHand.bestScore(dice2));
	}

	@Test
	public void testRollAndRollOne() {
		PokerDice dice1 = PokerDice.roll();
		assertNotNull(dice1);

		PokerDice dice2 = dice1.rolling(0);
		assertNotNull(dice2);

		assertNotEquals(dice1.dice[0], dice2.dice[0]);
		assertEquals(dice1.dice[1], dice2.dice[1]);
		assertEquals(dice1.dice[2], dice2.dice[2]);
		assertEquals(dice1.dice[3], dice2.dice[3]);
		assertEquals(dice1.dice[4], dice2.dice[4]);
	}

	@Test
	public void testMultipleRoll() {
		PokerDice dice1 = PokerDice.roll();
		assertNotNull(dice1);

		PokerDice dice2 = PokerDice.roll();
		assertNotNull(dice2);

		assertNotEquals(dice1.dice, dice2.dice);
	}

}
