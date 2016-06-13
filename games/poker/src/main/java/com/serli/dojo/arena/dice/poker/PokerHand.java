package com.serli.dojo.arena.dice.poker;

import static java.util.Arrays.asList;
import static java.util.Arrays.stream;
import static java.util.Comparator.comparing;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public enum PokerHand {

	HIGH_DIE(1, 1), ONE_PAIR(2, 1), TWO_PAIRS(2, 2), THREE_OF_A_KIND(3, 1), FOUR_OF_A_KIND(4, 1), 
	FULL_HOUSE(
			ONE_PAIR.apply.and(THREE_OF_A_KIND.apply),
			dice -> ONE_PAIR.score.apply(dice) + THREE_OF_A_KIND.score.apply(dice)
	), 
	STRAIGHT(
			dice -> stream(dice.dice).distinct().count() == 5 && (!asList(dice.dice).contains(1) || !asList(dice.dice).contains(6)),
			dice -> stream(dice.dice).reduce(0, (a, i) -> a + i * 5)
	);

	final Predicate<PokerDice> apply;
	final Function<PokerDice, Integer> score;

	private PokerHand(int sideCount, int groupCount) {
		this(
				dice -> sidesAsManyAs(dice, sideCount).count() >= groupCount,
				dice -> sidesAsManyAs(dice, sideCount).limit(groupCount).mapToInt(entry -> (int) (entry.getKey() * entry.getValue())).sum()
		);
	}

	private PokerHand(Predicate<PokerDice> apply, Function<PokerDice, Integer> score) {
		this.apply = apply;
		this.score = dice -> apply.test(dice) ? score.apply(dice) : 0;
	}

	private static final Comparator<Entry<Integer, Long>> BY_VALUE_DESC = Map.Entry.<Integer, Long> comparingByValue().reversed();
	private static final Comparator<Entry<Integer, Long>> BY_KEY_DESC = Map.Entry.<Integer, Long> comparingByKey().reversed();

	public static PokerHand bestHand(PokerDice dice) {
		return asList(values()).stream()
				.filter(hand -> hand.apply.test(dice))
				.max(comparing(hand -> hand.score.apply(dice)))
				.get();
	}

	public static Integer bestScore(PokerDice dice) {
		return bestHand(dice).score.apply(dice);
	}

	public static Stream<Map.Entry<Integer, Long>> sidesAsManyAs(PokerDice dice, int count) {
		return stream(dice.dice)
				.collect(groupingBy(identity(), counting()))
				.entrySet().stream()
				.filter(entry -> entry.getValue() == count)
				.sorted(BY_VALUE_DESC.thenComparing(BY_KEY_DESC));
	}
}
