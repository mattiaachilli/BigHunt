package view.scenecontroller;

import view.scenefactory.SceneFactory;

/**
 * this class factorises the shared methods of the scenes.
 *
 */
public abstract class AbstractSceneController implements SceneController {
    private SceneFactory sceneFactory;

    @Override
    public final void setSceneFactory(final SceneFactory sceneFactory) {
        this.sceneFactory = sceneFactory;
    }

    @Override
    public final SceneFactory getSceneFactory() {
        return this.sceneFactory;
    }

}
