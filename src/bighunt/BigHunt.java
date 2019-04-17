package bighunt;

import audio.SoundUtil;
import controller.Controller;
import controller.ControllerImpl;
import controller.files.FilesHomeManagerUtils;
import javafx.application.Application;
import javafx.stage.Stage;
import model.ModelImpl;
import view.View;
import view.ViewImpl;

/**
 * 
 * 
 * Provides methods to initialize the game.
 */

public final class BigHunt extends Application {

    /**
     * 
     * The main of the application.
     * 
     * @param args arguments.
     */
    public static void main(final String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage primaryStage) throws Exception {
        FilesHomeManagerUtils.setupApplication();
        SoundUtil.initAudio();
        final View view = new ViewImpl(primaryStage);
        final Controller controller = new ControllerImpl(() -> new ModelImpl(), view);
        view.viewLauncher(controller);
    }
}
