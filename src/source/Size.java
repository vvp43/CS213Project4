package source;
/**
 * Sauce Enum: Defines sauces available to choose from
 */
public enum Size {
    SMALL(0),
    MEDIUM(1),
    LARGE(2)
    ;
    final int size;
    /**
     * Default constructor
     * @param size string name of size enum.
     */
    Size(int size){
        this.size = size;
    }
}
