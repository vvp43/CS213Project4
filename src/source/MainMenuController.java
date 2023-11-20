package source;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController {

    @FXML
    private Button BuildPizzaButton;

    @FXML
    private Button currentOrderButton;

    @FXML
    private Button orderHistoryButton;

    @FXML
    private Button specialtyPizzaButton;

    @FXML
    void initialize(){

    }

    private Stage secondaryStage;
    public void openSecondaryScene() {
        if (secondaryStage == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("BuildYourOwnView.fxml"));
                Parent root = loader.load();
                secondaryStage = new Stage();
                secondaryStage.setTitle("Choose Your Speciality Pizza");
                secondaryStage.setScene(new Scene(root, 600, 650));
                secondaryStage.setOnCloseRequest(event -> secondaryStage = null);

                secondaryStage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
