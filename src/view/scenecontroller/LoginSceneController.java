package view.scenecontroller;

import view.View;

/**
 *
 * interface for login scene controller.
 */
public interface LoginSceneController {

    /**
     *
     * @param view the view to be set in order to charge the scene
     */
    void setView(View view);

    /**
     * Call the login method.
     */
    void callLogin();
}
