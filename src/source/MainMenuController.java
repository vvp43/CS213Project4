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
    /**
     * initialize: initial method run at the creation of controller.
     */
    @FXML
    void initialize(){
//        StoreOrders storeOrders = new StoreOrders().getInstance();
//        Order order1 = new Order();
//        Order order2 = new Order();
//
//        ArrayList<Topping> toppings = new ArrayList<>(Arrays.asList(Topping.MUSHROOM, Topping.BACON));
//
//        Pizza deluxePizza = PizzaMaker.createPizza("Supreme");
//        deluxePizza.extraSauce = true;
//        deluxePizza.extraCheese = false;
//        deluxePizza.toppings = toppings;
//        deluxePizza.size = Size.SMALL;
//        Pizza meatzzaPizza = PizzaMaker.createPizza("Meatzza");
//        meatzzaPizza.toppings = toppings;
//        meatzzaPizza.extraSauce = false;
//        meatzzaPizza.extraCheese = true;
//
//        order1.addPizza(deluxePizza);
//        order1.addPizza(deluxePizza);
//        order2.addPizza(meatzzaPizza);
//
//        storeOrders.placeOrder(order1);
//        storeOrders.placeOrder(order2);
    }

    private Stage SpecialtyPizzaTab;

    /**
     * openSecondaryScene: Opens the Specialty Pizza Tab GUI.
     */
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
                SpecialtyPizzaTab.setResizable(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private Stage BuildYourOwnPizzaTab;
    /**
     * openTertiaryScene: Opens the Build Your Own Pizza Tab GUI.
     */
    public void openTertiaryScene() {
        if (BuildYourOwnPizzaTab == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("BuildYourOwnView.fxml"));
                Parent root = loader.load();
                BuildYourOwnPizzaTab = new Stage();
                BuildYourOwnPizzaTab.setTitle("Build Your Own Pizza");
                BuildYourOwnPizzaTab.setScene(new Scene(root, 600, 650));
                BuildYourOwnPizzaTab.setOnCloseRequest(event -> BuildYourOwnPizzaTab = null);
                BuildYourOwnPizzaTab.setResizable(false);
                BuildYourOwnPizzaTab.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private Stage StoreOrdersTab;
    /**
     * openQuaternaryScene: Opens the Store Orders Tab GUI.
     */
    public void openQuaternaryScene() {
        if (StoreOrdersTab == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("StoreOrdersView.fxml"));
                Parent root = loader.load();
                StoreOrdersTab = new Stage();
                StoreOrdersTab.setTitle("Store Orders");
                StoreOrdersTab.setScene(new Scene(root, 600, 650));
                StoreOrdersTab.setOnCloseRequest(event -> StoreOrdersTab = null);
                StoreOrdersTab.setResizable(false);

                StoreOrdersTab.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private Stage PizzaOrdersTab;
    /**
     * openPrimaryScene: Opens the Current Orders Tab GUI.
     */
    public void openPrimaryScene() {
        if (PizzaOrdersTab == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("PizzaOrdersView.fxml"));
                Parent root = loader.load();
                PizzaOrdersTab = new Stage();
                PizzaOrdersTab.setTitle("Current Order");
                PizzaOrdersTab.setScene(new Scene(root, 600, 650));
                PizzaOrdersTab.setOnCloseRequest(event -> PizzaOrdersTab = null);
                PizzaOrdersTab.setResizable(false);

                PizzaOrdersTab.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
