package com.serli.dojo.arena.dice;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;
import org.junit.Test;

import static org.junit.Assert.*;

public class DiceTest {

    @Test
    public void testRoll() {
        Dice dice = Dice.roll(5);
        assertNotNull(dice);
        assertNotNull(dice.values);
        assertEquals(5, dice.values.size());
    }

    @Test
    public void testSeveralRolls() {
        Set<Integer> sums = new HashSet<>();
        IntStream.range(0, 100).forEach(i -> sums.add(Dice.roll(5).values.stream().mapToInt(d -> d).sum()));
        assertTrue("Should be more than one single sum rolled", sums.size() > 1);
    }

    @Test
    public void testRollAndRollOne() {
        Dice dice1 = Dice.roll(5);
        assertNotNull(dice1);

        Dice dice2 = dice1.rolling(0);
        assertNotNull(dice2);

        assertEquals(dice1.values.get(1), dice2.values.get(1));
        assertEquals(dice1.values.get(2), dice2.values.get(2));
        assertEquals(dice1.values.get(3), dice2.values.get(3));
        assertEquals(dice1.values.get(4), dice2.values.get(4));

        Set<Integer> firsts = new HashSet<>();
        IntStream.range(0, 100).forEach(i -> firsts.add(dice1.rolling(0).values.get(0)));
        assertTrue("Should be more than one single side rolled",firsts.size() > 1);
    }

}
