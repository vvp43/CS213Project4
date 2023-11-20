package source;

public class PizzaMaker {
    public static Pizza createPizza(String pizzaType) {
        return switch (pizzaType.toLowerCase()) {
            case "deluxe" -> new DeluxePizza();
            case "supreme" -> new SupremePizza();
            case "meatzza" -> new MeatzzaPizza();
            // Add more cases for other pizza types
            default -> null; // Default to BuildYourOwnPizza if the type is unknown
        };
    }
}
