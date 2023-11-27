package source;

public class PizzaMaker {
    /**
     * createPizza: "Pizza Factory" method that uses only a string to identify which instance of Pizza to make.
     * @param pizzaType String used to identify Pizza subclass
     * @return respective Pizza Object according to pizzaType String, otherwise null
     */
    public static Pizza createPizza(String pizzaType) {
        return switch (pizzaType.toLowerCase()) {
            case "deluxe" -> new PizzaTypes.Deluxe();
            case "supreme" -> new PizzaTypes.Supreme();
            case "meatzza" -> new PizzaTypes.Meatzza();
            case "seafood" -> new PizzaTypes.Seafood();
            case "pepperoni" -> new PizzaTypes.Pepperoni();
            case "buildyourown" -> new BuildYourOwn();
            // Add more cases for other pizza types
            default -> null; // Default to BuildYourOwnPizza if the type is unknown
        };
    }
}
