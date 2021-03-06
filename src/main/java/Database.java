import controller.BookingController;
import controller.FlightController;
import controller.UserController;
import model.Flight;
import util.FlightGenerator;

import java.io.File;
import java.util.List;

public class Database {

    private final UserController userController;
    private final FlightController flightController;
    private final BookingController bookingController;
    private final FlightGenerator flightGenerator;

    public Database(UserController userController,
                    FlightController flightController,
                    BookingController bookingController) {
        this.userController = userController;
        this.flightController = flightController;
        this.bookingController = bookingController;
        this.flightGenerator = new FlightGenerator();
    }

    public void init() {
        File file = new File("./db", "flights.txt");
        if (file.length()!=0) {
            flightController.load();
        } else {
            List<Flight> flights = flightGenerator.generateFlights(50);
            for (Flight f : flights) {
                flightController.create(f);
            }
            flightController.save();
        }
        if (new File("./db","users.txt").exists()) {
            userController.load();
        }

        if (new File("./db","bookings.txt").exists()) {
            bookingController.load();
        }
    }
}
