package util;

import ui.InputTypes;

public class Pair {
    private final String message;
    private final InputTypes types;

    public Pair(String message, InputTypes types) {
        this.message = message;
        this.types = types;
    }

    public String getMessage() {
        return message;
    }

    public InputTypes getTypes() {
        return types;
    }
}
