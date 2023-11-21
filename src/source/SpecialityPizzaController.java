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
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Arrays;

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

    // Assume a method to initialize the storeOrders instance
    @FXML
    void initialize() {
        StoreOrders storeOrders = new StoreOrders().getInstance();
        saucePicker.setEditable(false);
        priceBox.setEditable(false);
        pizzaPicker.getItems().addAll("Deluxe", "Supreme", "Meatzza");

        pizzaPicker.setOnAction(event -> onSpecialtySelected());

        EventHandler<ActionEvent> buttonHandler = event -> {
            calculatePizzaPrice();
        };
        pizzaPicker.setOnAction(buttonHandler);
        largeButton.setOnAction(buttonHandler);
        mediumButton.setOnAction(buttonHandler);
        smallButton.setOnAction(buttonHandler);
        extraCheeseButton.setOnAction(buttonHandler);
        extraSauceButton.setOnAction(buttonHandler);

        addButton.setOnAction(event -> addOrder());
    }

    private void calculatePizzaPrice(){
        double total = 0;
        String selectedPizza = pizzaPicker.getValue();
        if(selectedPizza != null) {
            switch (selectedPizza) {
                case "Deluxe" -> total += 14.99;
                case "Supreme" -> total += 15.99;
                case "Meatzza" -> total += 16.99;
                default -> total = 0;
            }
            if(smallButton.isSelected()){
                total +=0;
            } else if(mediumButton.isSelected()){
                total += 2;
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
        priceBox.setText(Double.toString(total));
    }

    private void onSpecialtySelected() {
        String selectedPizza = pizzaPicker.getValue();
        if (selectedPizza != null) {
            switch (selectedPizza) {
                case "Deluxe" -> {
                    saucePicker.setText("Tomato");
                    pizzaImage.setImage(null);
                    ArrayList<String> tops = new ArrayList<>(Arrays.asList(Topping.SAUSAGE.ToppingName,
                            Topping.PEPPERONI.ToppingName, Topping.GREEN_PEPPER.ToppingName, Topping.ONION.ToppingName,
                            Topping.MUSHROOM.ToppingName));

                    toppingsList.getItems().clear();
                    for (String a : tops) {
                        toppingsList.getItems().add(a);
                    }
                }
                case "Supreme" -> {
                    saucePicker.setText("Tomato");
                    pizzaImage.setImage(null);

                    toppingsList.getItems().clear();
                    ArrayList<String> tops = new ArrayList<>(Arrays.asList(Topping.SAUSAGE.ToppingName,
                            Topping.PEPPERONI.ToppingName, Topping.HAM.ToppingName, Topping.GREEN_PEPPER.ToppingName,
                            Topping.ONION.ToppingName, Topping.BLACK_OLIVE.ToppingName, Topping.MUSHROOM.ToppingName));
                    for (String a : tops) {
                        toppingsList.getItems().add(a);
                    }
                }
                case "Meatzza" -> {
                    saucePicker.setText("Tomato");
                    toppingsList.getItems().clear();

                    toppingsList.getItems().clear();
                    ArrayList<String> tops = new ArrayList<>(Arrays.asList(Topping.SAUSAGE.ToppingName,
                            Topping.PEPPERONI.ToppingName, Topping.BEEF.ToppingName, Topping.HAM.ToppingName));
                    for (String a : tops) {
                        toppingsList.getItems().add(a);
                    }
                }
                default -> {
                    // choose a pizza!!!
                }
            }

        }

    }

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
            }
        }
        return tops;
    }
    @FXML
    void addOrder() {
        StoreOrders storeOrders = new StoreOrders().getInstance();
        Order order = new Order();
        String pizzaType = pizzaPicker.getValue();

        if(pizzaType != null){
            Pizza specialPizza = null;
            switch(pizzaType) {
                case "deluxe" -> {
                    specialPizza = PizzaMaker.createPizza("deluxe");
                } case "supreme" -> {
                    specialPizza = PizzaMaker.createPizza("supreme");
                } case "meatzza" -> {
                    specialPizza = PizzaMaker.createPizza("meatzza");
                }
            }
            if(specialPizza != null){
                if(smallButton.isSelected()){
                    specialPizza.size = Size.SMALL;
                } else if (mediumButton.isSelected()){
                    specialPizza.size = Size.MEDIUM;
                } else if (largeButton.isSelected()){
                    specialPizza.size = Size.LARGE;
                } else {
                    //error retard
                }
                if(extraSauceButton.isSelected()){
                    specialPizza.extraCheese = true;
                }
                if(extraCheeseButton.isSelected()){
                    specialPizza.extraCheese = true;
                }
                if(getToppingsList(pizzaType) != null){
                    specialPizza.toppings = getToppingsList(pizzaType);
                }
                order.addPizza(specialPizza);
            }
        }
    }


    /**
     * isValidDouble() check if the String contains double is valid
     * @param input String contains double
     * @return true if double is valid
     */
    public static boolean isValidDouble(String input) {
        if (input == null) {
            return false;
        }
        try {
            Double.parseDouble(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
