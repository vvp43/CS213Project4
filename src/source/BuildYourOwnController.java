package source;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class BuildYourOwnController {

    @FXML
    private Button addButton;

    @FXML
    private Button addToppingsButton;

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
    private ComboBox<String> pizzaPicker;

    @FXML
    private TextField priceBox;

    @FXML
    private Button removeToppingsButton;

    @FXML
    private ToggleGroup sizeToggleGroup;

    @FXML
    private RadioButton smallButton;

    @FXML
    private ListView<Topping> toppingsListAdded;

    @FXML
    private ListView<Topping> toppingsListChoose;
    /**
     * initialize: initial method run at the creation of controller.
     */
    @FXML
    void initialize(){
        pizzaImage.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/resources/delicious.jpg"))));
        Topping[] tops = Topping.values();
        for(Topping a : tops){
            toppingsListChoose.getItems().add(a);
        }

        Sauce[] sa = Sauce.values();
        for(Sauce a : sa){
            pizzaPicker.getItems().add(String.valueOf(a));
        }

        EventHandler<ActionEvent> buttonHandler = event -> {
            calculatePizzaPrice();
        };
        largeButton.setOnAction(buttonHandler);
        mediumButton.setOnAction(buttonHandler);
        smallButton.setOnAction(buttonHandler);
        extraCheeseButton.setOnAction(buttonHandler);
        extraSauceButton.setOnAction(buttonHandler);
        pizzaPicker.setOnAction(buttonHandler);
    }

    /**
     * chooseStuff: helper method used to choose properties of build your own pizza object.
     * @param buildPizza BYOP pizza to update properties to
     */
    private void chooseStuff(Pizza buildPizza){
        if (smallButton.isSelected()) {
            buildPizza.size = Size.SMALL;
        } else if (mediumButton.isSelected()) {
            buildPizza.size = Size.MEDIUM;
        } else if (largeButton.isSelected()) {
            buildPizza.size = Size.LARGE;
        }
        if (extraSauceButton.isSelected()) {
            buildPizza.extraCheese = true;
        }
        if (extraCheeseButton.isSelected()) {
            buildPizza.extraSauce = true;
        }
    }

    /**
     * addOrder: Reads attributes of a given build your own pizza inputted from the GUI and generates
     * a pizza object and adds it to the current order.
     */
    @FXML
    void addOrder(ActionEvent event) {
        if (smallButton.isSelected() || mediumButton.isSelected() || largeButton.isSelected()) {
            if(pizzaPicker.getValue() != null){
                String pizzaType = pizzaPicker.getValue().toLowerCase();
                if(toppingsListAdded.getItems().size() >= 3) {
                    if(toppingsListAdded.getItems().size() <= 7) {
                        Pizza buildPizza = PizzaMaker.createPizza("buildyourown");
                        buildPizza.toppings = new ArrayList<>(toppingsListAdded.getItems());
                        chooseStuff(buildPizza);
                        if (pizzaType.equals("alfredo")) {
                            buildPizza.sauce = Sauce.ALFREDO;
                        } else if (pizzaType.equals("tomato")) {
                            buildPizza.sauce = Sauce.TOMATO;
                        }
                        if (PizzaOrdersController.getOrderForApproval() == null) {
                            PizzaOrdersController.createOrder();
                        }
                        Order ExistingOrder = PizzaOrdersController.getOrderForApproval();
                        ExistingOrder.addPizza(buildPizza);
                        showSuccessPopup("Success: Build Your Own Pizza Added to Order!");
                    } else {
                        showErrorPopup("Error: Maximum of 7 toppings!");
                    }
                } else {
                    showErrorPopup("Error: Choose at least 3 toppings!");
                }
            } else {
                showErrorPopup("Error: Select Sauce!");
                // error not enough toppings
            }
        } else {
            showErrorPopup("Error: Select Size!");
        }
    }
    /**
     * showErrorPopup: Method used to generate Error popups
     * @param message error message to display
     */
    private void showErrorPopup(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    /**
     * showSuccessPopup: Method used to generate Confirmation popups
     * @param message confirmation message to display
     */
    private void showSuccessPopup(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * addToppings: Handles adding toppings to selected toppings, and removing from addable toppings.
     */
    public void addToppings(){
        Topping topToAdd = toppingsListChoose.getSelectionModel().getSelectedItem();
        if(topToAdd != null){
            if(!toppingsListAdded.getItems().contains(topToAdd)){
                toppingsListChoose.getItems().remove(topToAdd);
                toppingsListAdded.getItems().add(topToAdd);
            }
        }
        calculatePizzaPrice();
    }
    /**
     * removeToppings: Handles removing toppings from selected toppings, and adding to addable toppings.
     */
    public void removeToppings(){
        Topping topToAdd = toppingsListAdded.getSelectionModel().getSelectedItem();
        if(topToAdd != null){
            toppingsListAdded.getItems().remove(topToAdd);
            toppingsListChoose.getItems().add(topToAdd);
        }
        calculatePizzaPrice();
    }
    /**
     * calculatePizzaPrice: calculates price of Pizza based on realtime inputs, and prints it to
     * the GUI whenever any feature is adjusted.
     */
    private void calculatePizzaPrice(){
        DecimalFormat df = new DecimalFormat("#,##0.00");
        double total = 0;
        String selectedPizza = pizzaPicker.getValue();
        if(selectedPizza != null) {
            if(smallButton.isSelected()){
                total = 8.99;
            } else if(mediumButton.isSelected()){
                total = 8.99+2;
            } else if(largeButton.isSelected()){
                total = 8.99+2+2;
            } else {
                total += 0;
            }
            if(extraCheeseButton.isSelected()){
                total+=1;
            }
            if(extraSauceButton.isSelected()){
                total+=1;
            }
            for(int i = 0; i < toppingsListAdded.getItems().size(); i++){
                if(i > 2){
                    total += 1.49;
                }
            }
        }
        priceBox.setText(df.format(total));
    }

}
