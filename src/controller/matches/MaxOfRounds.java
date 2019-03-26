package controller.matches;

/**
 * 
 * @author simone
 * Enumeration that shows how many rounds can a match have 
 * related to his game mode.
 */
public enum MaxOfRounds {

    /**
     * The rounds can be five or just one.
     */
    FIVE_ROUNDS(5), UNIQUE_ROUND(1);

    private final int maxRounds;

    MaxOfRounds(final int max) {
        this.maxRounds = max;
    }

    /**
     * 
     * @return the maximum number of rounds that a certain match can have.
     */
    public int getRounds() {
        return this.maxRounds;
    }
}