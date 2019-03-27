package view.scenecontroller;

import javafx.fxml.FXML;

/**
 * 
 * Factory the shared methods of the secondary menu.
 */
public abstract class AbstractSecondarySceneController extends AbstractSceneController {

    @FXML
    protected void backToMenu() {
        super.getSceneFactory().openMenuScene();
    }
}
