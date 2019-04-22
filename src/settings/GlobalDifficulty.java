package settings;

/**
 * The difficulties that can be chosen from the settings.
 *
 *
 */
public enum GlobalDifficulty {

    /**
     * The game difficulties.
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
