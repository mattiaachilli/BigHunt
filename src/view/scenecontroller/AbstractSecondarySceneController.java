package view.scenecontroller;

import javafx.fxml.FXML;

/**
 *
 * Factory the shared methods of the secondary menus.
 */
public abstract class AbstractSecondarySceneController extends AbstractSceneController {

    /**
     * return to main menu page.
     */
    @FXML
    protected void backToMenu() {
        super.getSceneFactory().openMenuScene();
    }
}
