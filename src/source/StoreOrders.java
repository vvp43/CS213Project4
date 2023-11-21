package source;
import java.util.ArrayList;
import java.util.List;

public class StoreOrders {
    private final static StoreOrders instance = new StoreOrders();
    private static int nextAvailableOrderNumber = 0;
    private static final int nextOrderNum = 1;
    private int orderNum;
    private final ArrayList<Order> orders;

    public StoreOrders() {
        this.orders = new ArrayList<>();
    }
    public StoreOrders getInstance(){
        return instance;
    }

    // Method to place a new order
    public void placeOrder(Order order) {
        order.setOrderNumber(generateNextOrderNumber());
        orders.add(order);
    }

    // Method to get the list of all orders
    public ArrayList<Order> getAllOrders() {
        return orders;
    }


    // Private method to generate the next available order number
    private static int generateNextOrderNumber() {
        return nextAvailableOrderNumber++;
    }


    public static void main(String[] args) {
        StoreOrders storeOrders = new StoreOrders();

        Order order1 = new Order();
        Order order2 = new Order();

        Pizza deluxePizza = PizzaMaker.createPizza("Deluxe");
        Pizza meatzzaPizza = PizzaMaker.createPizza("Meatzza");

        order1.addPizza(deluxePizza);
        order1.addPizza(deluxePizza);
        order2.addPizza(meatzzaPizza);

        storeOrders.placeOrder(order1);
        storeOrders.placeOrder(order2);

        // Display all orders and their details
        List<Order> allOrders = storeOrders.getAllOrders();
        for (Order order : allOrders) {
            System.out.println("Order Number: " + order.getOrderNumber());
            System.out.println("Pizzas in the Order:");
            for (Pizza pizza : order.getPizzas()) {
                System.out.println("- " + pizza.getClass().getSimpleName() + ": $" + pizza.price());
            }
            System.out.println("Total Order Price: $" + order.calculateTotalPrice());
            System.out.println("-------------");
        }
    }
}