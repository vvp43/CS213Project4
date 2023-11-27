package source;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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

    /**
     * setupRefreshService: Service used to instantiate constant updates to the current order window
     */
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
        refreshService.setPeriod(Duration.millis(100));
        refreshService.start();
    }

    /**
     * checkForUpdates: Method that decides what to update within the Current order tab, such as
     * Order Numbers, pizzas in Order, prices, etc.
     */
    private void checkForUpdates() {
        DecimalFormat df = new DecimalFormat("#,##0.00");

        // Check if there are updates to the order, and update the UI accordingly
        if (orderForApproval != null) {

            Platform.runLater(() -> OrderNumber.setText(Integer.toString(orderForApproval.getOrderNumber())));

            // Update the ListView with pizza details
            List<Pizza> pizzas = orderForApproval.getPizzas();
            ObservableList<Pizza> pizzaDetails = FXCollections.observableArrayList();
            pizzaDetails.addAll(pizzas);
            Platform.runLater(() -> CurrentOrderViewer.setItems(pizzaDetails));
            Platform.runLater(() -> SubtotalTextField.setText(df.format(orderForApproval.calculateSubTotalPrice())));
            Platform.runLater(() -> SalesTaxTextField.setText(df.format(orderForApproval.calculateSalesTax())));
            Platform.runLater(() -> TotalTextField.setText(df.format(orderForApproval.calculateTotal())));

        } else {

        }
    }

    /**
     * updatePizzaListView: Helper method to update list of pizzas in an order
     */
    private void updatePizzaListView() {
        if (orderForApproval != null) {
            // Get the pizzas from the order and populate the ListView
            ObservableList<Pizza> pizzas = FXCollections.observableArrayList(orderForApproval.getPizzas());
            CurrentOrderViewer.setItems(pizzas);
        }
    }

    /**
     * initialize: initial method run at the creation of controller.
     */
    @FXML
    void initialize(){
        OrderNumber.setEditable(false);
        if(orderForApproval != null){
            OrderNumber.setText(Integer.toString(orderForApproval.getNextOrderNumber()));
        }
        updatePizzaListView();
        PlaceOrderButton.setOnAction(event -> approveOrder());
        setupRefreshService();
    }

    /**
     * createOrder: creates an instance of orderForApproval to be passed through different controllers for approval
     */
    public static void createOrder() {
        orderForApproval = new Order();
    }
    /**
     * removePizza: removes selected pizza from listViewer of pizzas in an order, to remove it from the Order list.
     */
    public void removePizza(){
        Pizza selectedPizza = CurrentOrderViewer.getSelectionModel().getSelectedItem();
        if(selectedPizza != null){
            if(orderForApproval != null){
                orderForApproval.removePizza(selectedPizza);
            }
            updatePizzaListView();
        }
    }

    /**
     * approveOrder: Approves list of pizzas in Order for the store and places it, moving it to the store orders
     * object.
     */
    public void approveOrder() {
        StoreOrders storeOrders = new StoreOrders().getInstance();
        CurrentOrderViewer.getItems().clear();
        OrderNumber.clear();
        SalesTaxTextField.clear();
        SubtotalTextField.clear();
        TotalTextField.clear();
        if (orderForApproval != null && !orderForApproval.getPizzas().isEmpty()) {
            // Add the approved order to storeOrders
            OrderNumber.setText(Integer.toString(orderForApproval.getNextOrderNumber()));
            storeOrders.placeOrder(orderForApproval);

            // Reset orderForApproval to null for the next order
            orderForApproval = null;
        } else {
            showErrorPopup("Error: Nothing in Order!");
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
     * getOrderForApproval: static method using in other classes to return an Order for approval through
     * different controllers.
     * @return orderForApproval
     */
    public static Order getOrderForApproval() {
        return orderForApproval;
    }
}

