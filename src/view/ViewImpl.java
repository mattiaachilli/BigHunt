package view;

import java.util.List;
import java.util.Map;

import controller.Controller;
import javafx.stage.Stage;
import model.achievements.Achievement;
import model.achievements.AchievementType;
import model.data.HighScore;
import view.scenefactory.SceneFactory;
import view.scenefactory.SceneFactoryImpl;

/**
 * 
 * Implements starting game methods.
 *
 */
public class ViewImpl implements View {

    private static final String GAME_TITLE = "BIG HUNT";

    private final SceneFactory sceneFactory;
    private final Stage stage;
    private Controller controller;

    /**
     * Constructor.
     * 
     * @param primaryStage the stage received from the application launcher
     */
    public ViewImpl(final Stage primaryStage) {
        super();

        this.stage = primaryStage;
        this.sceneFactory = new SceneFactoryImpl(this);
    }

    @Override
    public void viewLauncher(final Controller controller) {
        this.controller = controller;
        this.stage.setTitle(GAME_TITLE);
        this.stage.setOnCloseRequest(e -> Runtime.getRuntime().exit(0));
        this.sceneFactory.setStage(this.stage);
        this.sceneFactory.openMenuScene();

        /*this.stage.setMinWidth(MIN_WIDTH);
        this.stage.setMinHeight(MIN_HEIGHT);
        this.stage.setMaximized(true);
        this.loadScene(GameScene.MAIN_MENU);
        ImageLoader.getLoader().loadAll();

        Metodi di view per istanziare lo stage
        visti da altro codice

        */
    }

    @Override
    public void startGame() {
        // TODO Auto-generated method stub

    }

    @Override
    public void startViewRender() {
        // TODO Auto-generated method stub

    }

    @Override
    public void stopViewRender() {
        // TODO Auto-generated method stub

    }

    @Override
    public void closeGame() {
        // TODO Auto-generated method stub

    }

    @Override
    public void resetGame() {
        // TODO Auto-generated method stub

    }

    @Override
    public void setStateGame() {
        // TODO Auto-generated method stub

    }

    @Override
    public void setHighScores() {
        // TODO Auto-generated method stub

    }

    @Override
    public Map<AchievementType, Achievement> getAchievements() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setAchievements(final Map<AchievementType, Achievement> achievements) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<HighScore> getHighScores() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SceneFactory getSceneFactory() {
        // TODO Auto-generated method stub
        return null;
    }
}
