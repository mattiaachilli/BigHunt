package bighunt;

import java.util.function.Supplier;

import controller.Controller;
import controller.ControllerImpl;
import controller.files.FilesHomeManager;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Model;
import model.ModelImpl;
import view.View;
import view.ViewImpl;

/**
 * 
 * 
 * @author mattia Provides methods to initialize the game.
 */

public class BigHunt extends Application {
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
        // TODO Auto-generated method stub
        //FilesHomeManager.setupApplication();
        final View view = new ViewImpl();
        final Model model = new ModelImpl();
        final Supplier<Model> modelSupplier = model;
        final Controller controller = new ControllerImpl(model, view);
    }
}
