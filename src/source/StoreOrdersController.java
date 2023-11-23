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

import java.util.List;

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
        //setupRefreshService();

    }
    private void onOrderSelected() {
        StoreOrders storeOrders = new StoreOrders().getInstance();
        Integer selectedOrderNumber = OrderNumberPicker.getValue();
        if (selectedOrderNumber != null) {
            Order selectedOrder = findOrder(selectedOrderNumber);
            if (selectedOrder != null) {
                StoreOrderList.getItems().clear();
                for(Pizza a : selectedOrder.getPizzas()){
                    StoreOrderList.getItems().add(a.toString());
                }
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



}
