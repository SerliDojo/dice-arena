package com.serli.dojo.arena.dice.yahtzee;

import java.util.*;
import java.util.stream.IntStream;

public class YahtzeeCard {

    private Map<YahtzeeLine, Integer> lines;

    private YahtzeeCard(Map<YahtzeeLine, Integer> lines) {
        this.lines = lines;
    }

    public YahtzeeCard() {
        this.lines = new HashMap<>();
    }

    public int score() {
        return lines.values().stream().mapToInt(i -> i).sum()
        + Optional.ofNullable(lines)
                .map(Map::entrySet)
                .map(Set::stream)
                .map(stream -> stream.filter(entry -> YahtzeeLine.UPPER_SECTION.contains(entry.getKey())))
                .map(stream -> stream.mapToInt(entry -> entry.getValue()))
                .map(IntStream::sum)
                .filter(sum -> sum >= 63)
                .map(sum -> 35)
                .orElse(0);
    }

    public boolean lineFree(YahtzeeLine line) {
        return !lines.containsKey(line);
    }

    public boolean full() {
        return lines.size() == YahtzeeLine.values().length;
    }

    public Optional<YahtzeeLine> nextEmptyLine() {
        return Arrays.stream(YahtzeeLine.values())
                .filter(this::lineFree)
                .findFirst();
    }

    public Optional<YahtzeeLine> lineOrNext(YahtzeeLine line) {
        return Optional.ofNullable(line)
                .filter(this::lineFree)
                .map(Optional::ofNullable)
                .orElseGet(this::nextEmptyLine);
    }

    public YahtzeeCard scoring(YahtzeeLine line, int score) {
        YahtzeeCard card = this;
        if(lineFree(line)) {
            HashMap<YahtzeeLine, Integer> newLines = new HashMap<>(this.lines);
            newLines.put(line, score);
            card = new YahtzeeCard(newLines);
        }
        return card;
    }

    @Override
    public String toString() {
        return lines.toString();
    }
}
