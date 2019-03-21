package settings;

public enum GlobalDifficulty {
    
    EASY("EASY"), MEDIUM("MEDIUM"), HARD("HARD");
    
    private String diff;
    
    GlobalDifficulty(final String diff) {
        this.diff = diff;
    }
    
    public String getGlobalDifficulty() {
        return this.diff;
    }
}