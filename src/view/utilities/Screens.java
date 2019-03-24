package view.utilities;

/**
 * Enum that contains the game scenes.
 *
 */
public enum Screens {

    GAME(""),
    
    PAUSE(""),
    
    SELECTMODE(""),
    
    MENU(""),
    
    SETTINGS(""),
    
    ACHIEVEMENTS(""),
    
    MANUAL(""),
    
    HIGH_SCORES(""),
    
    GAME_OVER("");
    
    private static final String PATH = "/view/scenes/";
    private final String selecedSceneName;
    
    private Screens(final String sceneName) {
        this.selecedSceneName = sceneName;
    }
    
    public String getPath() {
        return Screens.PATH + this.selecedSceneName;
    }
}
