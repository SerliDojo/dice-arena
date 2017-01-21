package com.serli.dojo.arena.dice.yahtzee;

import org.junit.Test;

import static org.junit.Assert.*;

public class YahtzeeDiceTest {

	@Test
	public void testRoll() {
		YahtzeeDice dice = YahtzeeDice.roll();
		assertNotNull(dice);
		assertNotNull(dice.dice);
		assertEquals(5, dice.dice.length);
	}

	@Test
	public void testRollAndRoll() {
		YahtzeeDice dice1 = YahtzeeDice.roll();
		assertNotNull(dice1);

		YahtzeeDice dice2 = dice1.rolling(0, 1, 2, 3, 4);
		assertNotNull(dice2);

		assertNotEquals(YahtzeeLine.CHANCE.score.apply(dice1), YahtzeeLine.CHANCE.score.apply(dice2));
	}

	@Test
	public void testRollAndRollOne() {
		YahtzeeDice dice1 = YahtzeeDice.roll();
		assertNotNull(dice1);

		YahtzeeDice dice2 = dice1.rolling(0);
		assertNotNull(dice2);

		assertNotEquals(dice1.dice[0], dice2.dice[0]);
		assertEquals(dice1.dice[1], dice2.dice[1]);
		assertEquals(dice1.dice[2], dice2.dice[2]);
		assertEquals(dice1.dice[3], dice2.dice[3]);
		assertEquals(dice1.dice[4], dice2.dice[4]);
	}

	@Test
	public void testMultipleRoll() {
		YahtzeeDice dice1 = YahtzeeDice.roll();
		assertNotNull(dice1);

		YahtzeeDice dice2 = YahtzeeDice.roll();
		assertNotNull(dice2);

		assertNotEquals(dice1.dice, dice2.dice);
	}

}
