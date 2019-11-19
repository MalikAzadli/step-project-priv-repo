package util;

import model.User;
import ui.Console;
import ui.InputTypes;
import ui.Switcher;
import ui.Validator;

public class Toolkit {
    private final Validator validator;
    private final Switcher switcher;
    private final Console console;
    private User user;

    public Validator getValidator() {
        return validator;
    }

    public Switcher getSwitcher() {
        return switcher;
    }

    public Console getConsole() {
        return console;
    }

    public Toolkit(Validator validator, Switcher switcher, Console console) {
        this.validator = validator;
        this.switcher = switcher;
        this.console = console;
    }

    public User getUser(){
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getInput(String message, InputTypes dataType) {
        boolean isValid = false;
        String input = "";
        while (!isValid) {
            console.print(message);
            input = console.readLn();
            if (validator.check(input, InputTypes.EXIT)) {
                return "";
            } else if (validator.check(input.trim(), dataType)) {
                isValid = true;
            }

        }
        return input.trim();
    }
}
