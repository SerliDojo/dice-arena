/**
 * Created by Laurent on 17/01/2015.
 */
public class PigPlayer {

    public final String name;

    public PigPlayer(String name) {
        this.name = name;
    }


    public PigGameAction play(PigGameState state) {
        return PigGameAction.HOLD;
    }
}
