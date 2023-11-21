package source;

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

    @FXML
    void initialize(){
        StoreOrders storeOrders = new StoreOrders().getInstance();
        List<Order> allOrders = storeOrders.getAllOrders();
        for(int i = 0; i < allOrders.size(); i++){
            OrderNumberPicker.getItems().add(i);
        }
        OrderNumberPicker.setOnAction(event -> onOrderSelected());

    }
    private void onOrderSelected() {
        Integer selectedOrderNumber = OrderNumberPicker.getValue();
        if (selectedOrderNumber != null) {
            Order selectedOrder = findOrder(selectedOrderNumber);
            if (selectedOrder != null) {
                StoreOrderList.getItems().clear();
                StoreOrderList.getItems().add(selectedOrder.toString());
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
