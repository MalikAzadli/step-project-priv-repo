package command_entities;

import ui.Console;
import ui.InputTypes;
import ui.Switcher;
import ui.Validator;

public class CancelBooking extends CommandBlueprint{

    public CancelBooking(Console console, Validator validator, Switcher switcher) {
        super(console,validator,switcher);
    }

    @Override
    public void execute() {
        String bookingId = getInput("Booking ID: ", InputTypes.BOOKING_NO);
        if(bookingId.isEmpty()) return;
        console.printLn(switcher.cancelBooking(bookingId));
    }
}
