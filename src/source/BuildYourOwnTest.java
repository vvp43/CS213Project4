package source;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
/**
 * Junit test for BuildYourOwnTest class price() method
 */
public class BuildYourOwnTest {

    @Test
    public void check3Items() {
        Pizza a = PizzaMaker.createPizza("buildyourown");
        // 8.99+2.00+0+0+0 = 10.99
        a.sauce = Sauce.TOMATO;
        a.size = Size.MEDIUM;
        a.extraSauce = false;
        a.extraCheese = false;
        a.toppings = new ArrayList<>(Arrays.asList(Topping.STRONTIUM90, Topping.PEPPERONI, Topping.CRAB_MEATS));
        assertEquals(10.99, a.price(),0);
    }
    @Test
    public void checkNullItems() {
        Pizza a = PizzaMaker.createPizza("buildyourown");
        // 8.99+2.00+0+0+0 = 0 (too less toppings)
        a.sauce = Sauce.TOMATO;
        a.size = Size.MEDIUM;
        a.extraSauce = false;
        a.extraCheese = false;
        assertEquals(0, a.price(),0);
    }
    @Test
    public void checkLessThan3Items() {
        Pizza a = PizzaMaker.createPizza("buildyourown");
        // 8.99+2.00+0+0+0 = 10.99
        a.sauce = Sauce.TOMATO;
        a.size = Size.MEDIUM;
        a.toppings = new ArrayList<>(List.of(Topping.STRONTIUM90));
        a.extraSauce = false;
        a.extraCheese = false;
        assertEquals(0, a.price(),0);
    }
    @Test
    public void checkMoreThan7Items() {
        Pizza a = PizzaMaker.createPizza("buildyourown");
        // 8.99+4.00+1.00+1.00+1.49+1.49+1.49+1.49 = 0 (too many toppings)
        a.sauce = Sauce.TOMATO;
        a.size = Size.LARGE;
        a.toppings = new ArrayList<>(Arrays.asList(Topping.STRONTIUM90, Topping.PEPPERONI, Topping.PINEAPPLE, Topping.GREEN_PEPPER, Topping.SAUSAGE, Topping.HAM, Topping.MUSHROOM, Topping.ONION));
        a.extraSauce = false;
        a.extraCheese = false;
        assertEquals(0, a.price(),0);
    }
    @Test
    public void check7Items() {
        Pizza a = PizzaMaker.createPizza("buildyourown");
        // 8.99+4+1.49+1.49+1.49+1.49+ = 18.95 (too many toppings)
        a.sauce = Sauce.TOMATO;
        a.size = Size.LARGE;
        a.toppings = new ArrayList<>(Arrays.asList(Topping.STRONTIUM90, Topping.PEPPERONI, Topping.PINEAPPLE, Topping.GREEN_PEPPER, Topping.SAUSAGE, Topping.HAM, Topping.MUSHROOM));
        a.extraSauce = false;
        a.extraCheese = false;
        assertEquals(18.95, a.price(),0.01);
    }
    @Test
    public void checkExtraCheesePizza() {
        Pizza a = PizzaMaker.createPizza("buildyourown");
        // 8.99+2.00+1.00+1.00 = 12.99 (too many toppings)
        a.sauce = Sauce.TOMATO;
        a.size = Size.MEDIUM;
        a.toppings = new ArrayList<>(Arrays.asList(Topping.STRONTIUM90, Topping.PEPPERONI, Topping.PINEAPPLE));
        a.extraSauce = false;
        a.extraCheese = true;
        assertEquals(11.99, a.price(),0.01);
    }
    @Test
    public void checkExtraSaucePizza() {
        Pizza a = PizzaMaker.createPizza("buildyourown");
        // 8.99+2.00+1.00+1.00 = 12.99 (too many toppings)
        a.sauce = Sauce.TOMATO;
        a.size = Size.MEDIUM;
        a.toppings = new ArrayList<>(Arrays.asList(Topping.STRONTIUM90, Topping.PEPPERONI, Topping.PINEAPPLE));
        a.extraSauce = true;
        a.extraCheese = false;
        assertEquals(11.99, a.price(),0.01);
    }
}