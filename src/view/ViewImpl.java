package view;

import java.util.List;

import controller.matches.GameMode;
import javafx.application.Application;
import javafx.stage.Stage;
import model.data.HighScore;
import model.data.MatchData;
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
    
    public ViewImpl() {
        this.sceneFactory = new SceneFactoryImpl(this);
    }
    
    @Override
    public void start(final Stage primaryStage) throws Exception {
        primaryStage.setTitle(GAME_TITLE);
        primaryStage.setOnCloseRequest(e -> Runtime.getRuntime().exit(0));
        this.sceneFactory.setStage(primaryStage);
        this.sceneFactory.openMenuScene();
        //load images
    }
    
    @Override
    public void viewLauncher() {
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
    public void resetGame() {
        // TODO Auto-generated method stub

    }

    @Override
    public void setStateGame() {
        // TODO Auto-generated method stub

    }

    @Override
    public void serHighScores() {
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
