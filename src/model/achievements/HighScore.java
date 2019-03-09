package model.achievements;

import java.io.Serializable;

/**
 * 
 * @author simone
 *  
 *  A class representing an high score.
 *  Later on, the high scores will be 5,
 *  not only the top score has to be considered an high score.
 */
public interface HighScore extends Serializable {
    
    /**
     * 
     * @return the username of the player that has registered the high score
     */
    public String getName();
    
    /**
     * 
     * @return the score
     */
    public int getScore();
}
