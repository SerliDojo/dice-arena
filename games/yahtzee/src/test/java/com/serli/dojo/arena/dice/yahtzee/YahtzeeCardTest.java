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
}
