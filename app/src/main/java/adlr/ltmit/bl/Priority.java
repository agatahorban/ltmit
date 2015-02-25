package adlr.ltmit.bl;

/**
 * Created by Agata on 2015-02-22.
 */

public enum Priority {
    HIGH(1),
    MEDIUM(2),
    LOW(3);

    private int value;

    private Priority(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
