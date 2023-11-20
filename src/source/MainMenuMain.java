package source;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;

public class MainMenuMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainMenuMain.class.getResource("MainMenuView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        scene.setFill(Color.LAVENDER);
        stage.setResizable(false);
        stage.setTitle("Project 4 - RU Pizzeria Manager");
        stage.setScene(scene);
        stage.show();
    }
}
