package com.serli.dojo.arena.dice.yahtzee;

import static java.util.Arrays.stream;
import static java.util.Comparator.comparing;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public enum YahtzeeLine {

	ACES(sumForSide(1)),
	TWOS(sumForSide(2)),
	THREES(sumForSide(3)),
	FOURS(sumForSide(4)),
	FIVES(sumForSide(5)),
	SIXES(sumForSide(6)),
	THREE_OF_A_KIND(sumForAllIf(minGroupsOver(3, 1))),
	FOUR_OF_A_KIND(sumForAllIf(minGroupsOver(4, 1))),
	FULL_HOUSE(scoreIf(minGroupsOver(2, 2).and(minGroupsOver(3, 1)), 25)),
	SMALL_STRAIGHT(scoreIf(
			minGroupsOver(1, 4).and(missing(1, 2).or(missing(1, 6)).or(missing(5, 6))).or(
					minGroupsOver(1, 5).and(missing(1).or(missing(2)).or(missing(5)).or(missing(6)))),
			30)),
	LARGE_STRAIGHT(scoreIf(minGroupsOver(1, 5).and(missing(1).or(missing(6))), 40)),
	YAHTZEE(scoreIf(minGroupsOver(5, 1), 50)),
	CHANCE(sumForAll());

	final Function<YahtzeeDice, Integer> score;

	public static final List<YahtzeeLine> UPPER_SECTION = Arrays.asList(ACES, TWOS, THREES, FOURS, FIVES, SIXES);

	private YahtzeeLine(Function<YahtzeeDice, Integer> score) {
		this.score = score;
	}

	private static final Comparator<Entry<Integer, Long>> BY_SIDE_DESC = Entry.<Integer, Long> comparingByValue().reversed();

	private static final Comparator<Entry<Integer, Long>> BY_KEY_DESC = Entry.<Integer, Long> comparingByKey().reversed();

	private static Function<YahtzeeDice, Integer> sumForAll() {
		return dice -> Arrays.stream(dice.dice).mapToInt(Integer::intValue).sum();
	}

	private static Function<YahtzeeDice, Integer> sumForAllIf(Predicate<YahtzeeDice> predicate) {
		return dice -> Optional.ofNullable(dice).filter(predicate).map(sumForAll()).orElse(0);
	}

	private static Function<YahtzeeDice, Integer> sumForSide(int side) {
		return dice -> Arrays.stream(dice.dice).filter(die -> die == side).mapToInt(Integer::intValue).sum();
	}

	private static Function<YahtzeeDice, Integer> scoreIf(Predicate<YahtzeeDice> predicate, Integer score) {
		return dice -> Optional.ofNullable(dice).filter(predicate).map(d -> score).orElse(0);
	}

	public static Predicate<YahtzeeDice> missing(int... sides) {
		return dice -> Arrays.stream(sides)
				.boxed()
				.map(YahtzeeLine::missing)
				.reduce(Predicate::and)
				.orElse(o -> false)
				.test(dice);
	}

	public static Predicate<YahtzeeDice> missing(int side) {
		return dice -> stream(dice.dice).noneMatch(Predicate.isEqual(side));
	}

	public static Predicate<YahtzeeDice> minGroupsOver(int diceCount, int groupCount) {
		return dice -> Optional.ofNullable(dice)
				.map(groupsOver(diceCount))
				.filter(groups -> groups.count() >= groupCount)
                .isPresent();
	}

	public static Function<YahtzeeDice, Stream<Integer>> groupsOver(int count) {
		return dice -> stream(dice.dice)
				.collect(groupingBy(identity(), counting()))
				.entrySet().stream()
				.filter(entry -> entry.getValue() >= count)
				.map(Entry::getKey);
	}
}
