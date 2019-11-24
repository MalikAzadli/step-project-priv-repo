package service;

import model.*;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class BookingServiceImplTest {

    private BookingService bookingService;

    @Before
    public void before(){
        this.bookingService = new BookingServiceImpl();
    }

    @Test
    public void findAllBookings() {
        Flight flight = new Flight("AB2345",
                100,
                LocalDateTime.now(),
                Airport.FRANKFURT,
                LocalDateTime.now().plusHours(5),
                Airport.LONDON);
        Passenger passenger1 = new Passenger("james", "kulkin");
        Passenger passenger2 = new Passenger("michael", "cane");
        User user = new User("malikk",
                "T@ylorGone5",
                "Malik",
                "Ibadov",
                "malikk@gmail.com");
        bookingService.createBooking(flight, passenger1, user);
        bookingService.createBooking(flight, passenger2, user);

        Booking booking1 = new Booking(flight, passenger1, user, 1);
        Booking booking2 = new Booking(flight, passenger2, user, 2);

        ArrayList<Booking> bookings = new ArrayList<>();
        bookings.add(booking1);
        bookings.add(booking2);

        assertEquals(bookings, bookingService.findAllBookings());
    }

    @Test
    public void findAllBookingsOfPassengerById1() {
        Flight flight1 = new Flight("AB2345",
                100,
                LocalDateTime.now(),
                Airport.FRANKFURT,
                LocalDateTime.now().plusHours(5),
                Airport.LONDON);
        Flight flight2 = new Flight("TS2348",
                90,
                LocalDateTime.now(),
                Airport.NEWYORK,
                LocalDateTime.now().plusHours(2),
                Airport.MOSCOW);
        User user = new User("malikk",
                "T@ylorGone5",
                "Malik",
                "Ibadov",
                "malikk@gmail.com");

        Passenger passenger1 = new Passenger("james", "kulkin");
        Passenger passenger2 = new Passenger("michael", "cane");

        bookingService.createBooking(flight1, passenger1, user);
        bookingService.createBooking(flight2, passenger1, user);
        bookingService.createBooking(flight2, passenger2, user);

        Booking booking1 = new Booking(flight1, passenger1, user, 1);
        Booking booking2 = new Booking(flight2, passenger1, user, 2);
        Booking booking3 = new Booking(flight2, passenger2, user, 3);

        ArrayList<Booking> bookings = new ArrayList<>();
        bookings.add(booking1);
        bookings.add(booking2);
        bookings.add(booking3);

        List<Booking> allBookingsOfPass1 = bookings.stream()
                .filter(booking -> booking.getPassenger().getId()==passenger1.getId())
                .collect(Collectors.toList());

        assertEquals(allBookingsOfPass1, bookingService.findAllBookingsOfPassengerById(passenger1.getId()));
    }

    @Test
    public void findAllBookingsOfPassengerById2() {
        Flight flight1 = new Flight("AB2345",
                100,
                LocalDateTime.now(),
                Airport.FRANKFURT,
                LocalDateTime.now().plusHours(5),
                Airport.LONDON);
        Flight flight2 = new Flight("TS2348",
                90,
                LocalDateTime.now(),
                Airport.NEWYORK,
                LocalDateTime.now().plusHours(2),
                Airport.MOSCOW);
        User user = new User("malikk",
                "T@ylorGone5",
                "Malik",
                "Ibadov",
                "malikk@gmail.com");

        Passenger passenger1 = new Passenger("james", "kulkin");
        Passenger passenger2 = new Passenger("michael", "cane");

        bookingService.createBooking(flight1, passenger1, user);
        bookingService.createBooking(flight2, passenger1, user);
        bookingService.createBooking(flight2, passenger2, user);

        Booking booking1 = new Booking(flight1, passenger1, user, 1);
        Booking booking2 = new Booking(flight2, passenger1, user, 2);
        Booking booking3 = new Booking(flight2, passenger2, user, 3);

        ArrayList<Booking> bookings = new ArrayList<>();
        bookings.add(booking1);
        bookings.add(booking2);
        bookings.add(booking3);

        List<Booking> allBookingsOfPass1 = bookings.stream()
                .filter(booking -> booking.getPassenger().getId()==4)
                .collect(Collectors.toList());

        assertEquals(allBookingsOfPass1, bookingService.findAllBookingsOfPassengerById(4));
    }

    @Test
    public void findAllBookingsOfUserById1() {
        Flight flight1 = new Flight("AB2345",
                100,
                LocalDateTime.now(),
                Airport.FRANKFURT,
                LocalDateTime.now().plusHours(5),
                Airport.LONDON);
        Flight flight2 = new Flight("TS2348",
                90,
                LocalDateTime.now(),
                Airport.NEWYORK,
                LocalDateTime.now().plusHours(2),
                Airport.MOSCOW);
        User user = new User("malikk",
                "T@ylorGone5",
                "Malik",
                "Ibadov",
                "malikk@gmail.com");

        Passenger passenger1 = new Passenger("james", "kulkin");
        Passenger passenger2 = new Passenger("michael", "cane");

        bookingService.createBooking(flight1, passenger1, user);
        bookingService.createBooking(flight2, passenger1, user);
        bookingService.createBooking(flight2, passenger2, user);

        Booking booking1 = new Booking(flight1, passenger1, user, 1);
        Booking booking2 = new Booking(flight2, passenger1, user, 2);
        Booking booking3 = new Booking(flight2, passenger2, user, 3);

        ArrayList<Booking> bookings = new ArrayList<>();
        bookings.add(booking1);
        bookings.add(booking2);
        bookings.add(booking3);

        List<Booking> allBookingsOfPass1 = bookings.stream()
                .filter(booking -> booking.getUser().getId()==user.getId())
                .collect(Collectors.toList());

        assertEquals(allBookingsOfPass1, bookingService.findAllBookingsOfUserById(user.getId()));
    }


    @Test
    public void findAllBookingsOfUserById2() {
        Flight flight1 = new Flight("AB2345",
                100,
                LocalDateTime.now(),
                Airport.FRANKFURT,
                LocalDateTime.now().plusHours(5),
                Airport.LONDON);
        Flight flight2 = new Flight("TS2348",
                90,
                LocalDateTime.now(),
                Airport.NEWYORK,
                LocalDateTime.now().plusHours(2),
                Airport.MOSCOW);
        User user = new User("malikk",
                "T@ylorGone5",
                "Malik",
                "Ibadov",
                "malikk@gmail.com");

        Passenger passenger1 = new Passenger("james", "kulkin");
        Passenger passenger2 = new Passenger("michael", "cane");

        bookingService.createBooking(flight1, passenger1, user);
        bookingService.createBooking(flight2, passenger1, user);
        bookingService.createBooking(flight2, passenger2, user);

        Booking booking1 = new Booking(flight1, passenger1, user, 1);
        Booking booking2 = new Booking(flight2, passenger1, user, 2);
        Booking booking3 = new Booking(flight2, passenger2, user, 3);

        ArrayList<Booking> bookings = new ArrayList<>();
        bookings.add(booking1);
        bookings.add(booking2);
        bookings.add(booking3);

        List<Booking> allBookingsOfPass1 = bookings.stream()
                .filter(booking -> booking.getUser().getId()==13)
                .collect(Collectors.toList());

        assertEquals(allBookingsOfPass1, bookingService.findAllBookingsOfUserById(13));
    }


    @Test
    public void findAllBookingsOPassengerByName1() {
        Flight flight1 = new Flight("AB2345",
                100,
                LocalDateTime.now(),
                Airport.FRANKFURT,
                LocalDateTime.now().plusHours(5),
                Airport.LONDON);
        Flight flight2 = new Flight("TS2348",
                90,
                LocalDateTime.now(),
                Airport.NEWYORK,
                LocalDateTime.now().plusHours(2),
                Airport.MOSCOW);
        User user = new User("malikk",
                "T@ylorGone5",
                "Malik",
                "Ibadov",
                "malikk@gmail.com");

        Passenger passenger1 = new Passenger("james", "kulkin");
        Passenger passenger2 = new Passenger("michael", "cane");

        bookingService.createBooking(flight1, passenger1, user);
        bookingService.createBooking(flight2, passenger1, user);
        bookingService.createBooking(flight2, passenger2, user);

        Booking booking1 = new Booking(flight1, passenger1, user, 1);
        Booking booking2 = new Booking(flight2, passenger1, user, 2);
        Booking booking3 = new Booking(flight2, passenger2, user, 3);

        ArrayList<Booking> bookings = new ArrayList<>();
        bookings.add(booking1);
        bookings.add(booking2);
        bookings.add(booking3);

        List<Booking> allBookingsOfPass1 = bookings.stream()
                .filter(booking -> booking.getPassenger().getFullName().equalsIgnoreCase(passenger1.getFullName()))
                .collect(Collectors.toList());

        assertEquals(allBookingsOfPass1, bookingService.findAllBookingsOPassengerByName(passenger1.getFullName()));
    }


    @Test
    public void findAllBookingsOPassengerByName2() {
        Flight flight1 = new Flight("AB2345",
                100,
                LocalDateTime.now(),
                Airport.FRANKFURT,
                LocalDateTime.now().plusHours(5),
                Airport.LONDON);
        Flight flight2 = new Flight("TS2348",
                90,
                LocalDateTime.now(),
                Airport.NEWYORK,
                LocalDateTime.now().plusHours(2),
                Airport.MOSCOW);
        User user = new User("malikk",
                "T@ylorGone5",
                "Malik",
                "Ibadov",
                "malikk@gmail.com");

        Passenger passenger1 = new Passenger("james", "kulkin");
        Passenger passenger2 = new Passenger("michael", "cane");

        bookingService.createBooking(flight1, passenger1, user);
        bookingService.createBooking(flight2, passenger1, user);
        bookingService.createBooking(flight2, passenger2, user);

        Booking booking1 = new Booking(flight1, passenger1, user, 1);
        Booking booking2 = new Booking(flight2, passenger1, user, 2);
        Booking booking3 = new Booking(flight2, passenger2, user, 3);

        ArrayList<Booking> bookings = new ArrayList<>();
        bookings.add(booking1);
        bookings.add(booking2);
        bookings.add(booking3);

        List<Booking> allBookingsOfPass1 = bookings.stream()
                .filter(booking -> booking.getPassenger().getFullName().equalsIgnoreCase("Boris KArloff"))
                .collect(Collectors.toList());

        assertEquals(allBookingsOfPass1, bookingService.findAllBookingsOPassengerByName("Boris Karloff"));
    }


    @Test
    public void findAllBookingsOfUserByName1() {
        Flight flight1 = new Flight("AB2345",
                100,
                LocalDateTime.now(),
                Airport.FRANKFURT,
                LocalDateTime.now().plusHours(5),
                Airport.LONDON);
        Flight flight2 = new Flight("TS2348",
                90,
                LocalDateTime.now(),
                Airport.NEWYORK,
                LocalDateTime.now().plusHours(2),
                Airport.MOSCOW);
        User user = new User("malikk",
                "T@ylorGone5",
                "Malik",
                "Ibadov",
                "malikk@gmail.com");

        Passenger passenger1 = new Passenger("james", "kulkin");
        Passenger passenger2 = new Passenger("michael", "cane");

        bookingService.createBooking(flight1, passenger1, user);
        bookingService.createBooking(flight2, passenger1, user);
        bookingService.createBooking(flight2, passenger2, user);

        Booking booking1 = new Booking(flight1, passenger1, user, 1);
        Booking booking2 = new Booking(flight2, passenger1, user, 2);
        Booking booking3 = new Booking(flight2, passenger2, user, 3);

        ArrayList<Booking> bookings = new ArrayList<>();
        bookings.add(booking1);
        bookings.add(booking2);
        bookings.add(booking3);

        List<Booking> allBookingsOfPass1 = bookings.stream()
                .filter(booking -> booking.getUser().getFullName().equalsIgnoreCase("Malik Ibadov"))
                .collect(Collectors.toList());

        assertEquals(allBookingsOfPass1, bookingService.findAllBookingsOfUserByName("Malik Ibadov"));
    }

    @Test
    public void findAllBookingsOfUserByName2() {
        Flight flight1 = new Flight("AB2345",
                100,
                LocalDateTime.now(),
                Airport.FRANKFURT,
                LocalDateTime.now().plusHours(5),
                Airport.LONDON);
        Flight flight2 = new Flight("TS2348",
                90,
                LocalDateTime.now(),
                Airport.NEWYORK,
                LocalDateTime.now().plusHours(2),
                Airport.MOSCOW);
        User user = new User("malikk",
                "T@ylorGone5",
                "Malik",
                "Ibadov",
                "malikk@gmail.com");

        Passenger passenger1 = new Passenger("james", "kulkin");
        Passenger passenger2 = new Passenger("michael", "cane");

        bookingService.createBooking(flight1, passenger1, user);
        bookingService.createBooking(flight2, passenger1, user);
        bookingService.createBooking(flight2, passenger2, user);

        Booking booking1 = new Booking(flight1, passenger1, user, 1);
        Booking booking2 = new Booking(flight2, passenger1, user, 2);
        Booking booking3 = new Booking(flight2, passenger2, user, 3);

        ArrayList<Booking> bookings = new ArrayList<>();
        bookings.add(booking1);
        bookings.add(booking2);
        bookings.add(booking3);

        List<Booking> foundBookings = bookings.stream()
                .filter(booking -> booking.getUser().getFullName().equalsIgnoreCase("Val Newton"))
                .collect(Collectors.toList());

        assertEquals(foundBookings, bookingService.findAllBookingsOfUserByName("Val Newton"));
    }

    @Test
    public void findBookingById1() {
        Flight flight1 = new Flight("AB2345",
                100,
                LocalDateTime.now(),
                Airport.FRANKFURT,
                LocalDateTime.now().plusHours(5),
                Airport.LONDON);
        Flight flight2 = new Flight("TS2348",
                90,
                LocalDateTime.now(),
                Airport.NEWYORK,
                LocalDateTime.now().plusHours(2),
                Airport.MOSCOW);
        User user = new User("malikk",
                "T@ylorGone5",
                "Malik",
                "Ibadov",
                "malikk@gmail.com");

        Passenger passenger1 = new Passenger("james", "kulkin");
        Passenger passenger2 = new Passenger("michael", "cane");

        bookingService.createBooking(flight1, passenger1, user);
        bookingService.createBooking(flight2, passenger1, user);
        bookingService.createBooking(flight2, passenger2, user);

        Booking booking1 = new Booking(flight1, passenger1, user, 1);
        Booking booking2 = new Booking(flight2, passenger1, user, 2);
        Booking booking3 = new Booking(flight2, passenger2, user, 3);

        ArrayList<Booking> bookings = new ArrayList<>();
        bookings.add(booking1);
        bookings.add(booking2);
        bookings.add(booking3);

        Optional<Booking> foundBookings = bookings.stream()
                .filter(booking -> booking.getBookingId() == booking1.getBookingId())
                .findFirst();
        assertEquals(foundBookings, bookingService.findBookingById(booking1.getBookingId()));
    }

    @Test
    public void findBookingById2() {
        Flight flight1 = new Flight("AB2345",
                100,
                LocalDateTime.now(),
                Airport.FRANKFURT,
                LocalDateTime.now().plusHours(5),
                Airport.LONDON);
        Flight flight2 = new Flight("TS2348",
                90,
                LocalDateTime.now(),
                Airport.NEWYORK,
                LocalDateTime.now().plusHours(2),
                Airport.MOSCOW);
        User user = new User("malikk",
                "T@ylorGone5",
                "Malik",
                "Ibadov",
                "malikk@gmail.com");

        Passenger passenger1 = new Passenger("james", "kulkin");
        Passenger passenger2 = new Passenger("michael", "cane");

        bookingService.createBooking(flight1, passenger1, user);
        bookingService.createBooking(flight2, passenger1, user);
        bookingService.createBooking(flight2, passenger2, user);

        Booking booking1 = new Booking(flight1, passenger1, user, 1);
        Booking booking2 = new Booking(flight2, passenger1, user, 2);
        Booking booking3 = new Booking(flight2, passenger2, user, 3);

        ArrayList<Booking> bookings = new ArrayList<>();
        bookings.add(booking1);
        bookings.add(booking2);
        bookings.add(booking3);

        Optional<Booking> foundBookings = bookings.stream()
                .filter(booking -> booking.getBookingId() == (booking3.getBookingId() + 4))
                .findFirst();
        assertEquals(foundBookings, bookingService.findBookingById(booking3.getBookingId() + 4));
    }
}