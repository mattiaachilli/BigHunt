package controller.matches;

public enum MatchDifficulty {
    
    /**
     * The user has to score more than 100 to pass to the next round
     */
    STORY_EASY(100),
    
    /**
     * The user has to score more than 200 to pass to the next round
     */
    STORY_MEDIUM(200),
    
    /**
     * The user has to score more than 300 to pass to the next round
     */
    STORY_HARD(300),
    
    /**
     * The match ends when 5 ducks have flown away
     */
    SURVIVAL_EASY(5),
    
    /**
     * The match ends when 3 ducks have flown away
     */
    SURVIVAL_MEDIUM(3),
    
    /**
     * The match ends when 1 ducks have flown away
     */
    SURVIVAL_HARD(1);
    
    private int value;
    
    MatchDifficulty(final int value) {
        this.value = value;
    }
    
    public int getLimitOfDifficulty() {
        return this.value;
    }
}
