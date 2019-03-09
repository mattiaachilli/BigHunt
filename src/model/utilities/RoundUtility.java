package model.utilities;

public enum RoundUtility {
    
    FIVE_ROUNDS(5), UNIQUE_ROUND(1);
    
    private final int maxRounds;
    
    RoundUtility(final int max) {
        this.maxRounds = max;
    }
    
    public int getRounds() {
        return this.maxRounds;
    }
}
