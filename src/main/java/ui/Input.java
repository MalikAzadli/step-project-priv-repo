package ui;

import java.util.InputMismatchException;

public class Input {

    private final Console console = new SystemConsole();

    public String getSelection() {
        String option = "";
        try {
            option = console.readLn();
        } catch (InputMismatchException e) {
            console.readLn();
        }
        return option;
    }

    public String getfirstName() {
        System.out.println("First name: ");
        return console.readLn();
    }

    public String getLastName() {
        System.out.println("First name: ");
        return console.readLn();
    }

    public String getUsername() {
        System.out.println("Username: ");
        return console.readLn();
    }

    public String getPassword() {
        System.out.println("Password: ");
        return console.readLn();
    }
}
