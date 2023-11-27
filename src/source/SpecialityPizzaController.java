package source;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class SpecialityPizzaController {

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
    private ComboBox<String> pizzaPicker;

    @FXML
    private TextField priceBox;

    @FXML
    private TextField saucePicker;

    @FXML
    private ToggleGroup sizeToggleGroup;

    @FXML
    private RadioButton smallButton;

    @FXML
    private ListView<String> toppingsList;

    /**
     * initialize: initial method run at the creation of controller.
     */
    @FXML
    void initialize() {
        // Create and set the ListView reference
        StoreOrders storeOrders = new StoreOrders().getInstance();
        saucePicker.setEditable(false);
        priceBox.setEditable(false);
        pizzaPicker.getItems().addAll("Deluxe", "Supreme", "Meatzza", "Seafood", "Pepperoni");

        pizzaPicker.setOnAction(event -> onSpecialtySelected());

        EventHandler<ActionEvent> buttonHandler = event -> {
            calculatePizzaPrice();
        };
        largeButton.setOnAction(buttonHandler);
        mediumButton.setOnAction(buttonHandler);
        smallButton.setOnAction(buttonHandler);
        extraCheeseButton.setOnAction(buttonHandler);
        extraSauceButton.setOnAction(buttonHandler);

        addButton.setOnAction(event -> addOrder());
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
            switch (selectedPizza) {
                case "Deluxe" -> total = 14.99;
                case "Supreme" -> total = 15.99;
                case "Meatzza" -> total = 16.99;
                case "Seafood" -> total = 17.99;
                case "Pepperoni" -> total = 10.99;

                default -> total = 0;
            }
            if(smallButton.isSelected()){
                total +=0;
            } else if(mediumButton.isSelected()){
                total += 2.00;
            } else if(largeButton.isSelected()){
                total += 4;
            } else {
                total += 0;
            }
            if(extraCheeseButton.isSelected()){
                total+=1;
            }
            if(extraSauceButton.isSelected()){
                total+=1;
            }
        }
        priceBox.setText(df.format(total));
    }
    /**
     * onSpecialtySelected: Updates the toppingsList, sauce, and picture of chosen specialty pizza whenever the combobox
     * for choosing pizza type is interacted with.
     */
    private void onSpecialtySelected() {
        String pizzaType = pizzaPicker.getValue().toLowerCase();
        //System.out.println(pizzaType);
        ArrayList<String> tops = null;
        toppingsList.getItems().clear();
        switch(pizzaType) {
            case "deluxe" -> {
                tops = new ArrayList<>(Arrays.asList(Topping.SAUSAGE.ToppingName, Topping.PEPPERONI.ToppingName, Topping.GREEN_PEPPER.ToppingName, Topping.ONION.ToppingName, Topping.MUSHROOM.ToppingName));
                toppingsList.getItems().addAll(tops);
                saucePicker.setText(Sauce.TOMATO.sauceVal);
                pizzaImage.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/resources/del.jpg"))));
            } case "supreme" -> {
                tops = new ArrayList<>(Arrays.asList(Topping.SAUSAGE.ToppingName, Topping.PEPPERONI.ToppingName, Topping.HAM.ToppingName, Topping.GREEN_PEPPER.ToppingName, Topping.ONION.ToppingName, Topping.BLACK_OLIVE.ToppingName, Topping.MUSHROOM.ToppingName));
                toppingsList.getItems().addAll(tops);
                saucePicker.setText(Sauce.TOMATO.sauceVal);
                pizzaImage.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/resources/suprem.jpg"))));

            } case "meatzza" -> {
                tops = new ArrayList<>(Arrays.asList(Topping.SAUSAGE.ToppingName, Topping.PEPPERONI.ToppingName, Topping.BEEF.ToppingName, Topping.HAM.ToppingName));
                toppingsList.getItems().addAll(tops);
                saucePicker.setText(Sauce.TOMATO.sauceVal);
                pizzaImage.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/resources/meats.jpg"))));
            } case "seafood" -> {
                tops = new ArrayList<>(Arrays.asList(Topping.SHRIMP.ToppingName, Topping.SQUID.ToppingName, Topping.CRAB_MEATS.ToppingName));
                toppingsList.getItems().addAll(tops);
                saucePicker.setText(Sauce.ALFREDO.sauceVal);
                pizzaImage.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/resources/seafood.jfif"))));

            } case "pepperoni" -> {
                tops = new ArrayList<>(List.of(Topping.PEPPERONI.ToppingName));
                toppingsList.getItems().addAll(tops);
                saucePicker.setText(Sauce.TOMATO.sauceVal);
                pizzaImage.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/resources/pep.jpg"))));
            }
        }
        calculatePizzaPrice();
    }

    /**
     * getToppingsList: Helper method used to generate a String Arraylist full of toppings contained within a
     * certain specialty pizza.
     * @param pizzaType type of specialty pizza
     * @return list of pizza toppings of pizza otherwise null.
     */
    private ArrayList<Topping> getToppingsList(String pizzaType){
        pizzaType = pizzaType.toLowerCase();
        ArrayList<Topping> tops = null;
        switch(pizzaType) {
            case "deluxe" -> {
                tops = new ArrayList<>(Arrays.asList(Topping.SAUSAGE, Topping.PEPPERONI, Topping.GREEN_PEPPER, Topping.ONION, Topping.MUSHROOM));
            } case "supreme" -> {
                tops = new ArrayList<>(Arrays.asList(Topping.SAUSAGE, Topping.PEPPERONI, Topping.HAM, Topping.GREEN_PEPPER, Topping.ONION, Topping.BLACK_OLIVE, Topping.MUSHROOM));
            } case "meatzza" -> {
                tops = new ArrayList<>(Arrays.asList(Topping.SAUSAGE, Topping.PEPPERONI, Topping.BEEF, Topping.HAM));
            } case "seafood" -> {
                tops = new ArrayList<>(Arrays.asList(Topping.SHRIMP, Topping.SQUID, Topping.CRAB_MEATS));
            } case "pepperoni" -> {
                tops = new ArrayList<>(List.of(Topping.PEPPERONI));
            }
        }
        return tops;
    }

    /**
     * pizzaChoose: Helper method that chooses what pizza to call PizzaMaker with given a string.
     * @param pizzaType type of specialty pizza.
     * @return specialty pizza of corresponding type.
     */
    private Pizza pizzaChoose(String pizzaType){
        switch (pizzaType) {
            case "deluxe" -> {
                return PizzaMaker.createPizza("deluxe");
            }
            case "supreme" -> {
                return PizzaMaker.createPizza("supreme");
            }
            case "meatzza" -> {
                return PizzaMaker.createPizza("meatzza");
            }
            case "seafood" -> {
                return PizzaMaker.createPizza("seafood");
            }
            case "pepperoni" -> {
                return PizzaMaker.createPizza("pepperoni");
            }
        }
        return null;
    }

    /**
     * addOrder: Reads attributes of a given specialty pizza inputted from the GUI and generates
     * a pizza object and adds it to the current order.
     */
    @FXML
    void addOrder() {
        if(pizzaPicker.getValue() != null) {
            if(smallButton.isSelected() || mediumButton.isSelected() || largeButton.isSelected()){
                String pizzaType = pizzaPicker.getValue().toLowerCase();
                Pizza specialPizza = null;
                specialPizza = pizzaChoose(pizzaType);
                if (specialPizza != null) {
                    if (smallButton.isSelected()) {
                        specialPizza.size = Size.SMALL;
                    } else if (mediumButton.isSelected()) {
                        specialPizza.size = Size.MEDIUM;
                    } else if (largeButton.isSelected()) {
                        specialPizza.size = Size.LARGE;
                    }
                    if (extraSauceButton.isSelected()) {
                        specialPizza.extraCheese = true;
                    }
                    if (extraCheeseButton.isSelected()) {
                        specialPizza.extraSauce = true;
                    }
                    if (getToppingsList(pizzaType) != null) {
                        specialPizza.toppings = getToppingsList(pizzaType);
                    }
                    if (PizzaOrdersController.getOrderForApproval() == null) {
                        PizzaOrdersController.createOrder();
                    }
                    Order ExistingOrder = PizzaOrdersController.getOrderForApproval();
                    ExistingOrder.addPizza(specialPizza);
                    showSuccessPopup("Success: Specialty Pizza Added to Order");
                }
                //System.out.println(order);
            } else {
                showErrorPopup("Error: Choose Size!");
            }
        } else {
            showErrorPopup("Error: Choose Specialty Pizza Type!");
        }
    }

    /**
     * showSuccessPopup: Method used to generate Confirmation popups
     * @param message confirmation message to display
     */
    private void showSuccessPopup(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    /**
     * showErrorPopup: Method used to generate Error popups
     * @param message error message to display
     */
    private void showErrorPopup(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
