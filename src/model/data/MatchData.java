package model.data;

import utility.GameMode;

/**
 * 
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
   // public void powerUpCollected(PowerUpType type);
    
    /**
     * 
     * @return the number of powerups used int the match
     */
    public int getNumberOfUsedPowerUps();
    
    /**
     * 
     * @return true if the player has already a power up
     */
    //public boolean hasPowerUp();
    
    /**
     * 
     * Removes the power up
     */
    //public void powerUpEnded();
    
    /**
     * 
     * @return true if the match is going;
     * @return false if it is paused or the round is changing.
     */
    public boolean isMatchGoing();
    
    /**
     * Pauses the match
     */
    public void pauseMatch();
    
    /**
     * Unpauses the match
     */
    public void unpauseMatch();
    
    /**
     * 
     * @return the number of the current round
     */
    public int getCurrentRound();
    
    /**
     * Go to the next round
     */
    public void incrementRound();
    
    /**
     * 
     * @return the game mode of the match
     */
    public GameMode getMode();
    
    /**
     * 
     * @param matchdata, the match to be copied
     * @return an unmodifiable copy of a match
     */
    public UnmodifiableMatchData unmodifiableCopy();
}
