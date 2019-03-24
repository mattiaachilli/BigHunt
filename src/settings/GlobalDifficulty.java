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
<<<<<<< HEAD
}
=======
}
>>>>>>> 34da97731a83b79fe7c7c29dae89fd0b224998af
