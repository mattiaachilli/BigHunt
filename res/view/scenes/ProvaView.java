package view.scenes;
import com.sun.org.glassfish.external.statistics.annotations.Reset;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

public class ProvaView extends Application {
            @Override
            public void start(Stage primaryStage) {
                    try {
                        BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Menu.fxml"));
                        Scene scene = new Scene(root,800,600);
                        scene.getStylesheets().add(getClass().getResource("prova.css").toExternalForm());
                            primaryStage.setScene(scene);
                            primaryStage.show();
                    } catch(Exception e) {
                            e.printStackTrace();
                    }
            }
            
            public static void main(String[] args) {
                    launch(args);
            }
}

