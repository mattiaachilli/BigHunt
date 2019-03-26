package bighunt;

import java.util.function.Supplier;

import controller.Controller;
import controller.ControllerImpl;
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
    public final void start(final Stage primaryStage) throws Exception {
        // TODO Auto-generated method stub
        //FilesHomeManager.setupApplication();
        final Supplier<Model> modelSupplier = new Supplier<Model>() {
            @Override
            public Model get() {
                return new ModelImpl();
            }
        };
        final View view = new ViewImpl(controller);
        final Controller controller = new ControllerImpl(modelSupplier, view);
    }
}
