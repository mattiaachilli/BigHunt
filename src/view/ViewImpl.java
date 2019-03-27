package view;

import java.util.List;
import java.util.Map;

import controller.Controller;
import javafx.stage.Stage;
import model.achievements.Achievement;
import model.achievements.AchievementType;
import model.data.HighScore;
import model.data.Podium;
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
    private Map<AchievementType, Achievement> achievements;
    private Podium storyPodium;
    private Podium survivalPodium;

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

        /*
         * this.stage.setMinWidth(MIN_WIDTH); this.stage.setMinHeight(MIN_HEIGHT);
         * this.stage.setMaximized(true); this.loadScene(GameScene.MAIN_MENU);
         * ImageLoader.getLoader().loadAll();
         * 
         * Metodi di view per istanziare lo stage visti da altro codice
         * 
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
    public Map<AchievementType, Achievement> getAchievements() {
        return this.achievements;
    }

    @Override
    public void setAchievements(final Map<AchievementType, Achievement> achievements) {
        this.achievements = achievements;
    }

    @Override
    public SceneFactory getSceneFactory() {
        // TODO Auto-generated method stub
        return null;
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
}
