package controller.matches;

/**
 * An enumeration with the different values that represent the score to surpass
 * or the ducks that cannot fly away to allow the player to get to the next
 * round. These values are the essence of the difficulty of a match.
 * 
 *
 */
public enum MatchDifficulty {

    /**
     * The user has to score more than 100 to pass to the next round.
     */
    STORY_EASY(100),

    /**
     * The user has to score more than 200 to pass to the next round.
     */
    STORY_MEDIUM(200),

    /**
     * The user has to score more than 300 to pass to the next round.
     */
    STORY_HARD(300),

    /**
     * The match ends when 5 ducks have flown away.
     */
    SURVIVAL_EASY(5),

    /**
     * The match ends when 3 ducks have flown away.
     */
    SURVIVAL_MEDIUM(3),

    /**
     * The match ends when 1 ducks have flown away.
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