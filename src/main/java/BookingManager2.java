
import command_entities.*;
import controller.BookingController;
import controller.FlightController;
import controller.UserController;
import model.User;
import ui.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookingManager2 {
    private final Console console;
    private final Display display;
    private final Database database;
    private final Switcher switcher;
    private final Validator validator;
    private Commands command;
    private User currentUser;
    private CommandBlueprint commandBasic;
    private States state = States.VISITOR;

    public BookingManager2() {
        this.console = new SystemConsole();
        this.display = new Display();
        FlightController flightController = new FlightController();
        BookingController bookingController = new BookingController();
        UserController userController = new UserController();
        this.database = new Database(userController, flightController, bookingController);
        this.switcher = new Switcher(flightController, bookingController, userController);
        this.validator = new Validator(flightController, bookingController, userController, console);
    }

    public void run() {
        database.init();
        boolean isEnd = true;
        while (isEnd) {
            console.print(display.display(state));
            String line = console.readLn();
            command = validator.resolveCommand(line, state);
            switch (command) {
                case LOGIN: {
                    commandBasic = new Login(console, validator, switcher);
                    commandBasic.execute();
                    if (commandBasic.getUser().isPresent()) {
                        currentUser = commandBasic.getUser().get();
                        state = States.USER;
                    }
                    break;
                }
                case REGISTER: {
                    commandBasic = new Register(console, validator, switcher);
                    commandBasic.execute();
                    break;
                }
                case TIMETABLE: {
                    commandBasic = new Timetable(console, validator, switcher);
                    commandBasic.execute();
                    break;
                }
                case TIMETABLE_WITHIN: {
                    commandBasic = new TimetableWithin(console, validator, switcher);
                    commandBasic.execute();
                    break;
                }
                case SEARCH_BOOK: {
                    commandBasic = new SearchAndBook(console, validator, switcher, currentUser);
                    commandBasic.execute();
                    break;
                }
                case PASSENGER_BOOKINGS: {
                    commandBasic = new PassengerBooking(console, validator, switcher);
                    commandBasic.execute();
                    break;
                }
                case USER_BOOKINGS: {
                    commandBasic = new UserBookings(console, validator, switcher, currentUser);
                    commandBasic.execute();
                    break;
                }
                case FLIGHT_DETAILS: {
                    commandBasic = new FlightDetails(console, validator, switcher);
                    commandBasic.execute();
                    break;
                }
                case CANCEL_BOOKING: {
                    commandBasic = new CancelBooking(console, validator, switcher);
                    commandBasic.execute();
                    break;
                }
                case HELP: {
                    break;
                }
                case LOGOUT: {
                    commandBasic = new Logout(console, validator, switcher, currentUser);
                    commandBasic.execute();
                    state = States.VISITOR;
                    break;
                }
                case EXIT: {
                    commandBasic = new Exit(console, switcher);
                    commandBasic.execute();
                    isEnd = false;
                    break;
                }
                case NO_COMMAND: {
                    console.printLn(Commands.NO_COMMAND.getDescription());
                    break;
                }
            }

        }
    }
}
