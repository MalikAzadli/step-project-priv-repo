package command_entities;

import ui.Console;
import ui.InputTypes;
import ui.Switcher;
import ui.Validator;

public class TimetableWithin extends CommandBlueprint {

    public TimetableWithin(Console console, Validator validator, Switcher switcher) {
        super(console, validator, switcher);
    }

    @Override
    public void execute() {
        String day = getInput("Range with days: ", InputTypes.INTEGER);
        if(day.isEmpty()) return;
        console.printLn(switcher.allFlightsWithin(day));
    }
}
