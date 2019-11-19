package command_entities;

import ui.Console;
import ui.InputTypes;
import ui.Switcher;
import util.Toolkit;

public class TimetableWithin implements Executable {

    private final Console console;
    private final Switcher switcher;
    private final Toolkit toolkit;

    public TimetableWithin(Toolkit toolkit) {
        this.switcher = toolkit.getSwitcher();
        this.console = toolkit.getConsole();
        this.toolkit = toolkit;
    }

    @Override
    public void execute() {
        String day = toolkit.getInput("Range with days: ", InputTypes.INTEGER);
        if (day.isEmpty()) return;
        console.printLn(switcher.allFlightsWithin(day));
    }
}
