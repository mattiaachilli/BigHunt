package view.sceneloader;

import java.util.Optional;

import controller.input.CommandType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
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
    private static final String LOGO_PATH = "/view/logo/logo.png";

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
            loader.setLocation(getClass().getResource(screen.getPath()));
            root = loader.load();

            controller = loader.getController();
            controller.setSceneFactory(this.view.getSceneFactory());

            root.setPrefSize(SettingsImpl.getSettings().getSelectedResolution().getKey(),
            SettingsImpl.getSettings().getSelectedResolution().getValue());

            root.getChildrenUnmodifiable().stream().forEach(e -> {
                if ((screen == Screens.GAME && (e instanceof Label || e instanceof VBox || e instanceof Button)) || screen != Screens.GAME) {
                    e.setScaleX(SettingsImpl.getSettings().getScaleFactor());
                    e.setScaleY(SettingsImpl.getSettings().getScaleFactor());
                }
            });
            scene = new Scene(root);
            scene.getStylesheets().add(STYLE_CSS_PATH);

            stage.setScene(scene);

            stage.setResizable(false);
            stage.getIcons().add(new Image(LOGO_PATH));

            if (!SettingsImpl.getSettings().isFullScreen()) {
                stage.sizeToScene();
                stage.centerOnScreen();
            }

            if (!stage.isShowing()) {
                stage.show();
            }

            switch (screen) {
            case LOGIN:
                final LoginSceneController log = (LoginSceneController) controller;
                log.setView(this.view);
                break;
            case REGISTER:
                final RegisterSceneController reg = (RegisterSceneController) controller;
                reg.setView(this.view);
                break;
            case GAME:
                this.addEventHandlers(stage);
                if (!this.view.isPaused()) {
                    this.drawBackground((GameSceneController) controller);
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

    private void addEventHandlers(final Stage stage) {
        stage.getScene().addEventHandler(KeyEvent.KEY_PRESSED, e -> {

            Optional<CommandType> commandType = Optional.empty();
            switch (e.getCode()) {
            case ESCAPE:
                commandType = Optional.of(CommandType.PAUSE);
                break;
            case R:
                commandType = Optional.of(CommandType.RECHARGE);
                break;
            default:
                break;
            }
            commandType.ifPresent(command -> this.view.getController().notifyCommand(command, 0, 0));
        });

        stage.getScene().addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
            Optional<CommandType> commandType = Optional.empty();
            if (e.getEventType().equals(MouseEvent.MOUSE_PRESSED)) {
                commandType = Optional.ofNullable(CommandType.SHOOT);
            }
            commandType.ifPresent(command -> this.view.getController().notifyCommand(command, e.getX(), e.getY()));
        });
    }

    private void drawBackground(final GameSceneController controller) {
        final ImageView backgroundImage = new ImageView(new Image(getClass().getResourceAsStream("/view/backgrounds/gameBackground.png"),
                                                        SettingsImpl.getSettings().getSelectedResolution().getKey(),
                                                        SettingsImpl.getSettings().getSelectedResolution().getValue(), false, false));
        controller.getCanvas().getGraphicsContext2D().drawImage(backgroundImage.getImage(), 0, 0,
                                                        SettingsImpl.getSettings().getSelectedResolution().getKey(),
                                                        SettingsImpl.getSettings().getSelectedResolution().getValue());
        backgroundImage.setPreserveRatio(true);
    }
}
