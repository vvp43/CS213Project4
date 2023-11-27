package source;

/**
 * Sauce Enum: Defines sauces available to choose from
 */

public enum Sauce {
    TOMATO("Tomato"),
    ALFREDO("Alfredo")
    ;
    final String sauceVal;

    /**
     * Default constructor
     * @param sauceVal string name of sauce enum.
     */
    Sauce(String sauceVal){
        this.sauceVal = sauceVal;
    }
}
