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

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
            } case "supreme" -> {
                tops = new ArrayList<>(Arrays.asList(Topping.SAUSAGE.ToppingName, Topping.PEPPERONI.ToppingName, Topping.HAM.ToppingName, Topping.GREEN_PEPPER.ToppingName, Topping.ONION.ToppingName, Topping.BLACK_OLIVE.ToppingName, Topping.MUSHROOM.ToppingName));
                toppingsList.getItems().addAll(tops);
                saucePicker.setText(Sauce.TOMATO.sauceVal);
            } case "meatzza" -> {
                tops = new ArrayList<>(Arrays.asList(Topping.SAUSAGE.ToppingName, Topping.PEPPERONI.ToppingName, Topping.BEEF.ToppingName, Topping.HAM.ToppingName));
                toppingsList.getItems().addAll(tops);
                saucePicker.setText(Sauce.TOMATO.sauceVal);
            } case "seafood" -> {
                tops = new ArrayList<>(Arrays.asList(Topping.SHRIMP.ToppingName, Topping.SQUID.ToppingName, Topping.CRAB_MEATS.ToppingName));
                toppingsList.getItems().addAll(tops);
                saucePicker.setText(Sauce.ALFREDO.sauceVal);
            } case "pepperoni" -> {
                tops = new ArrayList<>(List.of(Topping.PEPPERONI.ToppingName));
                toppingsList.getItems().addAll(tops);
                saucePicker.setText(Sauce.TOMATO.sauceVal);
            }
        }
        calculatePizzaPrice();
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
            } case "seafood" -> {
                tops = new ArrayList<>(Arrays.asList(Topping.SHRIMP, Topping.SQUID, Topping.CRAB_MEATS));
            } case "pepperoni" -> {
                tops = new ArrayList<>(List.of(Topping.PEPPERONI));
            }
        }
        return tops;
    }
    @FXML
    void addOrder() {
        if(pizzaPicker.getValue() != null) {
            String pizzaType = pizzaPicker.getValue().toLowerCase();
            Pizza specialPizza = null;
            switch (pizzaType) {
                case "deluxe" -> {
                    specialPizza = PizzaMaker.createPizza("deluxe");
                }
                case "supreme" -> {
                    specialPizza = PizzaMaker.createPizza("supreme");
                }
                case "meatzza" -> {
                    specialPizza = PizzaMaker.createPizza("meatzza");
                }
                case "seafood" -> {
                    specialPizza = PizzaMaker.createPizza("seafood");
                }
                case "pepperoni" -> {
                    specialPizza = PizzaMaker.createPizza("pepperoni");
                }
            }
            if (specialPizza != null) {
                if (smallButton.isSelected()) {
                    specialPizza.size = Size.SMALL;
                } else if (mediumButton.isSelected()) {
                    specialPizza.size = Size.MEDIUM;
                } else if (largeButton.isSelected()) {
                    specialPizza.size = Size.LARGE;
                } else {
                    //error retard
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

                //System.out.println(order);
            }
        } else {
            //error
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
