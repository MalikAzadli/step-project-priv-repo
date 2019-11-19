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

public class PassengerBooking implements Executable {
    private final Console console;
    private final Switcher switcher;
    private final Toolkit toolkit;

    public PassengerBooking(Toolkit toolkit) {
        this.switcher = toolkit.getSwitcher();
        this.console = toolkit.getConsole();
        this.toolkit = toolkit;
    }

    @Override
    public void execute() {

        List<Pair> requirements = new ArrayList<>(Arrays.asList(
                new Pair("Name: ", InputTypes.NAME),
                new Pair("Surname: ", InputTypes.SURNAME)));

        List<String> inputs = new ArrayList<>();
        for (Pair p : requirements) {
            String input = toolkit.getInput(p.getMessage(), p.getTypes());
            if (input.isEmpty()) return;
            inputs.add(input);
        }

        console.printLn(switcher.bookingsMadeFor(inputs.get(0), inputs.get(1)));
    }
}
