package com.serli.dojo.arena.dice.yahtzee;

import com.serli.dojo.arena.dice.Dice;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.IntStream;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

public class YahtzeeLineTest {

    @Test
    public void testScoreAces() {
        Assert.assertThat(YahtzeeLine.ACES.score.apply(new Dice(1, 1, 1, 1, 1)), CoreMatchers.equalTo(5));
        Assert.assertThat(YahtzeeLine.ACES.score.apply(new Dice(1, 1, 1, 2, 1)), CoreMatchers.equalTo(4));
        Assert.assertThat(YahtzeeLine.ACES.score.apply(new Dice(1, 2, 1, 2, 1)), CoreMatchers.equalTo(3));
        Assert.assertThat(YahtzeeLine.ACES.score.apply(new Dice(1, 2, 2, 2, 1)), CoreMatchers.equalTo(2));
        Assert.assertThat(YahtzeeLine.ACES.score.apply(new Dice(2, 2, 2, 2, 1)), CoreMatchers.equalTo(1));
        Assert.assertThat(YahtzeeLine.ACES.score.apply(new Dice(2, 2, 2, 2, 2)), CoreMatchers.equalTo(0));
        Assert.assertThat(sumAllCombinations(YahtzeeLine.ACES.score), CoreMatchers.equalTo((5 + 5*5*4 + 25*10*3 + 125*10*2 + 625*5) * 1));
    }

    @Test
    public void testScoreTwos() {
        Assert.assertThat(YahtzeeLine.TWOS.score.apply(new Dice(2, 2, 2, 2, 2)), CoreMatchers.equalTo(10));
        Assert.assertThat(YahtzeeLine.TWOS.score.apply(new Dice(2, 2, 2, 3, 2)), CoreMatchers.equalTo(8));
        Assert.assertThat(YahtzeeLine.TWOS.score.apply(new Dice(2, 3, 2, 3, 2)), CoreMatchers.equalTo(6));
        Assert.assertThat(YahtzeeLine.TWOS.score.apply(new Dice(2, 3, 3, 3, 2)), CoreMatchers.equalTo(4));
        Assert.assertThat(YahtzeeLine.TWOS.score.apply(new Dice(3, 3, 3, 3, 2)), CoreMatchers.equalTo(2));
        Assert.assertThat(YahtzeeLine.TWOS.score.apply(new Dice(3, 3, 3, 3, 3)), CoreMatchers.equalTo(0));
        Assert.assertThat(sumAllCombinations(YahtzeeLine.TWOS.score), CoreMatchers.equalTo((5 + 5*5*4 + 25*10*3 + 125*10*2 + 625*5) * 2));
    }

    @Test
    public void testScoreThrees() {
        Assert.assertThat(YahtzeeLine.THREES.score.apply(new Dice(3, 3, 3, 3, 3)), CoreMatchers.equalTo(15));
        Assert.assertThat(YahtzeeLine.THREES.score.apply(new Dice(3, 3, 3, 4, 3)), CoreMatchers.equalTo(12));
        Assert.assertThat(YahtzeeLine.THREES.score.apply(new Dice(3, 4, 3, 4, 3)), CoreMatchers.equalTo(9));
        Assert.assertThat(YahtzeeLine.THREES.score.apply(new Dice(3, 4, 4, 4, 3)), CoreMatchers.equalTo(6));
        Assert.assertThat(YahtzeeLine.THREES.score.apply(new Dice(4, 4, 4, 4, 3)), CoreMatchers.equalTo(3));
        Assert.assertThat(YahtzeeLine.THREES.score.apply(new Dice(4, 4, 4, 4, 4)), CoreMatchers.equalTo(0));
        Assert.assertThat(sumAllCombinations(YahtzeeLine.THREES.score), CoreMatchers.equalTo((5 + 5*5*4 + 25*10*3 + 125*10*2 + 625*5) * 3));
    }

    @Test
    public void testScoreFours() {
        Assert.assertThat(YahtzeeLine.FOURS.score.apply(new Dice(4, 4, 4, 4, 4)), CoreMatchers.equalTo(20));
        Assert.assertThat(YahtzeeLine.FOURS.score.apply(new Dice(4, 4, 4, 5, 4)), CoreMatchers.equalTo(16));
        Assert.assertThat(YahtzeeLine.FOURS.score.apply(new Dice(4, 5, 4, 5, 4)), CoreMatchers.equalTo(12));
        Assert.assertThat(YahtzeeLine.FOURS.score.apply(new Dice(4, 5, 5, 5, 4)), CoreMatchers.equalTo(8));
        Assert.assertThat(YahtzeeLine.FOURS.score.apply(new Dice(5, 5, 5, 5, 4)), CoreMatchers.equalTo(4));
        Assert.assertThat(YahtzeeLine.FOURS.score.apply(new Dice(5, 5, 5, 5, 5)), CoreMatchers.equalTo(0));
        Assert.assertThat(sumAllCombinations(YahtzeeLine.FOURS.score), CoreMatchers.equalTo((5 + 5*5*4 + 25*10*3 + 125*10*2 + 625*5) * 4));
    }

    @Test
    public void testScoreFives() {
        Assert.assertThat(YahtzeeLine.FIVES.score.apply(new Dice(5, 5, 5, 5, 5)), CoreMatchers.equalTo(25));
        Assert.assertThat(YahtzeeLine.FIVES.score.apply(new Dice(5, 5, 5, 6, 5)), CoreMatchers.equalTo(20));
        Assert.assertThat(YahtzeeLine.FIVES.score.apply(new Dice(5, 6, 5, 6, 5)), CoreMatchers.equalTo(15));
        Assert.assertThat(YahtzeeLine.FIVES.score.apply(new Dice(5, 6, 6, 6, 5)), CoreMatchers.equalTo(10));
        Assert.assertThat(YahtzeeLine.FIVES.score.apply(new Dice(6, 6, 6, 6, 5)), CoreMatchers.equalTo(5));
        Assert.assertThat(YahtzeeLine.FIVES.score.apply(new Dice(6, 6, 6, 6, 6)), CoreMatchers.equalTo(0));
        Assert.assertThat(sumAllCombinations(YahtzeeLine.FIVES.score), CoreMatchers.equalTo((5 + 5*5*4 + 25*10*3 + 125*10*2 + 625*5) * 5));
    }

    @Test
    public void testScoreSixes() {
        Assert.assertThat(YahtzeeLine.SIXES.score.apply(new Dice(6, 6, 6, 6, 6)), CoreMatchers.equalTo(30));
        Assert.assertThat(YahtzeeLine.SIXES.score.apply(new Dice(6, 6, 6, 1, 6)), CoreMatchers.equalTo(24));
        Assert.assertThat(YahtzeeLine.SIXES.score.apply(new Dice(6, 1, 6, 1, 6)), CoreMatchers.equalTo(18));
        Assert.assertThat(YahtzeeLine.SIXES.score.apply(new Dice(6, 1, 1, 1, 6)), CoreMatchers.equalTo(12));
        Assert.assertThat(YahtzeeLine.SIXES.score.apply(new Dice(1, 1, 1, 1, 6)), CoreMatchers.equalTo(6));
        Assert.assertThat(YahtzeeLine.SIXES.score.apply(new Dice(1, 1, 1, 1, 1)), CoreMatchers.equalTo(0));
        Assert.assertThat(sumAllCombinations(YahtzeeLine.SIXES.score), CoreMatchers.equalTo((5 + 5*5*4 + 25*10*3 + 125*10*2 + 625*5) * 6));
    }

    @Test
    public void testScoreFullHouse() {
        Assert.assertThat(YahtzeeLine.FULL_HOUSE.score.apply(new Dice(6, 6, 6, 6, 6)), CoreMatchers.equalTo(0));
        Assert.assertThat(YahtzeeLine.FULL_HOUSE.score.apply(new Dice(6, 6, 6, 1, 6)), CoreMatchers.equalTo(0));
        Assert.assertThat(YahtzeeLine.FULL_HOUSE.score.apply(new Dice(6, 1, 6, 1, 6)), CoreMatchers.equalTo(25));
        Assert.assertThat(YahtzeeLine.FULL_HOUSE.score.apply(new Dice(6, 1, 1, 1, 6)), CoreMatchers.equalTo(25));
        Assert.assertThat(YahtzeeLine.FULL_HOUSE.score.apply(new Dice(1, 1, 1, 1, 6)), CoreMatchers.equalTo(0));
        Assert.assertThat(YahtzeeLine.FULL_HOUSE.score.apply(new Dice(1, 1, 1, 1, 1)), CoreMatchers.equalTo(0));
        Assert.assertThat(sumAllCombinations(YahtzeeLine.FULL_HOUSE.score), CoreMatchers.equalTo(6 * 10 * 5 * 25));
    }

    @Test
    public void testScoreThreeOfAKind() {
        Assert.assertThat(YahtzeeLine.THREE_OF_A_KIND.score.apply(new Dice(6, 6, 6, 6, 6)), CoreMatchers.equalTo(30));
        Assert.assertThat(YahtzeeLine.THREE_OF_A_KIND.score.apply(new Dice(6, 6, 6, 1, 6)), CoreMatchers.equalTo(25));
        Assert.assertThat(YahtzeeLine.THREE_OF_A_KIND.score.apply(new Dice(6, 1, 6, 1, 6)), CoreMatchers.equalTo(20));
        Assert.assertThat(YahtzeeLine.THREE_OF_A_KIND.score.apply(new Dice(6, 1, 1, 1, 6)), CoreMatchers.equalTo(15));
        Assert.assertThat(YahtzeeLine.THREE_OF_A_KIND.score.apply(new Dice(1, 1, 1, 1, 6)), CoreMatchers.equalTo(10));
        Assert.assertThat(YahtzeeLine.THREE_OF_A_KIND.score.apply(new Dice(1, 1, 1, 1, 1)), CoreMatchers.equalTo(5));
        Assert.assertThat(sumAllCombinations(YahtzeeLine.THREE_OF_A_KIND.score), CoreMatchers.equalTo(
                IntStream.range(1,7).map(mainDie -> 5 * (20*mainDie + 21)
                        + IntStream.range(1,7).filter(i -> i != mainDie).flatMap(dieOne ->
                        IntStream.range(1,7).filter(i -> i != mainDie).map(dieTwo ->
                                10 * (dieOne + dieTwo + 3 * mainDie))).sum()).sum()));
    }

    @Test
    public void testScoreFourOfAKind() {
        Assert.assertThat(YahtzeeLine.FOUR_OF_A_KIND.score.apply(new Dice(6, 6, 6, 6, 6)), CoreMatchers.equalTo(30));
        Assert.assertThat(YahtzeeLine.FOUR_OF_A_KIND.score.apply(new Dice(6, 6, 6, 1, 6)), CoreMatchers.equalTo(25));
        Assert.assertThat(YahtzeeLine.FOUR_OF_A_KIND.score.apply(new Dice(6, 1, 6, 1, 6)), CoreMatchers.equalTo(0));
        Assert.assertThat(YahtzeeLine.FOUR_OF_A_KIND.score.apply(new Dice(6, 1, 1, 1, 6)), CoreMatchers.equalTo(0));
        Assert.assertThat(YahtzeeLine.FOUR_OF_A_KIND.score.apply(new Dice(1, 1, 1, 1, 6)), CoreMatchers.equalTo(10));
        Assert.assertThat(YahtzeeLine.FOUR_OF_A_KIND.score.apply(new Dice(1, 1, 1, 1, 1)), CoreMatchers.equalTo(5));
        Assert.assertThat(sumAllCombinations(YahtzeeLine.FOUR_OF_A_KIND.score), CoreMatchers.equalTo(
                IntStream.range(1,7).map(i -> 5 * (20*i + 21)).sum()));
    }

    @Test
    public void testScoreYahtzee() {
        Assert.assertThat(YahtzeeLine.YAHTZEE.score.apply(new Dice(6, 6, 6, 6, 6)), CoreMatchers.equalTo(50));
        Assert.assertThat(YahtzeeLine.YAHTZEE.score.apply(new Dice(6, 6, 6, 1, 6)), CoreMatchers.equalTo(0));
        Assert.assertThat(YahtzeeLine.YAHTZEE.score.apply(new Dice(6, 1, 6, 1, 6)), CoreMatchers.equalTo(0));
        Assert.assertThat(YahtzeeLine.YAHTZEE.score.apply(new Dice(6, 1, 1, 1, 6)), CoreMatchers.equalTo(0));
        Assert.assertThat(YahtzeeLine.YAHTZEE.score.apply(new Dice(1, 1, 1, 1, 6)), CoreMatchers.equalTo(0));
        Assert.assertThat(YahtzeeLine.YAHTZEE.score.apply(new Dice(1, 1, 1, 1, 1)), CoreMatchers.equalTo(50));
        Assert.assertThat(sumAllCombinations(YahtzeeLine.YAHTZEE.score), CoreMatchers.equalTo(6*50));
    }

    @Test
    public void testScoreSmallStraight() {
        Assert.assertThat(YahtzeeLine.SMALL_STRAIGHT.score.apply(new Dice(6, 6, 6, 6, 6)), CoreMatchers.equalTo(0));
        Assert.assertThat(YahtzeeLine.SMALL_STRAIGHT.score.apply(new Dice(6, 6, 6, 1, 6)), CoreMatchers.equalTo(0));
        Assert.assertThat(YahtzeeLine.SMALL_STRAIGHT.score.apply(new Dice(6, 1, 6, 1, 6)), CoreMatchers.equalTo(0));
        Assert.assertThat(YahtzeeLine.SMALL_STRAIGHT.score.apply(new Dice(6, 1, 1, 1, 6)), CoreMatchers.equalTo(0));
        Assert.assertThat(YahtzeeLine.SMALL_STRAIGHT.score.apply(new Dice(1, 1, 1, 1, 6)), CoreMatchers.equalTo(0));
        Assert.assertThat(YahtzeeLine.SMALL_STRAIGHT.score.apply(new Dice(1, 1, 1, 1, 1)), CoreMatchers.equalTo(0));
        Assert.assertThat(YahtzeeLine.SMALL_STRAIGHT.score.apply(new Dice(1, 2, 3, 5, 6)), CoreMatchers.equalTo(0));
        Assert.assertThat(YahtzeeLine.SMALL_STRAIGHT.score.apply(new Dice(1, 2, 3, 6, 6)), CoreMatchers.equalTo(0));
        Assert.assertThat(YahtzeeLine.SMALL_STRAIGHT.score.apply(new Dice(1, 2, 3, 4, 6)), CoreMatchers.equalTo(30));
        Assert.assertThat(YahtzeeLine.SMALL_STRAIGHT.score.apply(new Dice(6, 3, 4, 5, 6)), CoreMatchers.equalTo(30));
        Assert.assertThat(YahtzeeLine.SMALL_STRAIGHT.score.apply(new Dice(1, 2, 3, 4, 5)), CoreMatchers.equalTo(30));
        Assert.assertThat(YahtzeeLine.SMALL_STRAIGHT.score.apply(new Dice(2, 3, 4, 5, 6)), CoreMatchers.equalTo(30));
        Assert.assertThat(sumAllCombinations(YahtzeeLine.SMALL_STRAIGHT.score), CoreMatchers.equalTo(5*4*3*2*1 * 2 * 5 * 30));
    }

    @Test
    public void testScoreLargeStraight() {
        Assert.assertThat(YahtzeeLine.LARGE_STRAIGHT.score.apply(new Dice(6, 6, 6, 6, 6)), CoreMatchers.equalTo(0));
        Assert.assertThat(YahtzeeLine.LARGE_STRAIGHT.score.apply(new Dice(6, 6, 6, 1, 6)), CoreMatchers.equalTo(0));
        Assert.assertThat(YahtzeeLine.LARGE_STRAIGHT.score.apply(new Dice(6, 1, 6, 1, 6)), CoreMatchers.equalTo(0));
        Assert.assertThat(YahtzeeLine.LARGE_STRAIGHT.score.apply(new Dice(6, 1, 1, 1, 6)), CoreMatchers.equalTo(0));
        Assert.assertThat(YahtzeeLine.LARGE_STRAIGHT.score.apply(new Dice(1, 1, 1, 1, 6)), CoreMatchers.equalTo(0));
        Assert.assertThat(YahtzeeLine.LARGE_STRAIGHT.score.apply(new Dice(1, 1, 1, 1, 1)), CoreMatchers.equalTo(0));
        Assert.assertThat(YahtzeeLine.LARGE_STRAIGHT.score.apply(new Dice(1, 2, 3, 4, 5)), CoreMatchers.equalTo(40));
        Assert.assertThat(YahtzeeLine.LARGE_STRAIGHT.score.apply(new Dice(2, 3, 4, 5, 6)), CoreMatchers.equalTo(40));
        Assert.assertThat(sumAllCombinations(YahtzeeLine.LARGE_STRAIGHT.score), CoreMatchers.equalTo(5*4*3*2*1 * 2 * 40));
    }

    @Test
    public void testScoreChance() {
        Assert.assertThat(YahtzeeLine.CHANCE.score.apply(new Dice(6, 6, 6, 6, 6)), CoreMatchers.equalTo(30));
        Assert.assertThat(YahtzeeLine.CHANCE.score.apply(new Dice(6, 6, 6, 1, 6)), CoreMatchers.equalTo(25));
        Assert.assertThat(YahtzeeLine.CHANCE.score.apply(new Dice(6, 1, 6, 1, 6)), CoreMatchers.equalTo(20));
        Assert.assertThat(YahtzeeLine.CHANCE.score.apply(new Dice(6, 1, 1, 1, 6)), CoreMatchers.equalTo(15));
        Assert.assertThat(YahtzeeLine.CHANCE.score.apply(new Dice(1, 1, 1, 1, 6)), CoreMatchers.equalTo(10));
        Assert.assertThat(YahtzeeLine.CHANCE.score.apply(new Dice(1, 1, 1, 1, 1)), CoreMatchers.equalTo(5));
        Assert.assertThat(sumAllCombinations(YahtzeeLine.CHANCE.score), CoreMatchers.equalTo(
                IntStream.range(1,7).flatMap(dieOne ->
                        IntStream.range(1,7).flatMap(dieTwo ->
                                IntStream.range(1,7).flatMap(dieThree ->
                                        IntStream.range(1,7).flatMap(dieFour ->
                                                IntStream.range(1,7).map(dieFive ->
                                                        dieOne + dieTwo + dieThree + dieFour + dieFive))))).sum()));
    }

    public int sumAllCombinations(Function<Dice, Integer> score) {
        return IntStream.range(1,7).flatMap(dieOne ->
            IntStream.range(1,7).flatMap(dieTwo ->
                IntStream.range(1,7).flatMap(dieThree ->
                    IntStream.range(1,7).flatMap(dieFour ->
                        IntStream.range(1,7).map(dieFive -> {
                            Integer computed = score.apply(new Dice(dieOne, dieTwo, dieThree, dieFour, dieFive));
//                            System.out.println(String.format("[%s, %s, %s, %s, %s] = %s", dieOne, dieTwo, dieThree, dieFour, dieFive, computed));
                            return computed.intValue();
                        })
//                    .map(i -> i > 0 ? 1 : 0)
                    )))).sum();
    }
}
