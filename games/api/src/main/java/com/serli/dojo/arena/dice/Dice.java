package com.serli.dojo.arena.dice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Dice {

    public final List<Integer> values;

    public static Dice roll(int diceCount) {
        return new Dice(IntStream.range(0, diceCount)
                .map(i -> Die.roll())
                .boxed()
                .collect(Collectors.toList()));
    }

    public Dice(List<Integer> values) {
        this.values = Collections.unmodifiableList(values);
    }

    public Dice(Integer... values) {
        this.values = Collections.unmodifiableList(Arrays.asList(values));
    }

    public Dice rolling(Integer... dieIndexes) {
        List<Integer> newDice = new ArrayList<>(values);
        Arrays.stream(dieIndexes)
                .filter(index -> index < newDice.size())
                .filter(index -> index >= 0)
                .forEach(index -> newDice.set(index, Die.roll()));
        return new Dice(newDice);
    }

    @Override
    public String toString() {
        return values.toString();
    }
}
