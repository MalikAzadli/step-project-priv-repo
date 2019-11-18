package ui;

import controller.BookingController;
import controller.FlightController;
import controller.UserController;
import model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import java.util.Optional;

import util.Logger;

import java.util.stream.Collectors;

public class Switcher {

    private final FlightController flightController;
    private final BookingController bookingController;
    private final UserController userController;
    private final Logger logger;

    public Switcher(FlightController flightController, BookingController bookingController, UserController userController) {
        this.flightController = flightController;
        this.bookingController = bookingController;
        this.userController = userController;
        this.logger = new Logger();
    }

    public String allFlightsWithin(String day) {
        Integer range = Integer.parseInt(day);
        StringBuilder stringBuilder = new StringBuilder();
        flightController.findAllWithin(range).forEach(flight -> stringBuilder.append(flight + "\n"));
        logger.log(LocalDateTime.now(), String.format("Flights were displayed within %s days range.", day));
        return stringBuilder.toString();
    }

    public String allFlights() {
        StringBuilder stringBuilder = new StringBuilder();
        flightController.findAllFlights().forEach(flight -> stringBuilder.append(flight + "\n"));
        logger.log(LocalDateTime.now(), String.format("All available flights were displayed."));
        return stringBuilder.toString();
    }

    public String search(String origin, String destination, String date, String peopleCount) {
        StringBuilder string = new StringBuilder();
        flightController.findFlightsMatchedByCriteria(
                Airport.get(origin),
                Airport.get(destination),
                LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                Integer.parseInt(peopleCount))
                .forEach(flight -> string.append(String.format("%s\n", flight.toString())));

        logger.log(LocalDateTime.now(), String.format("Flights were searched with %s, %s, %s, %s",
                origin,
                destination,
                date,
                peopleCount));

        return string.toString();
    }

    public String book(String flightId, List<String[]> fullname, User user) {
        int flightID = Integer.parseInt(flightId);
        Flight flight = flightController.findFlightByFlightId(flightID).get();
        Passenger[] passengers = new Passenger[fullname.size()];
        for(int i = 0; i < fullname.size(); i++){
            passengers[i] = new Passenger(fullname.get(i)[0], fullname.get(i)[1]);
        }
        bookingController.createBooking(flight, user, passengers);
        flightController.refresh();
        logger.log(LocalDateTime.now(), String.format("%d seats were booked for flight number %s",
                fullname.size(),
                flight.getFlightNo()));
        return "All bookings were noted.";
    }

    //TODO think about refactoting this method. eliminate one of them!!!
    public String book(String flightId, String pasName, String pasSurname, User user) {
        int flightID = Integer.parseInt(flightId);
        Flight flight = flightController.findFlightByFlightId(flightID).get();

        bookingController.createBooking(flight, new Passenger(pasName, pasSurname), user);
        flightController.refresh();
        logger.log(LocalDateTime.now(), String.format("Seats was booked for flight number %s for %s %s",
                flight.getFlightNo(),
                pasName,
                pasSurname));
        return "This booking was noted.";
    }

    public String flightDetails(String flightId) {
        Integer id = Integer.parseInt(flightId);
        Optional<Flight> flightByFlightId = flightController.findFlightByFlightId(id);
        logger.log(LocalDateTime.now(), String.format("Details of flight with id #%s were searched.", flightId));
        return flightByFlightId.get().toString();
    }

    public String userBookings(String name, String surname) {
        StringBuilder string = new StringBuilder();
        bookingController
                .findAllBookingsOfUserByName(String.format("%s %s", name, surname))
                .forEach(booking -> string.append(String.format("%s\n", booking.toString())));
        if (string.toString().isEmpty()) return "No bookings were found.";

        logger.log(LocalDateTime.now(),
                String.format("User with name, \"%s\" and surname, \"%s\" searched for his/her bookings.", name, surname));
        return string.toString();
    }

    public String bookingsMadeFor(String name, String surname) {
        StringBuilder string = new StringBuilder();
        bookingController
                .findAllBookingsOfPassengerByName(String.format("%s %s", name, surname))
                .forEach(booking -> string.append(String.format("%s\n", booking.toString())));
        if (string.toString().isEmpty()) return "No bookings were found.";

        logger.log(LocalDateTime.now(),
                String.format("Visitor searched for bookings that were made for \"%s %s\".", name, surname));
        return string.toString();
    }

    public String cancelBooking(String bookingId) {
        Integer id = Integer.parseInt(bookingId);
        bookingController.cancelBooking(id);

        logger.log(LocalDateTime.now(),
                String.format("Booking with id #%s were cancelled.", bookingId));
        return String.format("#%d booking was cancelled.", id);
    }

    public String register(String name, String surname, String email, String password, String username) {
        boolean result = userController.create(new User(username, password, name, surname, email));

        logger.log(LocalDateTime.now(),
                String.format("New user was registered with username of %s", username));
        return result ? "Successfully created" : "Something went wrong. Try later.";
    }

    public Optional<User> login(String username, String password) {
        logger.log(LocalDateTime.now(),
                String.format("User, %s logged in.", username));
        return Optional.of(userController.getUser(username, password));
    }

    public void logout(String username) {
        logger.log(LocalDateTime.now(),
                String.format("User, %s logged out.", username));
    }

    public void load() {
        flightController.load();
        bookingController.load();
    }

    public String exit() {
        flightController.save();
        bookingController.save();
        userController.save();
        logger.log(LocalDateTime.now(), "Program stopped");
        return "All changes were saved.";
    }
}
