package command_entities;

import ui.Console;
import ui.InputTypes;
import ui.Switcher;
import ui.Validator;
import util.Toolkit;

public class FlightDetails implements Executable {
    private final Console console;
    private final Switcher switcher;
    private final Toolkit toolkit;

    public FlightDetails(Toolkit toolkit) {
        this.switcher = toolkit.getSwitcher();
        this.console = toolkit.getConsole();
        this.toolkit = toolkit;
    }
    @Override
    public void execute() {
        String flightId = toolkit.getInput("Flight ID: ", InputTypes.FLIGHT_NO);
        if (flightId.isEmpty()) return;
        console.printLn(switcher.flightDetails(flightId));
    }
}
