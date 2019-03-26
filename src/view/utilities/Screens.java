package view.utilities;

/**
 * Enumeration that contains the game scenes.
 *
 */
public enum Screens {
    /**
     * The game scene.
     */
    GAME("Game.fxml"),
    /**
     * the login scene.
     */
    LOGIN("Login.fxml"),
    /**
     * the registration scene.
     */
    REGISTER("Register.fxml"),
    /**
     * select account scene.
     */
    SELECT_ACCOUNT("SelectAccount.fxml"),
    /**
     * Pause scene.
     */
    PAUSE("Pause.fxml"),
    /**
     * game mode selection.
     */
    SELECTMODE("SelectMode.fxml"),
    /**
     * main menu.
     */
    MENU("Menu.fxml"),
    /**
     * settings scene.
     */
    SETTINGS("Settings.fxml"),
    /**
     * achievements scene.
     */
    ACHIEVEMENTS("Achievements.fxml"),
    /**
     * manual scene.
     */
    MANUAL("Manual.fxml"),
    /**
     * high scores scene.
     */
    HIGH_SCORES("HighScores.fxml"),
    /**
     * game over scene.
     */
    GAME_OVER("GameOver.fxml");
    
    private static final String PATH = "/view/scenes/";
    private final String selecedSceneName;
    
    Screens(final String sceneName) {
        this.selecedSceneName = sceneName;
    }
    
    /**
     * Provides the scenes Path.
     * @return
     *          the selected game scene path
     */
    public String getPath() {
        return Screens.PATH + this.selecedSceneName;
    }
}
