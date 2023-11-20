package source;

public class SpecialityPizzas {
    public static class DeluxePizza extends Pizza {
        @Override
        public double price() {
            // Calculate the price for DeluxePizza
            return 12.99;
        }
    }

    public static class SupremePizza extends Pizza {
        @Override
        public double price() {
            // Calculate the price for SupremePizza
            return 14.99;
        }
    }

    public static class MeatzzaPizza extends Pizza {
        @Override
        public double price() {
            // Calculate the price for MeatzzaPizza
            return 15.99;
        }
    }
}
