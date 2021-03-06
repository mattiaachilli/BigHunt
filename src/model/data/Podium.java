package model.data;

import java.io.Serializable;
import java.util.List;

/**
 * A podium represents the top high scores.
 *
 */
public interface Podium extends Serializable {

    /**
     * 
     * @return the entire podium
     */
    List<HighScore> getHighScores();

    /**
     * 
     * @param score the new score
     * @return true if it is an high score
     */
    boolean isHighScore(int score);

    /**
     * 
     * @param score the new score
     * @param name  the username of the player who has made the score
     */
    void addHighScore(int score, String name);
}
