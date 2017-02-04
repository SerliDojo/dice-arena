package com.serli.dojo.arena.dice.yahtzee;

import java.util.Optional;
import org.junit.Test;

import static org.junit.Assert.*;

public class YahtzeeCardTest {

    @Test
    public void testNewCard() {
        YahtzeeCard card = new YahtzeeCard();
        assertNotNull(card);
        assertFalse(card.full());
        assertEquals(Optional.of(YahtzeeLine.values()[0]), card.nextEmptyLine());
    }

    @Test
    public void testScoringEmptyCard() {
        YahtzeeCard card = new YahtzeeCard().scoring(YahtzeeLine.values()[0], 0);
        assertNotNull(card);
        assertFalse(card.full());
        assertEquals(Optional.of(YahtzeeLine.values()[1]), card.nextEmptyLine());
    }

    @Test
    public void testFullCard() {
        YahtzeeCard card = new YahtzeeCard();
        assertNotNull(card);
        for( YahtzeeLine line : YahtzeeLine.values()) {
            card = card.scoring(line, 0);
        }
        assertTrue(card.full());
        assertEquals(Optional.empty(), card.nextEmptyLine());
    }

    @Test
    public void testScoringFullCard() {
        YahtzeeCard card = new YahtzeeCard();
        for( YahtzeeLine line : YahtzeeLine.values()) {
            card = card.scoring(line, 0);
        }
        assertEquals(card.score(), card.scoring(YahtzeeLine.values()[0], 10).score());
    }

    @Test
    public void testScoreForEmptyCard() {
        YahtzeeCard card = new YahtzeeCard();
        assertEquals(0, card.score());
    }

    @Test
    public void testScoreForFullAces() {
        YahtzeeCard card = new YahtzeeCard().scoring(YahtzeeLine.ACES, 6);
        assertEquals(6, card.score());
    }

    @Test
    public void testScoreWithoutBonus() {
        YahtzeeCard card = new YahtzeeCard()
                .scoring(YahtzeeLine.ACES, 2)
                .scoring(YahtzeeLine.TWOS, 6)
                .scoring(YahtzeeLine.THREES, 9)
                .scoring(YahtzeeLine.FOURS, 12)
                .scoring(YahtzeeLine.FIVES, 15)
                .scoring(YahtzeeLine.SIXES, 18);
        assertEquals(62, card.score());
    }

    @Test
    public void testScoreWithBonus() {
        YahtzeeCard card = new YahtzeeCard()
                .scoring(YahtzeeLine.ACES, 3)
                .scoring(YahtzeeLine.TWOS, 6)
                .scoring(YahtzeeLine.THREES, 9)
                .scoring(YahtzeeLine.FOURS, 12)
                .scoring(YahtzeeLine.FIVES, 15)
                .scoring(YahtzeeLine.SIXES, 18);
        assertEquals(98, card.score());
    }

    @Test
    public void testScoreWithBestLines() {
        YahtzeeCard card = new YahtzeeCard()
                .scoring(YahtzeeLine.ACES, 5)
                .scoring(YahtzeeLine.TWOS, 10)
                .scoring(YahtzeeLine.THREES, 15)
                .scoring(YahtzeeLine.FOURS, 20)
                .scoring(YahtzeeLine.FIVES, 25)
                .scoring(YahtzeeLine.SIXES, 30)
                .scoring(YahtzeeLine.THREE_OF_A_KIND, 30)
                .scoring(YahtzeeLine.FOUR_OF_A_KIND, 30)
                .scoring(YahtzeeLine.FULL_HOUSE, 25)
                .scoring(YahtzeeLine.SMALL_STRAIGHT, 30)
                .scoring(YahtzeeLine.LARGE_STRAIGHT, 40)
                .scoring(YahtzeeLine.YAHTZEE, 50)
                .scoring(YahtzeeLine.CHANCE, 30);
        assertEquals(375, card.score());
    }
}
