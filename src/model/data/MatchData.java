package model.data;

import model.entities.powerup.PowerUpType;

/**
 * Represents the information of the current match.
 * @author simone
 */

public interface MatchData {

    /**
     * 
     * @return the score of the actual match
     */
    int getGlobalScore();

    /**
     * 
     * Increments the number of killed ducks.
     */
    void incrementKilledDucks();

    /**
     * 
     * @return the number of killed ducks in the actual match
     */
    int getKilledDucks();

    /**
     * 
     * Increments the number of flown ducks.
     */
    void incrementFlownDucks();

    /**
     * 
     * @return the number of flown ducks in the actual match
     */
    int getFlownDucks();

    /**
     * 
     * @param score
     *          the score received by killing a certain duck
     */
    void incrementScoreOf(int score);

    /**
     * 
     * @param negativeScore
     *          the score to remove from the global score as a penalty
     */
    void decrementScoreOf(int negativeScore);

    /**
     * 
     * @return the time elapsed (in milliseconds) from the beginning of the match
     */
    int getTimerFromStart();

    /**
     * 
     * @param timeElapsed
     *          the time elapsed in a certain period
     */
    void addTimeToTimer(int timeElapsed);

    /**
     * 
     * @param type
     *          the type of the power up just collected
     */
    void powerUpCollected(PowerUpType type);

    /**
     * 
     * @return the number of powerups used int the match
     */
    int getNumberOfUsedPowerUps();

    /**
     * 
     * @return true if the player has already a power up
     */
    boolean hasPowerUp();

    /**
     * 
     * Removes the power up.
     */
    void powerUpEnded();

    /**
     * 
     * @return an unmodifiable copy of a match
     */
   UnmodifiableMatchData unmodifiableCopy();
}
