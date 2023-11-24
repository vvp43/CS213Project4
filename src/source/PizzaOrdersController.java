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
import java.text.DecimalFormat;


import java.util.concurrent.TimeUnit;


import java.util.List;

public class PizzaOrdersController {

    @FXML
    private ListView<Pizza> CurrentOrderViewer;

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
        DecimalFormat df = new DecimalFormat("#,##0.00");

        // Check if there are updates to the order, and update the UI accordingly
        if (orderForApproval != null) {
            // For example, update a label with the order status

            Platform.runLater(() -> OrderNumber.setText(Integer.toString(orderForApproval.getOrderNumber())));

            // Update the ListView with pizza details
            List<Pizza> pizzas = orderForApproval.getPizzas();
            ObservableList<Pizza> pizzaDetails = FXCollections.observableArrayList();
            pizzaDetails.addAll(pizzas);
            Platform.runLater(() -> CurrentOrderViewer.setItems(pizzaDetails));
            double subtotal = orderForApproval.calculateTotalPrice();
            double sales = subtotal*0.0625;
            double total = subtotal + sales;
            Platform.runLater(() -> SubtotalTextField.setText(df.format(subtotal)));
            Platform.runLater(() -> SalesTaxTextField.setText(df.format(sales)));
            Platform.runLater(() -> TotalTextField.setText(df.format(total)));

        } else {

        }
    }


    private void updatePizzaListView() {
        if (orderForApproval != null) {
            // Get the pizzas from the order and populate the ListView
            ObservableList<Pizza> pizzas = FXCollections.observableArrayList(orderForApproval.getPizzas());
            CurrentOrderViewer.setItems(pizzas);
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

    public void removePizza(){
        Pizza selectedPizza = CurrentOrderViewer.getSelectionModel().getSelectedItem();
        if(selectedPizza != null){
            if(orderForApproval != null){
                orderForApproval.removePizza(selectedPizza);
            }
            updatePizzaListView();
        }
    }
    public void approveOrder() {
        StoreOrders storeOrders = new StoreOrders().getInstance();
        CurrentOrderViewer.getItems().clear();
        OrderNumber.clear();
        SalesTaxTextField.clear();
        SubtotalTextField.clear();
        TotalTextField.clear();
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

