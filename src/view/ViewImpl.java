package view;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import controller.Controller;
import controller.input.CommandType;
import javafx.application.Platform;
import javafx.scene.ImageCursor;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.achievements.Achievement;
import model.achievements.AchievementType;
import model.data.Podium;
import model.gun.Magazine;
import model.matches.GameMode;

import java.util.concurrent.Semaphore;

import javax.swing.ImageIcon;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import model.data.MatchData;
import settings.SettingsImpl;
import utility.Utilities;
import view.entities.ViewEntity;
import view.scenecontroller.GameSceneController;
import view.scenefactory.SceneFactory;
import view.scenefactory.SceneFactoryImpl;

/**
 * 
 * Implements starting game methods.
 *
 */
public class ViewImpl implements View {

    private static final int GREEN_SEMAPHORE = 1;

    private Controller controller;
    private Render render;
    private List<Optional<ViewEntity>> viewEntities;
    private MatchData matchData;
    private final Semaphore mutex;
    private static final String GAME_TITLE = "BIG HUNT";

    private final SceneFactory sceneFactory;
    private Stage stage;
    private Map<AchievementType, Achievement> achievements;
    private Podium storyPodium;
    private Podium survivalPodium;
    private boolean gamePaused;
    private Magazine magazine;
    private int infoLimit;
    private GameMode gameMode;

    /**
     * Constructor.
     * 
     * @param primaryStage the stage received from the application launcher
     */
    public ViewImpl(final Stage primaryStage) {
        super();
        this.stage = primaryStage;
        this.achievements = new HashMap<>();
        this.mutex = new Semaphore(GREEN_SEMAPHORE);
        this.sceneFactory = new SceneFactoryImpl(this);
        this.gamePaused = true;
    }

    @Override
    public final void viewLauncher(final Controller controller) {
        this.controller = controller;
        this.stage.setTitle(GAME_TITLE);
        this.stage.setOnCloseRequest(e -> Runtime.getRuntime().exit(0));
        this.sceneFactory.setStage(this.stage);
        this.sceneFactory.openAccountSelectionScene();
    }

    @Override
    public final void startGame(final GameSceneController gameSceneController, final GameMode gameMode) {
        this.reset();
        this.gameMode = gameMode;
        this.render = new Render(gameSceneController, gameMode);
        controller.initGame(gameMode);
        this.startRender();
    }

    @Override
    public final GameMode getActualGameMode() {
        return this.gameMode;
    }

    @Override
    public final void reset() {
        this.gamePaused = true;
    }

    @Override
    public final void startRender() {
        this.render.start();
    }

    @Override
    public final void stopRender() {
        this.render.stopRender();
    }

    @Override
    public final void render(final List<Optional<ViewEntity>> viewEntities, final MatchData matchData, final Magazine magazine,
                            final int info) {
        try {
            this.mutex.acquire();
            this.viewEntities = viewEntities;
            this.matchData = matchData;
            this.magazine = magazine;
            this.infoLimit = info;
            this.mutex.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public final void closeGame(final MatchData matchData, final boolean isHighScores) {
        this.matchData = matchData;
        this.render.endGame();
        this.reset();
    }

    @Override
    public final Map<AchievementType, Achievement> getAchievements() {
        return this.achievements;
    }

    @Override
    public final void setAchievements(final Map<AchievementType, Achievement> achievements) {
        this.achievements = achievements;
    }

    @Override
    public final void setStoryPodium(final Podium podium) {
        this.storyPodium = podium;
    }

    @Override
    public final void setSurvivalPodium(final Podium podium) {
        this.survivalPodium = podium;
    }

    @Override
    public final Podium getStoryPodium() {
        return this.storyPodium;
    }

    @Override
    public final Podium getSurvivalPodium() {
        return this.survivalPodium;
    }

    @Override
    public final MatchData getMatchData() {
        return this.matchData;
    }

    @Override
    public final SceneFactory getSceneFactory() {
        return this.sceneFactory;
    }

    /**
     * 
     * Thread that render graphics independent from game loop.
     *
     */
    private class Render extends Thread {
        private static final int MILLIS_FROM_SECOND = 1000;

        private volatile boolean running, end;
        private final int period;
        private List<Optional<ViewEntity>> viewEntitiesGame;
        private MatchData currentMatchData;
        private final GameSceneController gameSceneController;
        private final GraphicsContext gamecanvas;
        private final ImageView backgroundImage;
        private Magazine currentMagazine;
        private final GameMode gameMode;
        private int info;

        Render(final GameSceneController gameSceneController, final GameMode gameMode) {
            super();
            this.period = MILLIS_FROM_SECOND / SettingsImpl.getSettings().getSelectedFPS();
            this.gameSceneController = gameSceneController;
            this.gamecanvas = this.gameSceneController.getCanvas().getGraphicsContext2D();
            this.running = true;
            Image cursorImage = new Image(getClass().getResourceAsStream("/view/weapon/gunsight.png"));
            this.gamecanvas.getCanvas().setCursor(new ImageCursor(cursorImage, cursorImage.getWidth() / 2, cursorImage.getHeight() / 2));
            this.gameMode = gameMode;

            this.backgroundImage = new ImageView(
            new Image(getClass().getResourceAsStream("/view/backgrounds/gameBackground.png"),
            SettingsImpl.getSettings().getSelectedResolution().getKey(),
            SettingsImpl.getSettings().getSelectedResolution().getValue(), false, false));

            ViewImpl.this.sceneFactory.getStage().addEventHandler(KeyEvent.KEY_PRESSED, e -> {
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
                commandType.ifPresent(command -> controller.notifyCommand(command, 0, 0));
            });

            ViewImpl.this.sceneFactory.getStage().addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
                Optional<CommandType> commandType = Optional.empty();
                if (e.getEventType().equals(MouseEvent.MOUSE_PRESSED)) {
                    commandType = Optional.ofNullable(CommandType.SHOOT);
                }
                commandType.ifPresent(command -> controller.notifyCommand(command, e.getX(), e.getY()));
            });
        }

        @Override
        public final void run() { 
            if (!gamePaused) {
                controller.startGameLoop();
            } else {
                gamePaused = false;
                controller.initGame(this.gameMode);
                controller.initGameLoop();
                controller.startGameLoop();
            }

            while (this.running) {
                try {
                    mutex.acquire();
                    this.viewEntitiesGame = viewEntities;
                    this.currentMatchData = matchData;
                    this.currentMagazine = magazine;
                    this.info = infoLimit;
                    mutex.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                final long currentTime = System.currentTimeMillis();

                Platform.runLater(() -> {
                    this.updateBackground();

                    this.gameSceneController.setGameData(this.currentMatchData, this.currentMagazine, this.info);

                    for (final Optional<ViewEntity> viewEntity : this.viewEntitiesGame) {
                        if (viewEntity.isPresent() && viewEntity.get().getShape() instanceof Rectangle) {
                            final ViewEntity ve = viewEntity.get();
                            final Rectangle rectangle = (Rectangle) ve.getShape();
                            this.gamecanvas.drawImage(ve.getPicture(), ve.getPosition().getX(), ve.getPosition().getY(),
                            rectangle.getWidth(), rectangle.getHeight());
                        }
                    }
                });
                Utilities.waitForNextFrame(period, currentTime);
            }

            if (this.end) {
                Platform.runLater(() -> {
                    sceneFactory.openGameOverScene();
                });
            }
        }

        private void updateBackground() {
            this.gamecanvas.drawImage(this.backgroundImage.getImage(), 0, 0,
                                      SettingsImpl.getSettings().getSelectedResolution().getKey(),
                                      SettingsImpl.getSettings().getSelectedResolution().getValue());
            this.backgroundImage.setPreserveRatio(true);
        }

        public final void endGame() {
            this.end = true;
        }

        public final void stopRender() {
            this.running = false;
        }

        @Override
        public void start() {
            this.running = true;
            super.start();
        }
    }

    @Override
    public final Controller getController() {
        return this.controller;
    }
}
