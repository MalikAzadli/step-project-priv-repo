package command_entities;

import model.User;
import ui.*;
import util.Toolkit;

import java.util.Optional;

public class Login implements Executable {
    private final Validator validator;
    private final Console console;
    private final Switcher switcher;
    private final Toolkit toolkit;

    public Login(Toolkit toolkit) {
        this.validator = toolkit.getValidator();
        this.switcher = toolkit.getSwitcher();
        this.console = toolkit.getConsole();
        this.toolkit = toolkit;
    }

    @Override
    public void execute() {
        String[] credentials = new String[2];
        boolean isExit = false;
        while (!isExit) {
            credentials = getCredentials();

            if (credentials[0].equalsIgnoreCase(Commands.EXIT.toString())
                    || credentials[1].equalsIgnoreCase(Commands.EXIT.toString())) return;

            String result = validator.isValidUser(credentials[0], credentials[1]);
            if (result.isEmpty()) isExit = true;
            else console.printLn(result);
        }

        Optional<User> result = switcher.login(credentials[0], credentials[1]);
        if (result.isPresent()) {
            toolkit.setUserActivated(true);
            toolkit.setUser(result.get());
        }
    }

    private String[] getCredentials() {
        String[] credentials = new String[2];
        console.print("Username: ");
        credentials[0] = console.readLn().trim();
        if (credentials[0].equalsIgnoreCase(Commands.EXIT.toString())) return credentials;
        console.print("Password: ");
        credentials[1] = console.readLn().trim();
        if (credentials[1].trim().equalsIgnoreCase(Commands.EXIT.toString())) return credentials;
        return credentials;
    }
}
