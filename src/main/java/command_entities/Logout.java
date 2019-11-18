package command_entities;

import model.User;
import ui.Console;
import ui.Switcher;
import ui.Validator;

public class Logout extends CommandBlueprint {

    public Logout(Console console, Validator validator, Switcher switcher, User user) {
        super(console, validator, switcher, user);
    }

    @Override
    public void execute() {
        switcher.logout(user.getUsername());
    }
}
