package edu.ib;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * CrazyEights class definition
 */
public class CrazyEights extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Overriden start method
     *
     * @param stage the primary Stage of the programme
     * @throws IOException exception shown by programme
     */
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/crazy_eights.fxml"));
        Scene scene = new Scene(root, 1000, 700);
        stage.setScene(scene);
        stage.show();
    }
}
