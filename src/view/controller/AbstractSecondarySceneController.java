package view.controller;

/**
 * 
 * Factory the shared methods of the secondary menu
 */
public abstract class AbstractSecondarySceneController extends AbstractSceneController {

    protected void backToMenu() {
        super.getSceneFactory().openMenuScene();
    }
}
