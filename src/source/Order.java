package source;

import java.util.ArrayList;

public class Order {

    private static int nextOrderNumber = 1;

    private int orderNumber;
    private ArrayList<Pizza> pizzas;

    public Order() {
        this.orderNumber = nextOrderNumber++;
        this.pizzas = new ArrayList<>();
    }

    // Method to add a pizza to the order
    public void addPizza(Pizza pizza) {
        pizzas.add(pizza);
    }

    // Method to get the order number
    public int getOrderNumber() {
        return orderNumber;
    }

    // Method to get the list of pizzas in the order
    public ArrayList<Pizza> getPizzas() {
        return pizzas;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    @Override
    public String toString(){
        String fin = "";
        for(Pizza p : pizzas){
            fin = fin.concat(p.toString()+"\n");
        }
        return fin;
    }
    // Method to calculate the total price of the order
    public double calculateTotalPrice() {
        double totalPrice = 0.0;
        for (Pizza pizza : pizzas) {
            totalPrice += pizza.price();
        }
        return totalPrice;
    }
}
