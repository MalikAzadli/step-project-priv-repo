package command_entities;

import model.User;
import ui.Console;
import ui.Switcher;
import ui.Validator;
import util.Toolkit;

public class UserBookings implements Executable {
    private final Console console;
    private final Switcher switcher;
    private final Toolkit toolkit;

    public UserBookings(Toolkit toolkit) {
        this.switcher = toolkit.getSwitcher();
        this.console = toolkit.getConsole();
        this.toolkit = toolkit;
    }

    @Override
    public void execute() {
        console.printLn(switcher.userBookings(toolkit.getUser().getFirstName(), toolkit.getUser().getLastName()));
    }
}
