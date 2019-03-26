package settings;

/**
 * The difficulty that can be chosen from the main menu.
 * 
 * @author simone
 *
 */
public enum GlobalDifficulty {

    /**
     * The three difficulties.
     */
    EASY("EASY"), MEDIUM("MEDIUM"), HARD("HARD");

    private String diff;

    GlobalDifficulty(final String diff) {
        this.diff = diff;
    }

    /**
     * Method to return the difficulty.
     * 
     * @return the chosen value for the difficulty.
     */
    public String getGlobalDifficulty() {
        return this.diff;
    }

}
