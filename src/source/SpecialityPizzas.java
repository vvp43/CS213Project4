package source;

import java.util.ArrayList;
import java.util.Arrays;

public class SpecialityPizzas {

    public static class Supreme extends Pizza {
        @Override
        public double price() {
            // Calculate the price for SupremePizza
            double price = 15.99;
            if(size == Size.SMALL){
                price += 0;
            } else if (size == Size.MEDIUM){
                price += 2.00;
            } else {
                price += 4.00;
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
            double price = 16.99;
            if(size == Size.SMALL){
                price += 0;
            } else if (size == Size.MEDIUM){
                price += 2.00;
            } else {
                price += 4.00;
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

    public static class Deluxe extends Pizza {
        @Override
        public double price() {
            // Calculate the price for DeluxePizza
            double price = 14.99;
            if(size == Size.SMALL){
                price += 0;
            } else if (size == Size.MEDIUM){
                price += 2.00;
            } else {
                price += 4.00;
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

    public static class Pepperoni extends Pizza {
        @Override
        public double price() {
            // Calculate the price for DeluxePizza
            double price = 10.99;
            if(size == Size.SMALL){
                price += 0;
            } else if (size == Size.MEDIUM){
                price += 2.00;
            } else {
                price += 4.00;
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
    public static class Seafood extends Pizza {
        @Override
        public double price() {
            // Calculate the price for DeluxePizza
            double price = 17.99;
            if(size == Size.SMALL){
                price += 0;
            } else if (size == Size.MEDIUM){
                price += 2.00;
            } else {
                price += 4.00;
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
    public static class BuildYourOwn {
    }

    public void calcPrice(){

    }
}
