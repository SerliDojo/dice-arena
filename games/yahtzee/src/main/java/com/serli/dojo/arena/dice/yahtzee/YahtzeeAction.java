package com.serli.dojo.arena.dice.yahtzee;

import com.serli.dojo.arena.dice.Action;
import java.util.Arrays;
import java.util.Optional;

public abstract class YahtzeeAction implements Action {

    public static YahtzeeAction score(YahtzeeLine line) {
        return new YahtzeeScoreAction(line);
    }

    public static YahtzeeAction roll(Integer... diceIndex) {
        return new YahtzeeRollAction(diceIndex);
    }

    static class YahtzeeScoreAction extends YahtzeeAction {
        public final YahtzeeLine line;

        public YahtzeeScoreAction(YahtzeeLine line) {
            this.line = line;
        }

        @Override
        public String toString() {
            return String.format("Score on %s line", line);
        }
    }

    static class YahtzeeRollAction extends YahtzeeAction {
        public final Integer[] diceIndex;

        public YahtzeeRollAction(Integer[] diceIndex) {
            this.diceIndex = diceIndex;
        }

        @Override
        public String toString() {
            return "Roll " + Arrays.toString(diceIndex);
        }
    }

}
