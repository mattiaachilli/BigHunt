package view.sceneloader;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import model.matches.GameMode;
import settings.SettingsImpl;
import view.View;
import view.scenecontroller.AchievementSceneController;
import view.scenecontroller.GameOverSceneController;
import view.scenecontroller.GameSceneController;
import view.scenecontroller.HighScoresSceneController;
import view.scenecontroller.LoginSceneController;
import view.scenecontroller.RegisterSceneController;
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
    private static Scene gameScreen;
    private static SceneController gameSceneController;

    /**
     * 
     * @param view object contains the entire view
     */
    public SceneLoaderImpl(final View view) {
        this.view = view;
    }

    @Override
    public final void loadScene(final Stage stage, final Screens screen, final GameMode gameMode) {
        final Region root;
        final FXMLLoader loader = new FXMLLoader();
        final SceneController controller;
        final Scene scene;

        try {

            if (screen != Screens.GAME || gameScreen == null) {
                loader.setLocation(getClass().getResource(screen.getPath()));
                root = loader.load();

                controller = loader.getController();
                controller.setSceneFactory(this.view.getSceneFactory());

                root.setPrefSize(SettingsImpl.getSettings().getSelectedResolution().getKey(),
                SettingsImpl.getSettings().getSelectedResolution().getValue());

                root.getChildrenUnmodifiable().stream().forEach(e -> {
                    if (screen == Screens.GAME && e instanceof Label || screen != Screens.GAME) {
                        e.setScaleX(SettingsImpl.getSettings().getScaleFactory());
                        e.setScaleY(SettingsImpl.getSettings().getScaleFactory());
                    }
                });
                scene = new Scene(root);
                scene.getStylesheets().add(STYLE_CSS_PATH);

                if (screen == Screens.GAME) {
                    gameScreen = scene;
                    gameSceneController = controller;
                }
            } else {
                scene = gameScreen;
                controller = gameSceneController;
            }
            stage.setScene(scene);

            stage.setResizable(false);

            if (!SettingsImpl.getSettings().isFullScreen()) {
                stage.sizeToScene();
                stage.centerOnScreen();
            }

            if (!stage.isShowing()) {
                stage.show();
            }

            switch (screen) {
            case SELECT_ACCOUNT:
                break;
            case MENU:
                break;
            case LOGIN:
                final LoginSceneController log = (LoginSceneController) controller;
                log.setView(this.view);
                break;
            case REGISTER:
                final RegisterSceneController reg = (RegisterSceneController) controller;
                reg.setView(this.view);
                break;
            case GAME:
                if (!this.view.isPaused()) {
                    //System.out.println("Scena game caricata, inizializzo partita");
                    this.view.startGame((GameSceneController) controller, gameMode);
                }
                break;
            case GAME_OVER:
                final GameOverSceneController gameOver = (GameOverSceneController) controller;
                gameOver.setMatchData(this.view.getMatchData());
                break;
            case ACHIEVEMENTS:
                final AchievementSceneController achievementController = (AchievementSceneController) controller;
                view.setAchievements(this.view.getController().getUser().get().getAchievements());
                achievementController.setAchievements(this.view.getAchievements());
                break;
            case HIGH_SCORES:
                final HighScoresSceneController highScores = (HighScoresSceneController) controller;
                highScores.setStoryModePodium(this.view.getStoryPodium());
                highScores.setSurvivalModePodium(this.view.getSurvivalPodium());
                break;
            default:
                break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
