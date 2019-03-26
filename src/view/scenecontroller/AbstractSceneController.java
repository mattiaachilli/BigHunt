package view.scenecontroller;

import view.scenefactory.SceneFactory;

/**
 * this class factories the shared methods of the scenes.
 *
 */
public abstract class AbstractSceneController implements SceneController {
    private SceneFactory sceneFactory;
    
    @Override
    public void setSceneFactory(final SceneFactory sceneFactory) {
        this.sceneFactory = sceneFactory;
    }
    
    @Override
    public SceneFactory getSceneFactory() {
        return this.sceneFactory;
    }
    
}
