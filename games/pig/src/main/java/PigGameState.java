import java.util.HashMap;
import java.util.Map;

/**
 * Created by Laurent on 17/01/2015.
 */
public class PigGameState {

    public Map<String, Integer> scores = new HashMap<String, Integer>();
    public Integer turnScore = 0;
    public Integer turnCount = 0;


    public PigGameState holded() {
        return new PigGameState(score + turnScore, 0, die, turn);
    }

    @Override
    public String toString() {
        return "At turn " + turn +
                ", player scored " + turnScore +
                " points and holds " + score + " points";
    }
}
