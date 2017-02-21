package com.serli.dojo.arena.dice.poker;

import static java.util.Arrays.asList;
import static java.util.Comparator.comparing;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

import com.serli.dojo.arena.dice.Dice;
import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public enum PokerHand {

	HIGH_DIE(
			dice -> true,
			dice -> dice.values.stream().max(Comparator.<Integer> naturalOrder()).orElse(0)
	), 
	ONE_PAIR(
			dice -> sidesAsManyAs(dice, 2).count() >= 1,
			dice -> 10
	), 
	TWO_PAIRS(
			dice -> sidesAsManyAs(dice, 2).count() >= 2,
			dice -> 20
	), 
	THREE_OF_A_KIND(
			dice -> sidesAsManyAs(dice, 3).count() >= 1,
			dice -> 30
	), 
	FOUR_OF_A_KIND(
			dice -> sidesAsManyAs(dice, 4).count() >= 1,
			dice -> 50
	), 
	FULL_HOUSE(
			TWO_PAIRS.apply.and(THREE_OF_A_KIND.apply),
			dice -> ONE_PAIR.score.apply(dice) + THREE_OF_A_KIND.score.apply(dice)
	), 
	STRAIGHT(
			dice -> dice.values.stream().distinct().count() == 5 && (!dice.values.contains(1) || !dice.values.contains(6)),
			dice -> dice.values.contains(1) ? 60 : 70
	);

	final Predicate<Dice> apply;
	final Function<Dice, Integer> score;

	private PokerHand(int sideCount, int groupCount) {
		this(
				dice -> sidesAsManyAs(dice, sideCount).count() >= groupCount,
				dice -> sidesAsManyAs(dice, sideCount).limit(groupCount).mapToInt(entry -> (int) (entry.getKey() * entry.getValue())).sum()
		);
	}

	private PokerHand(Predicate<Dice> apply, Function<Dice, Integer> score) {
		this.apply = apply;
		this.score = dice -> apply.test(dice) ? score.apply(dice) : 0;
	}

	private static final Comparator<Entry<Integer, Long>> BY_VALUE_DESC = Map.Entry.<Integer, Long> comparingByValue().reversed();
	private static final Comparator<Entry<Integer, Long>> BY_KEY_DESC = Map.Entry.<Integer, Long> comparingByKey().reversed();

	public static PokerHand bestHand(Dice dice) {
		return asList(values()).stream()
				.filter(hand -> hand.apply.test(dice))
				.max(comparing(hand -> hand.score.apply(dice)))
				.get();
	}

	public static Integer bestScore(Dice dice) {
		return bestHand(dice).score.apply(dice);
	}

	public static Stream<Map.Entry<Integer, Long>> sidesAsManyAs(Dice dice, int count) {
		return dice.values.stream()
				.collect(groupingBy(identity(), counting()))
				.entrySet().stream()
				.filter(entry -> entry.getValue() >= count)
				.sorted(BY_VALUE_DESC.thenComparing(BY_KEY_DESC));
	}
}
