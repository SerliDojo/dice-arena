package com.serli.dojo.arena.dice.poker;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class PokerHandTest {

	@Test
	public void test12345() {
		PokerDice dice = new PokerDice(1, 2, 3, 4, 5);

		assertThat(PokerHand.HIGH_DIE.apply.test(dice), equalTo(true));
		assertThat(PokerHand.HIGH_DIE.score.apply(dice), equalTo(5));
		assertThat(PokerHand.ONE_PAIR.apply.test(dice), equalTo(false));
		assertThat(PokerHand.ONE_PAIR.score.apply(dice), equalTo(0));
		assertThat(PokerHand.TWO_PAIRS.apply.test(dice), equalTo(false));
		assertThat(PokerHand.TWO_PAIRS.score.apply(dice), equalTo(0));
		assertThat(PokerHand.THREE_OF_A_KIND.apply.test(dice), equalTo(false));
		assertThat(PokerHand.THREE_OF_A_KIND.score.apply(dice), equalTo(0));
		assertThat(PokerHand.FOUR_OF_A_KIND.apply.test(dice), equalTo(false));
		assertThat(PokerHand.FOUR_OF_A_KIND.score.apply(dice), equalTo(0));
		assertThat(PokerHand.FULL_HOUSE.apply.test(dice), equalTo(false));
		assertThat(PokerHand.FULL_HOUSE.score.apply(dice), equalTo(0));
		assertThat(PokerHand.STRAIGHT.apply.test(dice), equalTo(true));
		assertThat(PokerHand.STRAIGHT.score.apply(dice), equalTo(60));

		assertThat(PokerHand.bestHand(dice), equalTo(PokerHand.STRAIGHT));
	}

	@Test
	public void test23456() {
		PokerDice dice = new PokerDice(2, 3, 4, 5, 6);

		assertThat(PokerHand.HIGH_DIE.apply.test(dice), equalTo(true));
		assertThat(PokerHand.HIGH_DIE.score.apply(dice), equalTo(6));
		assertThat(PokerHand.ONE_PAIR.apply.test(dice), equalTo(false));
		assertThat(PokerHand.ONE_PAIR.score.apply(dice), equalTo(0));
		assertThat(PokerHand.TWO_PAIRS.apply.test(dice), equalTo(false));
		assertThat(PokerHand.TWO_PAIRS.score.apply(dice), equalTo(0));
		assertThat(PokerHand.THREE_OF_A_KIND.apply.test(dice), equalTo(false));
		assertThat(PokerHand.THREE_OF_A_KIND.score.apply(dice), equalTo(0));
		assertThat(PokerHand.FOUR_OF_A_KIND.apply.test(dice), equalTo(false));
		assertThat(PokerHand.FOUR_OF_A_KIND.score.apply(dice), equalTo(0));
		assertThat(PokerHand.FULL_HOUSE.apply.test(dice), equalTo(false));
		assertThat(PokerHand.FULL_HOUSE.score.apply(dice), equalTo(0));
		assertThat(PokerHand.STRAIGHT.apply.test(dice), equalTo(true));
		assertThat(PokerHand.STRAIGHT.score.apply(dice), equalTo(70));

		assertThat(PokerHand.bestHand(dice), equalTo(PokerHand.STRAIGHT));
	}

	@Test
	public void test12356() {
		PokerDice dice = new PokerDice(1, 2, 3, 5, 6);

		assertThat(PokerHand.HIGH_DIE.apply.test(dice), equalTo(true));
		assertThat(PokerHand.HIGH_DIE.score.apply(dice), equalTo(6));
		assertThat(PokerHand.ONE_PAIR.apply.test(dice), equalTo(false));
		assertThat(PokerHand.ONE_PAIR.score.apply(dice), equalTo(0));
		assertThat(PokerHand.TWO_PAIRS.apply.test(dice), equalTo(false));
		assertThat(PokerHand.TWO_PAIRS.score.apply(dice), equalTo(0));
		assertThat(PokerHand.THREE_OF_A_KIND.apply.test(dice), equalTo(false));
		assertThat(PokerHand.THREE_OF_A_KIND.score.apply(dice), equalTo(0));
		assertThat(PokerHand.FOUR_OF_A_KIND.apply.test(dice), equalTo(false));
		assertThat(PokerHand.FOUR_OF_A_KIND.score.apply(dice), equalTo(0));
		assertThat(PokerHand.FULL_HOUSE.apply.test(dice), equalTo(false));
		assertThat(PokerHand.FULL_HOUSE.score.apply(dice), equalTo(0));
		assertThat(PokerHand.STRAIGHT.apply.test(dice), equalTo(false));
		assertThat(PokerHand.STRAIGHT.score.apply(dice), equalTo(0));

		assertThat(PokerHand.bestHand(dice), equalTo(PokerHand.HIGH_DIE));
	}

	@Test
	public void test11234() {
		PokerDice dice = new PokerDice(1, 1, 2, 3, 4);

		assertThat(PokerHand.HIGH_DIE.apply.test(dice), equalTo(true));
		assertThat(PokerHand.HIGH_DIE.score.apply(dice), equalTo(4));
		assertThat(PokerHand.ONE_PAIR.apply.test(dice), equalTo(true));
		assertThat(PokerHand.ONE_PAIR.score.apply(dice), equalTo(10));
		assertThat(PokerHand.TWO_PAIRS.apply.test(dice), equalTo(false));
		assertThat(PokerHand.TWO_PAIRS.score.apply(dice), equalTo(0));
		assertThat(PokerHand.THREE_OF_A_KIND.apply.test(dice), equalTo(false));
		assertThat(PokerHand.THREE_OF_A_KIND.score.apply(dice), equalTo(0));
		assertThat(PokerHand.FOUR_OF_A_KIND.apply.test(dice), equalTo(false));
		assertThat(PokerHand.FOUR_OF_A_KIND.score.apply(dice), equalTo(0));
		assertThat(PokerHand.FULL_HOUSE.apply.test(dice), equalTo(false));
		assertThat(PokerHand.FULL_HOUSE.score.apply(dice), equalTo(0));
		assertThat(PokerHand.STRAIGHT.apply.test(dice), equalTo(false));
		assertThat(PokerHand.STRAIGHT.score.apply(dice), equalTo(0));

		assertThat(PokerHand.bestHand(dice), equalTo(PokerHand.ONE_PAIR));
	}

	@Test
	public void test11223() {
		PokerDice dice = new PokerDice(1, 1, 2, 2, 3);

		assertThat(PokerHand.HIGH_DIE.apply.test(dice), equalTo(true));
		assertThat(PokerHand.HIGH_DIE.score.apply(dice), equalTo(3));
		assertThat(PokerHand.ONE_PAIR.apply.test(dice), equalTo(true));
		assertThat(PokerHand.ONE_PAIR.score.apply(dice), equalTo(10));
		assertThat(PokerHand.TWO_PAIRS.apply.test(dice), equalTo(true));
		assertThat(PokerHand.TWO_PAIRS.score.apply(dice), equalTo(20));
		assertThat(PokerHand.THREE_OF_A_KIND.apply.test(dice), equalTo(false));
		assertThat(PokerHand.THREE_OF_A_KIND.score.apply(dice), equalTo(0));
		assertThat(PokerHand.FOUR_OF_A_KIND.apply.test(dice), equalTo(false));
		assertThat(PokerHand.FOUR_OF_A_KIND.score.apply(dice), equalTo(0));
		assertThat(PokerHand.FULL_HOUSE.apply.test(dice), equalTo(false));
		assertThat(PokerHand.FULL_HOUSE.score.apply(dice), equalTo(0));
		assertThat(PokerHand.STRAIGHT.apply.test(dice), equalTo(false));
		assertThat(PokerHand.STRAIGHT.score.apply(dice), equalTo(0));

		assertThat(PokerHand.bestHand(dice), equalTo(PokerHand.TWO_PAIRS));
	}

	@Test
	public void test11123() {
		PokerDice dice = new PokerDice(1, 1, 1, 2, 3);

		assertThat(PokerHand.HIGH_DIE.apply.test(dice), equalTo(true));
		assertThat(PokerHand.HIGH_DIE.score.apply(dice), equalTo(3));
		assertThat(PokerHand.ONE_PAIR.apply.test(dice), equalTo(true));
		assertThat(PokerHand.ONE_PAIR.score.apply(dice), equalTo(10));
		assertThat(PokerHand.TWO_PAIRS.apply.test(dice), equalTo(false));
		assertThat(PokerHand.TWO_PAIRS.score.apply(dice), equalTo(0));
		assertThat(PokerHand.THREE_OF_A_KIND.apply.test(dice), equalTo(true));
		assertThat(PokerHand.THREE_OF_A_KIND.score.apply(dice), equalTo(30));
		assertThat(PokerHand.FOUR_OF_A_KIND.apply.test(dice), equalTo(false));
		assertThat(PokerHand.FOUR_OF_A_KIND.score.apply(dice), equalTo(0));
		assertThat(PokerHand.FULL_HOUSE.apply.test(dice), equalTo(false));
		assertThat(PokerHand.FULL_HOUSE.score.apply(dice), equalTo(0));
		assertThat(PokerHand.STRAIGHT.apply.test(dice), equalTo(false));
		assertThat(PokerHand.STRAIGHT.score.apply(dice), equalTo(0));

		assertThat(PokerHand.bestHand(dice), equalTo(PokerHand.THREE_OF_A_KIND));
	}

	@Test
	public void test11122() {
		PokerDice dice = new PokerDice(1, 1, 1, 2, 2);

		assertThat(PokerHand.HIGH_DIE.apply.test(dice), equalTo(true));
		assertThat(PokerHand.HIGH_DIE.score.apply(dice), equalTo(2));
		assertThat(PokerHand.ONE_PAIR.apply.test(dice), equalTo(true));
		assertThat(PokerHand.ONE_PAIR.score.apply(dice), equalTo(10));
		assertThat(PokerHand.TWO_PAIRS.apply.test(dice), equalTo(true));
		assertThat(PokerHand.TWO_PAIRS.score.apply(dice), equalTo(20));
		assertThat(PokerHand.THREE_OF_A_KIND.apply.test(dice), equalTo(true));
		assertThat(PokerHand.THREE_OF_A_KIND.score.apply(dice), equalTo(30));
		assertThat(PokerHand.FOUR_OF_A_KIND.apply.test(dice), equalTo(false));
		assertThat(PokerHand.FOUR_OF_A_KIND.score.apply(dice), equalTo(0));
		assertThat(PokerHand.FULL_HOUSE.apply.test(dice), equalTo(true));
		assertThat(PokerHand.FULL_HOUSE.score.apply(dice), equalTo(40));
		assertThat(PokerHand.STRAIGHT.apply.test(dice), equalTo(false));
		assertThat(PokerHand.STRAIGHT.score.apply(dice), equalTo(0));

		assertThat(PokerHand.bestHand(dice), equalTo(PokerHand.FULL_HOUSE));
	}

	@Test
	public void test11112() {
		PokerDice dice = new PokerDice(1, 1, 1, 1, 2);

		assertThat(PokerHand.HIGH_DIE.apply.test(dice), equalTo(true));
		assertThat(PokerHand.HIGH_DIE.score.apply(dice), equalTo(2));
		assertThat(PokerHand.ONE_PAIR.apply.test(dice), equalTo(true));
		assertThat(PokerHand.ONE_PAIR.score.apply(dice), equalTo(10));
		assertThat(PokerHand.TWO_PAIRS.apply.test(dice), equalTo(false));
		assertThat(PokerHand.TWO_PAIRS.score.apply(dice), equalTo(0));
		assertThat(PokerHand.THREE_OF_A_KIND.apply.test(dice), equalTo(true));
		assertThat(PokerHand.THREE_OF_A_KIND.score.apply(dice), equalTo(30));
		assertThat(PokerHand.FOUR_OF_A_KIND.apply.test(dice), equalTo(true));
		assertThat(PokerHand.FOUR_OF_A_KIND.score.apply(dice), equalTo(50));
		assertThat(PokerHand.FULL_HOUSE.apply.test(dice), equalTo(false));
		assertThat(PokerHand.FULL_HOUSE.score.apply(dice), equalTo(0));
		assertThat(PokerHand.STRAIGHT.apply.test(dice), equalTo(false));
		assertThat(PokerHand.STRAIGHT.score.apply(dice), equalTo(0));

		assertThat(PokerHand.bestHand(dice), equalTo(PokerHand.FOUR_OF_A_KIND));
	}

	@Test
	public void test66661() {
		PokerDice dice = new PokerDice(6, 6, 6, 6, 1);

		assertThat(PokerHand.HIGH_DIE.apply.test(dice), equalTo(true));
		assertThat(PokerHand.HIGH_DIE.score.apply(dice), equalTo(6));
		assertThat(PokerHand.ONE_PAIR.apply.test(dice), equalTo(true));
		assertThat(PokerHand.ONE_PAIR.score.apply(dice), equalTo(10));
		assertThat(PokerHand.TWO_PAIRS.apply.test(dice), equalTo(false));
		assertThat(PokerHand.TWO_PAIRS.score.apply(dice), equalTo(0));
		assertThat(PokerHand.THREE_OF_A_KIND.apply.test(dice), equalTo(true));
		assertThat(PokerHand.THREE_OF_A_KIND.score.apply(dice), equalTo(30));
		assertThat(PokerHand.FOUR_OF_A_KIND.apply.test(dice), equalTo(true));
		assertThat(PokerHand.FOUR_OF_A_KIND.score.apply(dice), equalTo(50));
		assertThat(PokerHand.FULL_HOUSE.apply.test(dice), equalTo(false));
		assertThat(PokerHand.FULL_HOUSE.score.apply(dice), equalTo(0));
		assertThat(PokerHand.STRAIGHT.apply.test(dice), equalTo(false));
		assertThat(PokerHand.STRAIGHT.score.apply(dice), equalTo(0));

		assertThat(PokerHand.bestHand(dice), equalTo(PokerHand.FOUR_OF_A_KIND));
	}

	@Test
	public void testScores() {
		assertThat(PokerHand.bestScore(new PokerDice(6, 5, 3, 2, 1)), equalTo(6));
		assertThat(PokerHand.bestScore(new PokerDice(6, 6, 3, 2, 1)), equalTo(10));
		assertThat(PokerHand.bestScore(new PokerDice(6, 5, 3, 1, 1)), equalTo(10));
		assertThat(PokerHand.bestScore(new PokerDice(6, 6, 3, 1, 1)), equalTo(20));
		assertThat(PokerHand.bestScore(new PokerDice(6, 3, 3, 1, 1)), equalTo(20));
		assertThat(PokerHand.bestScore(new PokerDice(3, 3, 3, 2, 1)), equalTo(30));
		assertThat(PokerHand.bestScore(new PokerDice(1, 5, 1, 3, 1)), equalTo(30));
		assertThat(PokerHand.bestScore(new PokerDice(3, 3, 3, 1, 1)), equalTo(40));
		assertThat(PokerHand.bestScore(new PokerDice(1, 3, 1, 3, 1)), equalTo(40));
		assertThat(PokerHand.bestScore(new PokerDice(3, 3, 3, 3, 1)), equalTo(50));
		assertThat(PokerHand.bestScore(new PokerDice(1, 3, 1, 1, 1)), equalTo(50));
		assertThat(PokerHand.bestScore(new PokerDice(1, 2, 3, 4, 5)), equalTo(60));
		assertThat(PokerHand.bestScore(new PokerDice(2, 3, 4, 5, 6)), equalTo(70));
	}
}
