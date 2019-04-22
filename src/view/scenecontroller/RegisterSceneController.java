package view.scenecontroller;

import view.View;

/**
 * 
 * Interface for register scene controller.
 */
public interface RegisterSceneController {

    /**
     * 
     * @param view the view to be set in order to charge the scene.
     */
    void setView(View view);

    /**
     * Call the register method.
     */
    void callRegister();
}
