package model.matches;

import org.apache.commons.lang3.tuple.ImmutablePair;

import model.data.MatchData;
import model.data.MatchDataImpl;
import model.gun.Magazine;
import model.gun.MagazineImpl;

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
    private static final int FIRST_MAGAZINE = 1;

    private MatchData matchdata;
    private int currentRound;
    private Magazine magazine;
    private int currentMagazineNumber;

    private boolean roundEnded;

    /**
     * Constructor of an abstract match.
     */
    public AbstractMatch() {
        this.matchdata = new MatchDataImpl();
        this.currentRound = FIRST_ROUND;
        this.currentMagazineNumber = FIRST_MAGAZINE;
        this.magazine = new MagazineImpl(this.currentMagazineNumber);
        this.roundEnded = false;
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
        return this.matchdata;
    }

    /**
     * 
     * @return the current magazine.
     */
    public Magazine getCurrentMagazine() {
        return this.magazine;
    }

    /**
     * 
     * @return the number of the current magazine.
     */
    public int getCurrentMagazineNumber() {
        return this.currentMagazineNumber;
    }

    /**
     * 
     * Recharges the magazine.
     */
    public void recharge() {
        this.currentMagazineNumber++;
        this.magazine = new MagazineImpl(this.currentMagazineNumber);
    }

    /**
     * Go to the next round.
     */
    public void incrementRound() {
        if (this.currentRound < this.getMaxOfRounds().getRounds()) {
            this.currentRound++;
        }
    }

    /**
     * 
     * @return the number of the current round
     */
    public int getCurrentRound() {
        return this.currentRound;
    }

    /**
     * Ends a round, so that the game over control can go on.
     */
    public void endRound() {
        this.roundEnded = true;
    }

    /**
     * Restarts the round.
     */
    public void startRound() {
        this.roundEnded = false;
    }

    /**
     * 
     * @return true if the round is ended.
     */
    public boolean isRoundEnded() {
        return this.roundEnded;
    }
    /**
     * 
     * @return the number of rounds that a match can have.
     */
    public abstract MaxOfRounds getMaxOfRounds();

    /**
     * 
     * @return a pair with the minimum and maximum round.
     */
    public abstract ImmutablePair<Integer, Integer> getRounds();

    /**
     * 
     * @return true if the match is over (winning or losing)
     */
    public abstract boolean isMatchOver(); 

    /**
     * Global method for game over.
     * @return true if the game is over
     */
    public boolean isGameOver() {
        return this.isRoundEnded() && this.isMatchOver();
    }

}
