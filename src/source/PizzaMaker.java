package source;

public class PizzaMaker {
    public static Pizza createPizza(String pizzaType) {
        return switch (pizzaType.toLowerCase()) {
            case "deluxe" -> new SpecialityPizzas.Deluxe();
            case "supreme" -> new SpecialityPizzas.Supreme();
            case "meatzza" -> new SpecialityPizzas.Meatzza();
            case "seafood" -> new SpecialityPizzas.Seafood();
            case "pepperoni" -> new SpecialityPizzas.Pepperoni();
            // Add more cases for other pizza types
            default -> null; // Default to BuildYourOwnPizza if the type is unknown
        };
    }
}
