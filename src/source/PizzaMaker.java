package source;

public class PizzaMaker {
    public static Pizza createPizza(String pizzaType) {
        return switch (pizzaType.toLowerCase()) {
            case "deluxe" -> new SpecialityPizzas.DeluxePizza();
            case "supreme" -> new SpecialityPizzas.SupremePizza();
            case "meatzza" -> new SpecialityPizzas.MeatzzaPizza();
            // Add more cases for other pizza types
            default -> null; // Default to BuildYourOwnPizza if the type is unknown
        };
    }
}
