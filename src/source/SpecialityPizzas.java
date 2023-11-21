package source;

import java.util.ArrayList;
import java.util.Arrays;

public class SpecialityPizzas {

    public static class Supreme extends Pizza {
        @Override
        public double price() {
            // Calculate the price for SupremePizza
            double price;
            if(size == Size.SMALL){
                price = 14.99;
            } else if (size == Size.MEDIUM){
                price = 16.99;
            } else {
                price = 18.99;
            }
            if(extraCheese){
                price+= 1.00;
            }
            if(extraSauce){
                price+= 1.00;
            }
            return price;

        }


    }

    public static class Meatzza extends Pizza {
        @Override
        public double price() {
            // Calculate the price for MeatzzaPizza
            return 15.99;
        }
    }

    public static class Deluxe extends Pizza {
        @Override
        public double price() {
            // Calculate the price for DeluxePizza
            return 12.99;
        }
    }

    public static class Pepperoni extends Pizza {
        @Override
        public double price() {
            // Calculate the price for DeluxePizza
            return 12.99;
        }
    }
    public static class Seafood extends Pizza {
        @Override
        public double price() {
            // Calculate the price for DeluxePizza
            return 12.99;
        }
    }
    public static class BuildYourOwn {
    }
}
