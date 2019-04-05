package model.spawner.duck;

/**
 * 
 * This enum represents the delay between two ducks in a round in milliseconds.
 * 
 * Each time we get the delay, it will generate a new again.
 *
 */

public enum DelayDuckSpawner {

    /**
     * DOG DELAY, WAIT DOG.
     */
    DOG_DELAY(2000),
    /**
     * ROUND DELAY BETWEEN TWO ROUNDS.
     */
    ROUND_DELAY(5000), 
    /**
     * SURVIVAL DELAY.
     */
    SURVIVAL_DELAY(1000),
    /**
     * FIRST ROUND DELAY.
     */
    FIRST_ROUND_DELAY(3000),
    /**
     * SECOND ROUND DELAY.
     */
    SECOND_ROUND_DELAY(2500),
    /**
     * THIRD ROUND DELAY.
     */
    THIRD_ROUND_DELAY(2000),
    /**
     * FOURTH ROUND DELAY.
     */
    FOURTH_ROUND_DELAY(1500),
    /**
     * FIFTH ROUND DELAY.
     */
    FIFTH_ROUND_DELAY(1000);

    /**
     * Contains the default delay.
     */
    private int secondRandomDelay;

    DelayDuckSpawner(final int secondRandomDelay) {
        this.secondRandomDelay = secondRandomDelay;
    }

    /**
     * 
     * @return the second delay.
     */
    public int getSecondDelay() {
        return this.secondRandomDelay;
    }
}


