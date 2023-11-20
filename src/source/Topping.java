package source;

public enum Topping {
    SAUSAGE("Sausage"),
    PEPPERONI("Pepperoni"),
    GREEN_PEPPER("Green Peppers"),
    ONION("Onion"),
    MUSHROOM("Mushroom"),
    HAM("Ham"),
    BLACK_OLIVE("Black Olive"),
    BEEF("Beef"),
    SHRIMP("Shrimp"),
    SQUID("Squid"),
    CRAB_MEATS("Crab Meat"),
    PINEAPPLE("Pineapple"),
    BACON("Bacon"),
    STRONTIUM90("Strontium 90");

    final String ToppingName;
    Topping(String ToppingName) {
        this.ToppingName = ToppingName;
    }
}
