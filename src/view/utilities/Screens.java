package view.utilities;

/**
 * Enum that contains the game scenes.
 *
 */
public enum Screens {
    /**
     * The game scene
     */
    GAME("Game.fxml"),
    /**
     * Pause scene
     */
    PAUSE("Pause.fxml"),
    /**
     * game mode secìlection
     */
    SELECTMODE(""),
    /**
     * main menu
     */
    MENU("Menu.fxml"),
    /**
     * settings scene
     */
    SETTINGS("Settings.fxml"),
    /**
     * achievements scene
     */
    ACHIEVEMENTS(""),
    /**
     * manual scene
     */
    MANUAL(""),
    /**
     * high scores scene
     */
    HIGH_SCORES(""),
    /**
     * game over scene
     */
    GAME_OVER("");
    
    private static final String PATH = "/view/scenes/";
    private final String selecedSceneName;
    
    Screens(final String sceneName) {
        this.selecedSceneName = sceneName;
    }
    
    /**
     * Provides the scenes Path
     * @return
     *          the selected game scene path
     */
    public String getPath() {
        return Screens.PATH + this.selecedSceneName;
    }
}
