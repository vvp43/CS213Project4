package source;

import java.util.ArrayList;

public abstract class Pizza {
    protected ArrayList<Topping> toppings; //Topping is a enum class
    protected Size size; //Size is a enum class
    protected Sauce sauce; //Sauce is a enum class
    protected boolean extraSauce;
    protected boolean extraCheese;
    public abstract double price(); //polymorphism

    @Override
    public String toString(){ // try not to print it all to one string please thanks!!!
        String fin = "["+getClass().getSimpleName()+"]";
        String a = "";
        for(Topping top : toppings){
            a = a.concat(top+", ");
        }
        fin = fin.concat("["+a);
        if(extraSauce){
            fin = fin.concat("extra sauce, ");
        }
        if(extraCheese){
            fin = fin.concat("extra cheese, ");
        }
        fin = fin.concat("$"+price());
        return fin;
    }
}