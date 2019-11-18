package command_entities;

import ui.Console;
import ui.Switcher;

public class Exit extends CommandBlueprint {

    public Exit(Console console, Switcher switcher) {
        super(console, switcher);
    }

    @Override
    public void execute() {
        console.printLn(switcher.exit());
    }
}
