package controller.matches;

import model.data.MatchData;
import model.data.MatchDataImpl;

/**
 * An abstract class representing the common aspects
 * among the different match modes.
 * @author simone
 *
 */
public abstract class AbstractMatch {
    /**
     * Common fields among the different match modes.
     */
    private static final int FIRST_ROUND = 1;
    private static final boolean START_PAUSED = true;

    private MatchData matchdata;
    private int currentRound;
    private boolean isMatchPaused;

    /**
     * Constructor of an abstract match.
     */
    public AbstractMatch() {
        this.matchdata = new MatchDataImpl();
        this.currentRound = FIRST_ROUND;
        this.isMatchPaused = START_PAUSED;
    }

    /**
     * 
     * @return the game mode
     */
    public abstract GameMode getMode();

    /**
     * 
     * @return the difficulty of the match
     */
    public abstract MatchDifficulty getDifficulty();

    /**
     * 
     * @return the match data of the current match
     */
    public MatchData getMatchData() {
        // TODO Auto-generated method stub
        return this.matchdata;
    }

    /**
     * 
     * @return the number of the current round
     */
    public int getCurrentRound() {
        // TODO Auto-generated method stub
        return this.currentRound;
    }

    /**
     * 
     * @return the number of rounds that a match can have.
     */
    public abstract MaxOfRounds getMaxOfRounds();

    /**
     * Go to the next round.
     */
    public void incrementRound() {
        if (this.currentRound < this.getMaxOfRounds().getRounds() && !this.isMatchGoing()) {
            this.currentRound++;
        }
    };

    /**
     * 
     * @return true if the match is over (winning or losing)
     */
    public abstract boolean isMatchOver(); 

    /**
     * 
     * @return true if the match is going
     */
    public boolean isMatchGoing() {
        // TODO Auto-generated method stub
        return !this.isMatchPaused;
    }

    /**
     * Pauses the match if it is going.
     */
    public void pauseMatch() {
        // TODO Auto-generated method stub
        if (this.isMatchGoing()) {
            this.isMatchPaused = true;
        }
    }

    /**
     * Unpauses the match if it is paused.
     */
    public void unpauseMatch() {
        // TODO Auto-generated method stub
        if (!this.isMatchGoing()) {
            this.isMatchPaused = false;
        }
    }
}
