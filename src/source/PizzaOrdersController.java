package source;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.concurrent.Task;
import javafx.concurrent.ScheduledService;
import javafx.util.Duration;

import java.util.concurrent.TimeUnit;


import java.util.List;

public class PizzaOrdersController {

    @FXML
    private ListView<String> CurrentOrderViewer;

    @FXML
    private TextField OrderNumber;

    @FXML
    private Button PlaceOrderButton;

    @FXML
    private Button RemovePizzaButton;

    @FXML
    private TextField SalesTaxTextField;

    @FXML
    private TextField SubtotalTextField;

    @FXML
    private TextField TotalTextField;

    private static Order orderForApproval;
    private ScheduledService<Void> refreshService;
    private void setupRefreshService() {
        refreshService = new ScheduledService<>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<>() {
                    @Override
                    protected Void call() {
                        // Check for updates and refresh the UI
                        checkForUpdates();
                        return null;
                    }
                };
            }
        };

        // Set the period for the refresh service (e.g., every 5 seconds)
        refreshService.setPeriod(Duration.millis(100));
        refreshService.start();
    }
    private void checkForUpdates() {

        // Check if there are updates to the order, and update the UI accordingly
        if (orderForApproval != null) {
            // For example, update a label with the order status

            Platform.runLater(() -> OrderNumber.setText(Integer.toString(orderForApproval.getOrderNumber())));



            // Update the ListView with pizza details
            List<Pizza> pizzas = orderForApproval.getPizzas();
            ObservableList<String> pizzaDetails = FXCollections.observableArrayList();
            for (Pizza pizza : pizzas) {
                pizzaDetails.add(pizza.toString());
            }
            Platform.runLater(() -> CurrentOrderViewer.setItems(pizzaDetails));
        } else {

        }
    }


    @FXML
    void initialize(){
        OrderNumber.setEditable(false);
        if(orderForApproval != null){
            OrderNumber.setText(Integer.toString(orderForApproval.getNextOrderNumber()));
        }
        PlaceOrderButton.setOnAction(event -> approveOrder());
        setupRefreshService();
    }

    public static void createOrder() {
        orderForApproval = new Order();
    }
    public static void approveOrder(Order order) {
        orderForApproval = order;
    }
    public void approveOrder() {
        StoreOrders storeOrders = new StoreOrders().getInstance();
        CurrentOrderViewer.getItems().clear();
        OrderNumber.clear();
        if (orderForApproval != null) {
            // Add the approved order to storeOrders
            OrderNumber.setText(Integer.toString(orderForApproval.getNextOrderNumber()));
            storeOrders.placeOrder(orderForApproval);

            // Reset orderForApproval to null for the next order
            orderForApproval = null;
        }
    }
    public static Order getOrderForApproval() {
        return orderForApproval;
    }
}

