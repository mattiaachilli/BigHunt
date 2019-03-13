package controller.matches;

import model.data.MatchData;

public interface Match {
    
    /**
     * 
     * @return the game mode
     */
    public GameMode getMode();
    
    /**
     * 
     * @return the difficulty of the match
     */
    public MatchDifficulty getDifficulty();
        
    /**
     * 
     * @return the match data of the actual match
     */
    public MatchData getMatchData();
    
    /**
     * 
     * @return true if the match is over (winning or losing)
     */
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
