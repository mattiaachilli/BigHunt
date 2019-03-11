package model.data;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * 
 * Keeps the game data, such as players and high scores
 */
public interface GlobalData extends Serializable {
    
    /**
     * 
     * @return a list of the high scores
     */
    public List<HighScore> getHighScores();
    
    /**
     * 
     * @param score, the score to be verified
     * @return true if the score is actually an high score
     */
    public boolean isHighScore(final int score);
    
    /**
     * 
     * @param score, the new high score
     * @param name, the player's username
     * Puts the new high score in the podium
     */
    public void addHighScore(final int score, final String name);
    
    /**
     * 
     * @return all the players' data
     */
    public List<UserData> getUsers();
    
    /**
     * 
     * @param name, the username of a player
     * @return true if a player already exists
     */
    public boolean isPresent(final String name);
    
    /**
     * 
     * @param name, the username of the new player
     * Adds a player account to the game and creates his data
     */
    public void addPlayer(final String name);
    
    /**
     * 
     * @param matchdata, the data of the just ended match
     * @param playerName, the player's username
     */
    public void addMatchData(final MatchData matchdata, final String playerName);
}
