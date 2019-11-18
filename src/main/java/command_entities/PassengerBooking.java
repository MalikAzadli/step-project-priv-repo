package command_entities;

import ui.Console;
import ui.InputTypes;
import ui.Switcher;
import ui.Validator;
import util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PassengerBooking extends CommandBlueprint {

    public PassengerBooking(Console console, Validator validator, Switcher switcher) {
        super(console,validator,switcher);
    }

    @Override
    public void execute() {

        List<Pair> requirements = new ArrayList<>(Arrays.asList(
                new Pair("Name: ", InputTypes.NAME),
                new Pair("Surname: ", InputTypes.SURNAME)));

        List<String> inputs = new ArrayList<>();
        for(Pair p: requirements){
            String input = getInput(p.getMessage(), p.getTypes());
            if(input.isEmpty()) return;
            inputs.add(input);
        }

        console.printLn(switcher.bookingsMadeFor(inputs.get(0), inputs.get(1)));
    }
}
