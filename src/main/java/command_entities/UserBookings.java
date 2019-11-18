package command_entities;

import model.User;
import ui.Console;
import ui.Switcher;
import ui.Validator;

public class UserBookings extends CommandBlueprint{

    public UserBookings(Console console, Validator validator, Switcher switcher, User user) {
        super(console, validator, switcher, user);
    }


    @Override
    public void execute() {
        console.printLn(switcher.userBookings(user.getFirstName(), user.getLastName()));
    }
}
