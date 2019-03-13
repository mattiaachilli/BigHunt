package controller.matches;

import model.data.MatchData;

public interface Match {
    
    public GameMode getMode();
    
    public MatchDifficulty getDifficulty();
    
    public MatchData getMatchData();
    
    public boolean isMatchOver(); 
    
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
}
