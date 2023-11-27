package source;

public class PizzaTypes {

    public static class Supreme extends Pizza {
        /**
         * price(): calculates price of supreme pizza
         * @return supreme pizza price
         */
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
            sauce = Sauce.TOMATO;
            return price;
        }
    }

    public static class Meatzza extends Pizza {
        /**
         * price(): calculates price of meatzaa pizza
         * @return supreme meatzza price
         */
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
            } if(extraSauce){
                price+= 1.00;
            }
            sauce = Sauce.TOMATO;
            return price;
        }
    }

    public static class Deluxe extends Pizza {
        /**
         * price(): calculates price of deluxe pizza
         * @return deluxe pizza price
         */
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
            } if(extraSauce){
                price+= 1.00;
            }
            sauce = Sauce.TOMATO;
            return price;
        }
    }

    public static class Pepperoni extends Pizza {
        @Override
        public double price() {
            /**
             * price(): calculates price of pepperoni pizza
             * @return supreme pepperoni price
             */
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
            } if(extraSauce){
                price+= 1.00;
            }
            sauce = Sauce.TOMATO;
            return price;
        }
    }
    public static class Seafood extends Pizza {
        /**
         * price(): calculates price of seafood pizza
         * @return seafood pizza price
         */
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
            } if(extraSauce){
                price+= 1.00;
            }
            sauce = Sauce.ALFREDO;
            return price;
        }
    }

}
