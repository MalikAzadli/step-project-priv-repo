package command_entities;

import model.User;
import ui.*;
import util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class CommandBlueprint {
    protected Console console;
    protected Validator validator;
    protected Switcher switcher;
    protected boolean isExit;
    protected User user;


    public abstract void execute();

    public CommandBlueprint(Console console, Validator validator, Switcher switcher) {
        this.console = console;
        this.validator = validator;
        this.switcher = switcher;
    }

    public CommandBlueprint(Console console, Validator validator, Switcher switcher, User user) {
        this.console = console;
        this.validator = validator;
        this.switcher = switcher;
        this.user = user;
    }

    public CommandBlueprint(Console console, Switcher switcher) {
        this.console = console;
        this.switcher = switcher;
    }

    protected String getInput(String message, InputTypes dataType) {
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

    public Optional<User> getUser(){
        return Optional.ofNullable(user);
    }
}
