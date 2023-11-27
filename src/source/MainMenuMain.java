package source;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;

public class MainMenuMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * start: sets program, sets scene, launches program
     * @param stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainMenuMain.class.getResource("MainMenuView.fxml"));
        MainMenuController controller1 = fxmlLoader.getController();
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);

        stage.setResizable(false);
        stage.setTitle("Project 4 - RU Pizzeria Manager");
        stage.setScene(scene);
        stage.show();
    }
}
