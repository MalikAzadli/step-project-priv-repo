package controller;

import model.*;
import org.junit.Before;
import org.junit.Test;

import java.awt.print.Book;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.*;

public class BookingControllerTest {

    private BookingController bookingController;

    @Before
    public void before(){
        this.bookingController = new BookingController();
    }

    @Test
    public void findAllBookings() {
        Flight flight1 = new Flight("AB2345",
                100,
                LocalDateTime.now(),
                Airport.FRANKFURT,
                LocalDateTime.now().plusHours(5),
                Airport.LONDON);
        Flight flight2 = new Flight("TS1256",
                120,
                LocalDateTime.now(),
                Airport.BAKU,
                LocalDateTime.now().plusHours(2),
                Airport.NEWYORK);
        User user1 = new User("malikk",
                "T@ylorGone5",
                "Malik",
                "Ibadov",
                "malikk@gmail.com");
        Passenger passenger1 = new Passenger("james", "kulkin");
        Passenger passenger2 = new Passenger("michael", "cane");

        bookingController.createBooking(flight1, passenger1, user1);
        bookingController.createBooking(flight2, passenger2, user1);

        Booking booking1 = new Booking(flight1, passenger1, user1, 1);
        Booking booking2 = new Booking(flight2, passenger2, user1, 2);

        ArrayList<Booking> bookings = new ArrayList<>(Arrays.asList(booking1, booking2));
        assertEquals(bookings, bookingController.findAllBookings());
    }

    @Test
    public void findAllBookingsOfPassengerByName() {
        Flight flight1 = new Flight("AB2345",
                100,
                LocalDateTime.now(),
                Airport.FRANKFURT,
                LocalDateTime.now().plusHours(5),
                Airport.LONDON);
        Flight flight2 = new Flight("TS1256",
                120,
                LocalDateTime.now(),
                Airport.BAKU,
                LocalDateTime.now().plusHours(2),
                Airport.NEWYORK);
        User user1 = new User("malikk",
                "T@ylorGone5",
                "Malik",
                "Ibadov",
                "malikk@gmail.com");
        Passenger passenger1 = new Passenger("james", "kulkin");
        Passenger passenger2 = new Passenger("michael", "cane");

        bookingController.createBooking(flight1, passenger1, user1);
        bookingController.createBooking(flight2, passenger2, user1);

        Booking booking1 = new Booking(flight1, passenger1, user1, 1);
        Booking booking2 = new Booking(flight2, passenger2, user1, 2);

        ArrayList<Booking> bookings = new ArrayList<>(Arrays.asList(booking2));
        assertEquals(bookings, bookingController.findAllBookingsOfPassengerByName(passenger2.getFullName()));
        assertEquals(new ArrayList<>(), bookingController.findAllBookingsOfPassengerByName("lea seydoux"));
    }

    @Test
    public void findAllBookingsOfUserByName() {
        Flight flight1 = new Flight("AB2345",
                100,
                LocalDateTime.now(),
                Airport.FRANKFURT,
                LocalDateTime.now().plusHours(5),
                Airport.LONDON);
        Flight flight2 = new Flight("TS1256",
                120,
                LocalDateTime.now(),
                Airport.BAKU,
                LocalDateTime.now().plusHours(2),
                Airport.NEWYORK);
        User user1 = new User("malikk",
                "T@ylorGone5",
                "Malik",
                "Ibadov",
                "malikk@gmail.com");
        Passenger passenger = user1.getAsPassenger();
        Passenger passenger1 = new Passenger("james", "kulkin");

        bookingController.createBooking(flight1, passenger, user1);
        bookingController.createBooking(flight2, passenger, user1);
        bookingController.createBooking(flight1, passenger1, user1);

        Booking booking1 = new Booking(flight1, passenger, user1, 1);
        Booking booking2 = new Booking(flight2, passenger, user1, 2);
        Booking booking3 = new Booking(flight2, passenger1, user1, 3);

        ArrayList<Booking> bookings = new ArrayList<>(Arrays.asList(booking1, booking2, booking3));

        assertEquals(bookings, bookingController.findAllBookingsOfUserByName(user1.getFullName()));
    }

    @Test
    public void findBookingById() {
        Flight flight1 = new Flight("AB2345",
                100,
                LocalDateTime.now(),
                Airport.FRANKFURT,
                LocalDateTime.now().plusHours(5),
                Airport.LONDON);
        Flight flight2 = new Flight("TS1256",
                120,
                LocalDateTime.now(),
                Airport.BAKU,
                LocalDateTime.now().plusHours(2),
                Airport.NEWYORK);
        User user1 = new User("malikk",
                "T@ylorGone5",
                "Malik",
                "Ibadov",
                "malikk@gmail.com");
        Passenger passenger = user1.getAsPassenger();
        Passenger passenger1 = new Passenger("james", "kulkin");

        bookingController.createBooking(flight1, passenger, user1);
        bookingController.createBooking(flight2, passenger, user1);
        bookingController.createBooking(flight1, passenger1, user1);

        Booking booking1 = new Booking(flight1, passenger, user1, 1);
        Booking booking2 = new Booking(flight2, passenger, user1, 2);
        Booking booking3 = new Booking(flight2, passenger1, user1, 3);

        assertEquals(Optional.of(booking1), bookingController.findBookingById(1));
        assertEquals(Optional.empty(), bookingController.findBookingById(4));
    }
}