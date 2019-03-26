package view.sceneloader;

import controller.matches.GameMode;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import view.View;
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
    public void loadScene(Stage stage, Screens screen, GameMode gameMode) {
        final Region root;
        final FXMLLoader loader = new FXMLLoader();
        
        try {
            loader.setLocation(getClass().getResource(screen.getPath()));
            
            final SceneController controller = loader.getController();
            //da implementare view 
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

}
