package view;

import java.util.List;

import controller.matches.GameMode;
import javafx.application.Application;
import javafx.stage.Stage;
import model.data.HighScore;
import model.data.MatchData;
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

    private static final String GAME_TITLE = "BIG HUNT";
    private final SceneFactory sceneFactory;

    /**
     * Constructor of the view of the application.
     */
    public ViewImpl() {
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
    public void startViewRender() {
        // TODO Auto-generated method stub

    }

    @Override
    public void stopViewRender() {
        // TODO Auto-generated method stub

    }

    @Override
    public void render(final List<ViewEntity> viewEntities, final MatchData matchData) {
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
    public void setAchievements() {
        // TODO Auto-generated method stub

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

    @Override
    public void startGame(GameSceneController gameSceneController, GameMode gameMode) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void closeGame(MatchData matchData, boolean isHighScores) {
        // TODO Auto-generated method stub
        
    }
}
