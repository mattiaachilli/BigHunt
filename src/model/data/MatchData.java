package model.data;

/**
 * 
 * @author simone
 * 
 * Represents the information of the current match
 *
 */

public interface MatchData {
    
    /**
     * 
     * @return the score of the actual match
     */
    public int getGlobalScore();
    
    /**
     * 
     * Increments the number of killed ducks
     */
    public void incrementNumberOdKilledDucks();
    
    /**
     * 
     * @return the number of killed ducks in the actual match
     */
    public int getNumberOfKilledDucks();
    
    /**
     * 
     * @param score, the score received by killing a certain duck
     */
    public void incrementScoreOf(int score);
    
    /**
     * 
     * @param negativeScore, the score to remove from the global score as a penalty
     */
    public void decrementScoreOf(int negativeScore);
    
    /**
     * 
     * @return the time elapsed (in milliseconds) from the beginning of the match
     */
    public int getTimerFromStart();
    
    /**
     * 
     * @param timeElapsed, the time elapsed in a certain period
     */
    public void addTimeToTimer(int timeElapsed);
    
    /**
     * 
     * @param type, the type of the power up just collected
     */
    public void powerUpCollected(PowerUpType type);
    
    /**
     * 
     * @return true if the player has already a power up
     */
    public boolean hasPowerUp();
    
    /**
     * 
     * Removes the power up
     */
    public void powerUpEnded();
}
