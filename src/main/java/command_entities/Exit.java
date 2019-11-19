package command_entities;

import ui.Console;
import ui.Switcher;
import util.Toolkit;

public class Exit implements Executable {
    private final Console console;
    private final Switcher switcher;

    public Exit(Toolkit toolkit) {
        this.switcher = toolkit.getSwitcher();
        this.console = toolkit.getConsole();
    }

    @Override
    public void execute() {
        console.printLn(switcher.exit());
    }
}
