package model.data;

import java.io.Serializable;

/**
 * 
 * @author simone
 * 
 * A class representing an high score. Later on, the high scores will be
 * 5, not only the top score has to be considered an high score.
 */
public interface HighScore extends Serializable {

    /**
     * 
     * @return the username of the player that has registered the high score
     */
    String getName();

    /**
     * 
     * @return the score
     */
    int getScore();
}
