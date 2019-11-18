package command_entities;

import ui.Console;
import ui.InputTypes;
import ui.Switcher;
import ui.Validator;

public class FlightDetails extends CommandBlueprint {

    public FlightDetails(Console console, Validator validator, Switcher switcher) {
        super(console, validator, switcher);
    }

    @Override
    public void execute() {
        String flightId = getInput("Flight ID: ", InputTypes.FLIGHT_NO);
        if (flightId.isEmpty()) return;
        console.printLn(switcher.flightDetails(flightId));
    }
}
