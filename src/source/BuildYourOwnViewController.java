package source;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;

public class BuildYourOwnViewController {

    @FXML
    private Button addButton;

    @FXML
    private CheckBox extraCheeseButton;

    @FXML
    private CheckBox extraSauceButton;

    @FXML
    private RadioButton largeButton;

    @FXML
    private RadioButton mediumButton;

    @FXML
    private ImageView pizzaImage;

    @FXML
    private ComboBox<?> pizzaPicker;

    @FXML
    private TextField priceBox;

    @FXML
    private TextField saucePicker;

    @FXML
    private ToggleGroup sizeToggleGroup;

    @FXML
    private RadioButton smallButton;

    @FXML
    private ListView<?> toppingsList;

}
