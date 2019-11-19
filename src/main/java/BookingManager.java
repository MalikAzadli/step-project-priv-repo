import command_entities.*;
import controller.BookingController;
import controller.FlightController;
import controller.UserController;
import ui.*;
import util.Toolkit;

public class BookingManager {
    private final Console console;
    private final Display display;
    private final Database database;
    private final Validator validator;
    private States state = States.VISITOR;
    private Toolkit toolkit;

    public BookingManager() {
        this.console = new SystemConsole();
        this.display = new Display();
        FlightController flightController = new FlightController();
        BookingController bookingController = new BookingController();
        UserController userController = new UserController();
        this.database = new Database(userController, flightController, bookingController);
        Switcher switcher = new Switcher(flightController, bookingController, userController);
        this.validator = new Validator(flightController, bookingController, userController, console);
        this.toolkit = new Toolkit(this.validator, switcher, this.console);
    }

    public void run() {
        database.init();
        boolean isEnd = true;
        while (isEnd) {
            console.print(display.display(state));
            String line = console.readLn();
            Commands command = validator.resolveCommand(line, state);
            Executable commandBasic;
            switch (command) {
                case LOGIN: {
                    commandBasic = new Login(toolkit);
                    commandBasic.execute();
                    if (toolkit.isUserActivated()) state = States.USER;
                    break;
                }
                case REGISTER: {
                    commandBasic = new Register(toolkit);
                    commandBasic.execute();
                    break;
                }
                case TIMETABLE: {
                    commandBasic = new Timetable(toolkit);
                    commandBasic.execute();
                    break;
                }
                case TIMETABLE_WITHIN: {
                    commandBasic = new TimetableWithin(toolkit);
                    commandBasic.execute();
                    break;
                }
                case SEARCH_BOOK: {
                    commandBasic = new SearchAndBook(toolkit);
                    commandBasic.execute();
                    break;
                }
                case PASSENGER_BOOKINGS: {
                    commandBasic = new PassengerBooking(toolkit);
                    commandBasic.execute();
                    break;
                }
                case USER_BOOKINGS: {
                    commandBasic = new UserBookings(toolkit);
                    commandBasic.execute();
                    break;
                }
                case FLIGHT_DETAILS: {
                    commandBasic = new FlightDetails(toolkit);
                    commandBasic.execute();
                    break;
                }
                case CANCEL_BOOKING: {
                    commandBasic = new CancelBooking(toolkit);
                    commandBasic.execute();
                    break;
                }
                case LOGOUT: {
                    commandBasic = new Logout(toolkit);
                    commandBasic.execute();
                    if (!toolkit.isUserActivated()) state = States.VISITOR;
                    break;
                }
                case EXIT: {
                    commandBasic = new Exit(toolkit);
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
