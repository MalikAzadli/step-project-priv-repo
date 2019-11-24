package dao.binary;

import static org.junit.Assert.*;

import model.Airport;
import model.Booking;
import model.Flight;
import model.Passenger;
import org.junit.Before;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

public class BookingDAOTest {

    private BookingDAO bookingDAO;

    @Before
    public void before() {
        this.bookingDAO = new BookingDAO();
    }

    @org.junit.Test
    public void findAll() {
        Flight flight = new Flight("AB2345",
                100,
                LocalDateTime.now(),
                Airport.FRANKFURT,
                LocalDateTime.now().plusHours(5),
                Airport.LONDON);
        Passenger passenger1 = new Passenger("james", "kulkin");
        Passenger passenger2 = new Passenger("michael", "cane");
        Booking booking1 = new Booking(flight, passenger1, 1);
        Booking booking2 = new Booking(flight, passenger2, 2);
        bookingDAO.create(booking1);
        bookingDAO.create(booking2);

        ArrayList<Booking> bookings = new ArrayList<>();
        bookings.add(booking1);
        bookings.add(booking2);

        assertEquals(bookings, bookingDAO.findAll());
    }

    @org.junit.Test
    public void findById1() {
        Flight flight = new Flight("AB2345",
                100,
                LocalDateTime.now(),
                Airport.FRANKFURT,
                LocalDateTime.now().plusHours(5),
                Airport.LONDON);
        Passenger passenger1 = new Passenger("james", "kulkin");
        Passenger passenger2 = new Passenger("michael", "cane");
        Booking booking1 = new Booking(flight, passenger1, 1);
        Booking booking2 = new Booking(flight, passenger2, 2);
        bookingDAO.create(booking1);
        bookingDAO.create(booking2);

        ArrayList<Booking> bookings = new ArrayList<>();
        bookings.add(booking1);
        bookings.add(booking2);

        Optional<Booking> found = bookings.stream().filter(booking -> booking.getBookingId() == 2).findFirst();
        assertEquals(found, bookingDAO.findById(2));
    }

    @org.junit.Test
    public void findById2() {
        Flight flight = new Flight("AB2345",
                100,
                LocalDateTime.now(),
                Airport.FRANKFURT,
                LocalDateTime.now().plusHours(5),
                Airport.LONDON);
        Passenger passenger1 = new Passenger("james", "kulkin");
        Passenger passenger2 = new Passenger("michael", "cane");
        Booking booking1 = new Booking(flight, passenger1, 1);
        Booking booking2 = new Booking(flight, passenger2, 2);
        bookingDAO.create(booking1);
        bookingDAO.create(booking2);

        ArrayList<Booking> bookings = new ArrayList<>();
        bookings.add(booking1);
        bookings.add(booking2);

        Optional<Booking> found = bookings.stream().filter(booking -> booking.getBookingId() == 4).findFirst();
        assertEquals(found, bookingDAO.findById(4));
    }

    @org.junit.Test
    public void create1() {
        Flight flight = new Flight("AB2345",
                100,
                LocalDateTime.now(),
                Airport.FRANKFURT,
                LocalDateTime.now().plusHours(5),
                Airport.LONDON);
        Passenger passenger1 = new Passenger("james", "kulkin");
        Booking booking1 = new Booking(flight, passenger1, 1);

        assertTrue(bookingDAO.create(booking1));
    }

    @org.junit.Test
    public void create2() {
        Flight flight = new Flight("AB2345",
                100,
                LocalDateTime.now(),
                Airport.FRANKFURT,
                LocalDateTime.now().plusHours(5),
                Airport.LONDON);
        Passenger passenger1 = new Passenger("james", "kulkin");
        Booking booking1 = new Booking(flight, passenger1, 1);
        Booking booking2 = new Booking(flight, passenger1, 1);

        bookingDAO.create(booking1);
        assertFalse(bookingDAO.create(booking2));
    }

    @org.junit.Test
    public void remove1() {
        Flight flight = new Flight("AB2345",
                100,
                LocalDateTime.now(),
                Airport.FRANKFURT,
                LocalDateTime.now().plusHours(5),
                Airport.LONDON);
        Passenger passenger1 = new Passenger("james", "kulkin");
        Booking booking1 = new Booking(flight, passenger1, 1);
        bookingDAO.create(booking1);

        assertTrue(bookingDAO.remove(1));
    }

    @org.junit.Test
    public void remove2() {
        Flight flight = new Flight("AB2345",
                100,
                LocalDateTime.now(),
                Airport.FRANKFURT,
                LocalDateTime.now().plusHours(5),
                Airport.LONDON);
        Passenger passenger1 = new Passenger("james", "kulkin");
        Booking booking1 = new Booking(flight, passenger1, 1);
        bookingDAO.create(booking1);

        assertFalse(bookingDAO.remove(2));
    }
}