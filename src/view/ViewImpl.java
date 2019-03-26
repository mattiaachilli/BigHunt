package view;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import controller.Controller;
import controller.ControllerImpl;
import controller.matches.GameMode;
import javafx.application.Application;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.achievements.Achievement;
import model.data.HighScore;
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
public class ViewImpl extends Application implements View {

    private static final int GREEN_SEMAPHORE = 1;

    private final Controller controller;
    private Render render;
    private List<ViewEntity> viewEntities;
    private MatchData matchData;
    private final Semaphore mutex;
    private static final String GAME_TITLE = "BIG HUNT";
    private final SceneFactory sceneFactory;
    private List<Achievement> achievements;
    private boolean gamePaused;

    /**
     * Constructor of the view of the application.
     */
    public ViewImpl(final Controller controller) {
        super();
        this.controller = controller;
        this.achievements = new ArrayList<>();
        this.mutex = new Semaphore(GREEN_SEMAPHORE);
        this.sceneFactory = new SceneFactoryImpl(this);
    }

    @Override
    public final void start(final Stage primaryStage) throws Exception {
        primaryStage.setTitle(GAME_TITLE);
        primaryStage.setOnCloseRequest(e -> Runtime.getRuntime().exit(0));
        this.sceneFactory.setStage(primaryStage);
        this.sceneFactory.openAccountSelectionScene();
        //this.sceneFactory.openMenuScene();
        //load images
    }

    @Override
    public final void viewLauncher() {
       launch();
    }

    @Override
    public final void startGame(final GameSceneController gameSceneController, final GameMode gameMode) {
        this.render = new Render(gameSceneController, gameMode);
        this.startRender();
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
    public final void render(final List<ViewEntity> viewEntities, final MatchData matchData) {
        try {
            this.mutex.acquire();
            this.viewEntities = viewEntities;
            this.matchData = matchData;
            this.mutex.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void resetGame() {
        // TODO Auto-generated method stub

    }

    @Override
    public void setHighScores() {
        // TODO Auto-generated method stub

    }

    @Override
    public void setAchievements(List<Achievement> achievements) {
        this.achievements = achievements;        
    }

    @Override
    public List<HighScore> getHighScores() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SceneFactory getSceneFactory() {
        return this.sceneFactory;
    }

    /**
     * 
     * Thread that render graphics independent from game loop.
     *
     */
    private class Render extends Thread {
        private static final int MILLIS_FROM_SECOND = 1000;

        private boolean running;
        private final int period;
        private List<ViewEntity> viewEntitiesGame;
        private MatchData currentMatchData;
        private final GameSceneController gameSceneController;
        private final GameMode gameMode;
        private final GraphicsContext gamecanvas;

        public Render(final GameSceneController gameSceneController, final GameMode gameMode) {
            super();
            this.period = MILLIS_FROM_SECOND / SettingsImpl.getSettings().getSelectedFPS();
            this.gameSceneController = gameSceneController;
            this.gameMode = gameMode;
            this.gamecanvas = this.gameSceneController.getCanvas().getGraphicsContext2D();
            this.running = true;
        }

        @Override
        public final void run() {
            if (gamePaused) {
                controller.startGame();
            } else {
                gamePaused = true;
                controller.initGame(this.gameMode);
                controller.startGame();
            }

            while (this.running) {
                final long currentTime = System.currentTimeMillis();
                try {
                    mutex.acquire();
                    this.viewEntitiesGame = viewEntities;
                    this.currentMatchData = matchData;
                    mutex.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                for (final ViewEntity viewEntity : this.viewEntitiesGame) {
                    if (viewEntity.getShape() instanceof Rectangle) {
                        final Rectangle rectangle = (Rectangle) viewEntity.getShape();
                        this.gamecanvas.drawImage(viewEntity.getPicture(), viewEntity.getPosition().getX(), viewEntity.getPosition().getY(),
                        rectangle.getWidth(), rectangle.getHeight());
                    }
                }

                Utilities.waitForNextFrame(period, currentTime);
            }
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
    public void closeGame(MatchData matchData, boolean isHighScores) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<Achievement> getAchievements() {
        return this.achievements;
    }
}
