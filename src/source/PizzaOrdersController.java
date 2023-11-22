package source;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

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
    @FXML
    void initialize(){
        CurrentOrderViewer = new ListView<>();
        OrderNumber.setEditable(false);
        PlaceOrderButton.setOnAction(event -> approveOrder());
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
        if (orderForApproval != null) {
            // Add the approved order to storeOrders
            storeOrders.placeOrder(orderForApproval);

            // Reset orderForApproval to null for the next order
            orderForApproval = null;


        }
    }
    public static Order getOrderForApproval() {
        return orderForApproval;
    }
}

