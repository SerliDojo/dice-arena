import com.google.common.collect.Collections2;
import com.google.common.collect.Iterables;

import java.lang.management.PlatformLoggingMXBean;
import java.util.*;
import java.util.stream.Stream;

/**
 * Created by Laurent on 17/01/2015.
 */
public class PigGameEngine {

    public static void main(String[] args) {
        PigGameEngine engine = new PigGameEngine();
        int id = engine.start(new PigPlayer("John"), new PigPlayer("Bill"));

        while (!engine.step()) ;
    }

    public static PigGameEngine create(List<PigPlayer> players) {
        PigGameEngine game = new PigGameEngine();
        game.players.addAll(players);
        game.state = new PigGameState();
        for (PigPlayer player : players) {
            game.state.scores.put(player.name, 0);
        }
        game.playerIterator = players.iterator();
        return game;
    }

    private final List<PigPlayer> players = new ArrayList<PigPlayer>();
    private PigGameState state;
    private Iterator<PigPlayer> playerIterator;

    private boolean step() {
        if(!playerIterator.hasNext()) {
            playerIterator = players.iterator();
        }
        PigPlayer currentPlayer = playerIterator.next();

        System.out.println(state);
        int die = new Random().nextInt(6) + 1;
        System.out.println(currentPlayer.name + " rolled a " + die);

        state.scores.get(currentPlayer.name)
        int newTurnScore = 0;
        if (die > 1) {
            newTurnScore = turnScore + die;
        }
        return new PigGameState(score, newTurnScore, die, turn + 1);



        if (die > 1) {
            PigGameAction action = player.play(state);
            System.out.println("Player choose to " + action);
            if (action == PigGameAction.HOLD) {
                state = state.holded();
            }
        }
        return die == 1 || state.score >= 100;
    }


}

