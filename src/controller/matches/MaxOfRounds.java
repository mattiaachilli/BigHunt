package controller.matches;

public enum MaxOfRounds {
    
    FIVE_ROUNDS(5), UNIQUE_ROUND(1);
    
    private final int maxRounds;
    
    MaxOfRounds(final int max) {
        this.maxRounds = max;
    }
    
    public int getRounds() {
        return this.maxRounds;
    }
}
