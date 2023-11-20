package source;

public enum Size {
    SMALL(0),
    MEDIUM(1),
    LARGE(2)
    ;
    final int size;
    Size(int size){
        this.size = size;
    }
}
