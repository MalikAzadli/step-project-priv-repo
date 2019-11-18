
import controller.BookingController;
import controller.FlightController;
import controller.UserController;
import model.User;
import ui.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookingManager {
    private final Console console;
    private final Display display;
    private final Database database;
    private final Switcher switcher;
    private final Validator validator;
    private Commands command;
    private States state = States.VISITOR;
    private User currentUser;

    public BookingManager() {
        this.console = new SystemConsole();
        this.display = new Display();
        FlightController flightController = new FlightController();
        BookingController bookingController = new BookingController();
        UserController userController = new UserController();
        this.database = new Database(userController, flightController, bookingController);
        this.switcher = new Switcher(flightController, bookingController, userController);
        this.validator = new Validator(flightController, bookingController, userController, console);
    }

    //TODO think about exit. It does not work as expected
    public void run() {
        database.init();
        boolean isEnd = true;
        while (isEnd) {
            console.print(display.display(state));
            String line = console.readLn();
            command = validator.resolveCommand(line, state);
            switch (command) {
                case LOGIN: {
                    String username = getInput("Username: ", InputTypes.REGISTERED_USERNAME);
                    String password = getInput("Password: ", InputTypes.REGISTERED_PASSWORD);
                    Optional<User> result = switcher.login(username, password);
                    if (result.isPresent()) {
                        state = States.USER;
                        currentUser = result.get();
                    }
                    break;
                }
                case REGISTER: {
                    String name = getInput("Name (As identified in ID): ", InputTypes.NAME);
                    String surname = getInput("Surname (As identified in ID): ", InputTypes.SURNAME);
                    String password = getInput("Password: ", InputTypes.PASSWORD);
                    String email = getInput("Email: ", InputTypes.EMAIL);
                    String username = getInput("Username: ", InputTypes.USERNAME);
                    console.printLn(switcher.register(name, surname, email, password, username));
                    break;
                }
                case TIMETABLE: {
                    console.printLn(switcher.allFlights());
                    break;
                }
                case TIMETABLE_WITHIN: {
                    String day = getInput("Range with days: ", InputTypes.INTEGER);
                    console.printLn(switcher.allFlightsWithin(day));
                    break;
                }
                case SEARCH_BOOK: {
                    String origin = getInput("Origin: ", InputTypes.CITY);
                    String destination = getInput("Destination: ", InputTypes.CITY);
                    String date = getInput("Date (dd/mm/yyyy): ", InputTypes.DATE);
                    String peopleCount = getInput("Number of people: ", InputTypes.INTEGER);
                    String result = switcher.search(origin, destination, date, peopleCount);
                    if(result.isEmpty()) {console.printLn("No flights were found for that criterias."); break;}
                    console.printLn(switcher.search(origin, destination, date, peopleCount));
                    String flightNo = getInput("Enter fligth ID to book or 'exit': ", InputTypes.FLIGHT_NO);

                    List<String[]> passengers = getUsers(peopleCount);
                    console.printLn(switcher.book(flightNo, passengers, currentUser));
                    break;
                }
                case PASSENGER_BOOKINGS: {
                    String name = getInput("Name: ", InputTypes.NAME);
                    String surname = getInput("Surname: ", InputTypes.SURNAME);
                    console.printLn(switcher.bookingsMadeFor(name, surname));
                    break;
                }
                case USER_BOOKINGS: {
                    console.printLn(switcher.userBookings(currentUser.getFirstName(), currentUser.getLastName()));
                    break;
                }
                case FLIGHT_DETAILS: {
                    String flightId = getInput("Flight ID: ", InputTypes.FLIGHT_NO);
                    console.printLn(switcher.flightDetails(flightId));
                    break;
                }
                case CANCEL_BOOKING: {
                    String bookingId = getInput("Booking ID: ", InputTypes.BOOKING_NO);
                    console.printLn(switcher.cancelBooking(bookingId));
                    break;
                }
                case HELP: {
                    break;
                }
                case LOGOUT: {
                    switcher.logout(currentUser.getUsername());
                    state = States.VISITOR;
                    break;
                }
                case EXIT: {
                    console.printLn(switcher.exit());
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

    private String getInput(String message, InputTypes dataType) {
        boolean isValid = false;
        String input = "";
        while (!isValid) {
            console.print(message);
            input = console.readLn();
            if (validator.check(input, InputTypes.EXIT)) {
                isValid = true;
                this.command = Commands.EXIT;
            } else if (validator.check(input.trim(), dataType)) {
                isValid = true;
            }

        }
        return input.trim();
    }

    private List<String[]> getUsers(String number) {
        int count = Integer.parseInt(number);
        ArrayList<String[]> users = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            String name = getInput("Name: ", InputTypes.NAME);
            String surname = getInput("Surname: ", InputTypes.SURNAME);
            users.add(new String[]{name.trim(), surname.trim()});
        }
        return users;
    }


}
