package bighunt;

import controller.Controller;
import controller.ControllerImpl;
import controller.files.FilesHomeManager;
import javafx.application.Application;
import javafx.stage.Stage;
import model.ModelImpl;
import view.View;
import view.ViewImpl;

/**
 * 
 * 
 * @author mattia Provides methods to initialize the game.
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
        FilesHomeManager.setupApplication();
        final View view = new ViewImpl(primaryStage);
        final Controller controller = new ControllerImpl(() -> new ModelImpl(), view);
        view.viewLauncher(controller);
    }
}
