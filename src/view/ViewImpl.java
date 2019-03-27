package view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import controller.Controller;
import javafx.stage.Stage;
import model.achievements.Achievement;
import model.achievements.AchievementType;
import model.data.Podium;
import java.util.concurrent.Semaphore;

import controller.matches.GameMode;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.shape.Rectangle;
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
public class ViewImpl implements View {

    private static final int GREEN_SEMAPHORE = 1;

    private Controller controller;
    private Render render;
    private List<ViewEntity> viewEntities;
    private MatchData matchData;
    private final Semaphore mutex;
    private static final String GAME_TITLE = "BIG HUNT";

    private final SceneFactory sceneFactory;
    private Stage stage;
    private Map<AchievementType, Achievement> achievements;
    private List<HighScore> survivalModeHighsScores;
    private List<HighScore> storyModeHighsScores;
    private Podium storyPodium;
    private Podium survivalPodium;
    private boolean gamePaused;

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
    }

    @Override
    public void viewLauncher(final Controller controller) {
        this.controller = controller;
        this.stage.setTitle(GAME_TITLE);
        this.stage.setOnCloseRequest(e -> Runtime.getRuntime().exit(0));
        this.sceneFactory.setStage(this.stage);
        //this.sceneFactory.openMenuScene();
        this.sceneFactory.openAccountSelectionScene();
    }

    @Override
    public final void startGame(final GameSceneController gameSceneController, final GameMode gameMode) {
        this.render = new Render(gameSceneController, gameMode);
        controller.initGame(gameMode);
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
    public void closeGame(final MatchData matchData, final boolean isHighScores) {
        // TODO Auto-generated method stub

    }

    @Override
    public Map<AchievementType, Achievement> getAchievements() {
        return null;
        //return this.achievements;
    }

    @Override
    public void setAchievements(final Map<AchievementType, Achievement> achievements) {
        //this.achievements = achievements;
    }

    @Override
    public void setStoryPodium(final Podium podium) {
        this.storyPodium = podium;
    }

    @Override
    public void setSurvivalPodium(final Podium podium) {
        this.survivalPodium = podium;
    }

    @Override
    public Podium getStoryPodium() {
        // TODO Auto-generated method stub
        return this.storyPodium;
    }

    @Override
    public Podium getSurvivalPodium() {
        // TODO Auto-generated method stub
        return this.survivalPodium;
    }
    
    @Override
    public MatchData getMatchData() {
        return this.matchData;
    }
    
    @Override
    public List<HighScore> getStoryHighScores() {
        return this.storyModeHighsScores;
    }

    @Override
    public List<HighScore> getSurvivalHighScores() {
        return this.survivalModeHighsScores;
    }
    
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
        private final ImageView backgroundImage;

        Render(final GameSceneController gameSceneController, final GameMode gameMode) {
            super();
            this.period = MILLIS_FROM_SECOND / SettingsImpl.getSettings().getSelectedFPS();
            this.gameSceneController = gameSceneController;
            this.gameMode = gameMode;
            this.gamecanvas = this.gameSceneController.getCanvas().getGraphicsContext2D();
            this.running = true;
            this.backgroundImage = new ImageView();
            this.backgroundImage
                    .setImage(new Image(getClass().getResourceAsStream("/view/backgrounds/gameBackground.png")));
        }

        @Override
        public final void run() {            
            controller.startGameLoop();
            
            while (this.running) {
                try {
                    mutex.acquire();
                    this.viewEntitiesGame = viewEntities;
                    this.currentMatchData = matchData;
                    mutex.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
                final long currentTime = System.currentTimeMillis();
                
                this.gamecanvas.drawImage(this.backgroundImage.getImage(), 0, 0, 1366, 768);
               
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

}

