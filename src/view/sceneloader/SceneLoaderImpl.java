package view.sceneloader;

import controller.matches.GameMode;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import model.data.HighScore;
import settings.SettingsImpl;
import view.View;
import view.scenecontroller.AchievementSceneController;
import view.scenecontroller.GameOverSceneController;
import view.scenecontroller.GameSceneController;
import view.scenecontroller.HighScoresSceneController;
import view.scenecontroller.SceneController;
import view.utilities.Screens;

/**
 * 
 * Implements scene loader scene.
 *
 */
public class SceneLoaderImpl implements SceneLoader {

    private final View view;
    private static final String STYLE_CSS_PATH = "/view/style/style.css";
    
    /**
     * 
     * @param view
     *          object contains the entire view
     */
    public SceneLoaderImpl(final View view) {
        this.view = view;
    }
    
    @Override
    public void loadScene(final Stage stage, final Screens screen, final GameMode gameMode) {
        final Region root;
        final FXMLLoader loader = new FXMLLoader();
        
        try {
            loader.setLocation(getClass().getResource(screen.getPath()));
            root = loader.load();
            
            final SceneController controller = loader.getController();
            controller.setSceneFactory(this.view.getSceneFactory());
            
            root.setPrefSize(SettingsImpl.getSettings().getSelectedResolution().getKey(),
                SettingsImpl.getSettings().getSelectedResolution().getValue());
            
            root.getChildrenUnmodifiable().stream().forEach(e -> {
                e.setScaleX(SettingsImpl.getSettings().getScaleFactory());
                e.setScaleY(SettingsImpl.getSettings().getScaleFactory());
            });
            
            stage.setScene(new Scene(root));
            
            stage.getScene().getStylesheets().add(STYLE_CSS_PATH);
            stage.setResizable(false);
            
            if(!SettingsImpl.getSettings().isFullScreen()) {
                stage.sizeToScene();
                stage.centerOnScreen();
            }
            
            if (!stage.isShowing()) {
                stage.show();
            }
            
            switch (screen) {
            case SELECT_ACCOUNT:
                //this.view.resetGame();
                break;
            case MENU: 
            //    this.view.resetGame();
                break;
            case LOGIN:
                //caricare i file o non ce bisogno??
                break;
            case REGISTER:
                //caricare il file o non ce bisogno??
                break;
            case GAME:
                this.view.startGame((GameSceneController) controller,gameMode);
                break;
            case GAME_OVER:
                final GameOverSceneController gameOver = (GameOverSceneController) controller;
                gameOver.setMatchData(this.view.getMatchData());
                break;
            case ACHIEVEMENTS:
                final AchievementSceneController achievementController = (AchievementSceneController) controller;
                achievementController.setAchievements(this.view.getAchievements());
                break;
            case HIGH_SCORES:
                final HighScoresSceneController highScores = (HighScoresSceneController) controller;
                highScores.setStoryModeHighScores(this.view.getStoryHighScores());
                highScores.setSurvivalModeHighScores(this.view.getSurvivalHighScores());
                break;
            default:
                break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
