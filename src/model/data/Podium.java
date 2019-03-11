package model.data;

import java.util.List;

public interface Podium {
    
    /**
     * 
     * @return the actual podium
     */
    public List<HighScore> getHighScores();
    
    /**
     * 
     * @param score, the new score
     * @return true if it is an high score
     */
    public boolean isHighScore(final int score);
    
    /**
     * 
     * @param score, the new score
     * @param name, the username of the player who has made the score
     */
    public void addHighScore(final int score, final String name);
}
