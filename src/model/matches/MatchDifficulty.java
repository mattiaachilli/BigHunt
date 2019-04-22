package model.matches;

/**
 * An enumeration with the different values that represents the score to surpass
 * or the ducks that cannot fly away to allow the player to get to the next
 * round. These values are the essence of the difficulty of a match.
 *
 */
public enum MatchDifficulty {

    /**
     * The user has to score more than 500 to pass to the next round.
     */
    STORY_EASY(500),

    /**
     * The user has to score more than 750 to pass to the next round.
     */
    STORY_MEDIUM(750),

    /**
     * The user has to score more than 1000 to pass to the next round.
     */
    STORY_HARD(1000),

    /**
     * The match ends when 5 ducks have flown away.
     */
    SURVIVAL_EASY(5),

    /**
     * The match ends when 3 ducks have flown away.
     */
    SURVIVAL_MEDIUM(3),

    /**
     * The match ends when 1 duck has flown away.
     */
    SURVIVAL_HARD(1);

    private int value;

    MatchDifficulty(final int value) {
        this.value = value;
    }

    /**
     * 
     * @return the value representing a certain difficulty
     */
    public int getLimitOfDifficulty() {
        return this.value;
    }
}

