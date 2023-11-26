package source;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.util.Duration;

public class StoreOrdersController {


    @FXML
    private Button CancelOrderButton;

    @FXML
    private Button ExportStoreOrderButton;

    @FXML
    private ComboBox<Integer> OrderNumberPicker;

    @FXML
    private TextField OrderTotalText;

    @FXML
    private ListView<String> StoreOrderList;

    private static Order orderForApproval;
    private ScheduledService<Void> refreshService;

    @FXML
    void initialize(){
        StoreOrders storeOrders = new StoreOrders().getInstance();
        List<Order> allOrders = storeOrders.getAllOrders();
        for(int i = 0; i < allOrders.size(); i++){
            OrderNumberPicker.getItems().add(i);
        }
        OrderNumberPicker.setOnAction(event -> onOrderSelected());
        //ExportStoreOrderButton.setOnAction(event -> onOrderSelected());
        setupRefreshService();

    }
    // Method to refresh the order number combo box

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
            StoreOrders storeOrders = new StoreOrders().getInstance();
            // Update the ListView with pizza details
            List<Order> orders = storeOrders.getAllOrders();
            ObservableList<Order> orderDetails = FXCollections.observableArrayList();
            orderDetails.addAll(orders);
            Platform.runLater(() -> OrderNumberPicker.getItems().addAll(storeOrders.getOrderNumbers()));

        }
    }
    private void onOrderSelected() {
        Integer selectedOrderNumber = OrderNumberPicker.getValue();
        if (selectedOrderNumber != null) {
            Order selectedOrder = findOrder(selectedOrderNumber);
            if (selectedOrder != null) {
                StoreOrderList.getItems().clear();
                OrderTotalText.clear();
                OrderTotalText.setText(Double.toString(selectedOrder.calculateTotal()));
                for(Pizza a : selectedOrder.getPizzas()){
                    StoreOrderList.getItems().add(a.toString());
                }
            }
        }
    }
    public void exportStoreOrdersToFile() {
        StoreOrders storeOrders = new StoreOrders().getInstance();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save StoreOrders to File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));

        Stage stage = (Stage) ExportStoreOrderButton.getScene().getWindow();
        java.io.File file = fileChooser.showSaveDialog(stage);

        if (file != null) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                // Write the content of storeOrders.toString() to the selected file
                writer.write(storeOrders.toString());
                showInformationPopup();
            } catch (IOException e) {
                showErrorPopup("Error exporting StoreOrders to file: " + e.getMessage());

            }
        }
    }
    private Order findOrder(int orderNumber) {
        StoreOrders storeOrders = new StoreOrders().getInstance();
        List<Order> allOrders = storeOrders.getAllOrders();
        for (Order order : allOrders) {
            if (order.getOrderNumber() == orderNumber) {
                return order;
            }
        }
        return null;
    }

    private void showInformationPopup() {
        showAlert(AlertType.INFORMATION, "Success", null, "File saved successfully!");
    }

    private void showErrorPopup(String message) {
        showAlert(AlertType.ERROR, "Error", null, message);
    }

    private void showAlert(AlertType alertType, String title, String header, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
