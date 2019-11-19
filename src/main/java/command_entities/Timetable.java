package command_entities;

import ui.Console;
import ui.Switcher;
import util.Toolkit;

public class Timetable implements Executable {

    private final Console console;
    private final Switcher switcher;

    public Timetable(Toolkit toolkit){
        this.switcher = toolkit.getSwitcher();
        this.console = toolkit.getConsole();
    }

    @Override
    public void execute() {
        console.printLn(switcher.allFlights());
    }
}
