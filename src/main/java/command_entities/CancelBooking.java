package command_entities;

import ui.Console;
import ui.InputTypes;
import ui.Switcher;
import ui.Validator;
import util.Toolkit;

public class CancelBooking implements Executable{
    private final Console console;
    private final Switcher switcher;
    private final Toolkit toolkit;

    public CancelBooking(Toolkit toolkit) {
        this.switcher = toolkit.getSwitcher();
        this.console = toolkit.getConsole();
        this.toolkit = toolkit;
    }
    @Override
    public void execute() {
        String bookingId = toolkit.getInput("Booking ID: ", InputTypes.BOOKING_NO);
        if(bookingId.isEmpty()) return;
        console.printLn(switcher.cancelBooking(bookingId));
    }
}
