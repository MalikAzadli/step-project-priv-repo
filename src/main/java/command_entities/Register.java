package command_entities;

import ui.Console;
import ui.InputTypes;
import ui.Switcher;
import ui.Validator;
import util.Pair;
import util.Toolkit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Register implements Executable {
    private final Validator validator;
    private final Console console;
    private final Switcher switcher;
    private final Toolkit toolkit;

    public Register(Toolkit toolkit) {
        this.validator = toolkit.getValidator();
        this.switcher = toolkit.getSwitcher();
        this.console = toolkit.getConsole();
        this.toolkit = toolkit;
    }

    @Override
    public void execute() {

        List<Pair> requirements = new ArrayList<>(Arrays.asList(
                new Pair("Name (As identified in ID): ", InputTypes.NAME),
                new Pair("Surname (As identified in ID): ", InputTypes.SURNAME),
                new Pair("Email: ", InputTypes.EMAIL),
                new Pair("Password: ", InputTypes.PASSWORD),
                new Pair("Username: ", InputTypes.USERNAME)));

        List<String> inputs = new ArrayList<>();
        for (Pair p : requirements) {
            String input = toolkit.getInput(p.getMessage(), p.getTypes());
            if (input.isEmpty()) return;
            inputs.add(input);
        }

        console.printLn(switcher.register(inputs.get(0), inputs.get(1), inputs.get(2), inputs.get(3), inputs.get(4)));
    }
}
