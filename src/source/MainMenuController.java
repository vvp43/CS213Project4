package source;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;

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
        StoreOrders storeOrders = new StoreOrders().getInstance();
        Order order1 = new Order();
        Order order2 = new Order();

        ArrayList<Topping> toppings = new ArrayList<>(Arrays.asList(Topping.MUSHROOM, Topping.BACON));

        Pizza deluxePizza = PizzaMaker.createPizza("Supreme");
        deluxePizza.extraSauce = true;
        deluxePizza.extraCheese = false;
        deluxePizza.toppings = toppings;
        deluxePizza.size = Size.SMALL;
        Pizza meatzzaPizza = PizzaMaker.createPizza("Meatzza");
        meatzzaPizza.toppings = toppings;
        meatzzaPizza.extraSauce = false;
        meatzzaPizza.extraCheese = true;

        order1.addPizza(deluxePizza);
        order1.addPizza(deluxePizza);
        order2.addPizza(meatzzaPizza);

        storeOrders.placeOrder(order1);
        storeOrders.placeOrder(order2);
    }

    private Stage SpecialtyPizzaTab;
    public void openSecondaryScene() {
        if (SpecialtyPizzaTab == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("SpecialtyPizzaView.fxml"));
                Parent root = loader.load();
                SpecialtyPizzaTab = new Stage();
                SpecialtyPizzaTab.setTitle("Choose Your Speciality Pizza");
                SpecialtyPizzaTab.setScene(new Scene(root, 600, 650));
                SpecialtyPizzaTab.setOnCloseRequest(event -> SpecialtyPizzaTab = null);

                SpecialtyPizzaTab.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private Stage BuildYourOwnPizzaTab; //wrong fix later
    public void openTertiaryScene() {
        if (BuildYourOwnPizzaTab == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("SpecialtyPizzaView.fxml"));
                Parent root = loader.load();
                BuildYourOwnPizzaTab = new Stage();
                BuildYourOwnPizzaTab.setTitle("Choose Your Speciality Pizza");
                BuildYourOwnPizzaTab.setScene(new Scene(root, 600, 650));
                BuildYourOwnPizzaTab.setOnCloseRequest(event -> BuildYourOwnPizzaTab = null);

                BuildYourOwnPizzaTab.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private Stage StoreOrdersTab;
    public void openQuaternaryScene() {
        if (StoreOrdersTab == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("StoreOrdersView.fxml"));
                Parent root = loader.load();
                StoreOrdersTab = new Stage();
                StoreOrdersTab.setTitle("Choose Your Speciality Pizza");
                StoreOrdersTab.setScene(new Scene(root, 600, 650));
                StoreOrdersTab.setOnCloseRequest(event -> StoreOrdersTab = null);

                StoreOrdersTab.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
