package command_entities;

import ui.Console;
import ui.Switcher;
import ui.Validator;

public class Timetable extends CommandBlueprint {

    public Timetable(Console console, Validator validator, Switcher switcher) {
        super(console, validator, switcher);
    }

    @Override
    public void execute() {
        console.printLn(switcher.allFlights());
    }
}
