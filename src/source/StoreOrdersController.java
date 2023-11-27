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
    /**
     * initialize: initial method run at the creation of controller.
     */
    @FXML
    void initialize(){
        StoreOrders storeOrders = new StoreOrders().getInstance();
        List<Order> allOrders = storeOrders.getAllOrders();
        for(Order a : allOrders){
            if(!OrderNumberPicker.getItems().contains(a.getOrderNumber())){
                OrderNumberPicker.getItems().add(a.getOrderNumber());
            }
        }
        OrderNumberPicker.setOnAction(event -> onOrderSelected());
        //ExportStoreOrderButton.setOnAction(event -> onOrderSelected());
        setupRefreshService();

    }

    /**
     * cancelOrder: Method used to cancel the order selected in the listViewer and remove it from
     * the store orders instance.
     */
    public void cancelOrder(){
        StoreOrders storeOrders = new StoreOrders().getInstance();
        Integer selectedOrderNumber = OrderNumberPicker.getValue();
        if(selectedOrderNumber != null){
            if(findOrder(selectedOrderNumber) != null){
                storeOrders.cancelOrder(findOrder(selectedOrderNumber));
                OrderNumberPicker.getItems().remove(selectedOrderNumber);
                OrderTotalText.clear();
                StoreOrderList.getItems().clear();
            }
        }
    }

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

        // Set the period for the refresh service (e.g., every 5 seconds)
        refreshService.setPeriod(Duration.millis(100));
        refreshService.start();
    }
    /**
     * checkForUpdates: Method that decides what to update within the Current order tab, such as
     * Order Numbers, pizzas in Order, prices, etc.
     */
    private void checkForUpdates() {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        StoreOrders storeOrders = new StoreOrders().getInstance();
        // Check if there are updates to the order, and update the UI accordingly
        if (storeOrders != null) {
            Platform.runLater(() -> {
                List<Order> allOrders = storeOrders.getAllOrders();
                for(Order a : allOrders){
                    if(!OrderNumberPicker.getItems().contains(a.getOrderNumber())){
                        OrderNumberPicker.getItems().add(a.getOrderNumber());
                    }
                }
            });
        }
    }

    /**
     * onOrderSelected: updates the listViewer to display order details whenever an order number is selected
     * from the comboBox.
     */
    private void onOrderSelected() {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        Integer selectedOrderNumber = OrderNumberPicker.getValue();
        if (selectedOrderNumber != null) {
            Order selectedOrder = findOrder(selectedOrderNumber);
            if (selectedOrder != null) {
                StoreOrderList.getItems().clear();
                OrderTotalText.clear();
                OrderTotalText.setText(df.format(selectedOrder.calculateTotal()));
                for(Pizza a : selectedOrder.getPizzas()){
                    StoreOrderList.getItems().add(a.toString());
                }
            }
        }
    }

    /**
     * exportStoreOrdersToFile: Writes all store order Orders and their details, prices, etc. to a txt file, and
     * prompts the user where to save and what to name it.
     */
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

    /**
     * Finds the order location in the store order instance given an integer.
     * @param orderNumber index or unique order number identification.
     * @return index of order in store orders.
     */
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

    /**
     * showInformationPopup: used to display successfully saved file notification.
     */
    private void showInformationPopup() {
        showAlert(AlertType.INFORMATION, "Success", null, "File saved successfully!");
    }
    /**
     * showErrorPopup: used to display unsuccessfully saved file notification.
     */
    private void showErrorPopup(String message) {
        showAlert(AlertType.ERROR, "Error", null, message);
    }

    /**
     * showAlert: Alert system used to display alerts depending on type of alert, title of alert,
     * header, and the actual alert message.
     * @param alertType type of alert
     * @param title title of window
     * @param header header of alert
     * @param content alert message
     */
    private void showAlert(AlertType alertType, String title, String header, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
