package source;

public enum Sauce {
    TOMATO(0),
    ALFREDO(1)
    ;
    final int sauceVal;
    Sauce(int sauceVal){
        this.sauceVal = sauceVal;
    }
}
