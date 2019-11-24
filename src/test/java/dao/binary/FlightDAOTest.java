package dao.binary;

import model.Airport;
import model.Flight;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.Assert.*;

public class FlightDAOTest {

    private FlightDAO flightDAO;

    @Before
    public void before() {
        this.flightDAO = new FlightDAO();
    }

    @Test
    public void findAll() {
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

        flightDAO.create(flight1);
        flightDAO.create(flight2);

        ArrayList<Flight> flights = new ArrayList<>();
        flights.add(flight1);
        flights.add(flight2);

        assertEquals(flights, flightDAO.findAll());
    }

    @Test
    public void findById1() {
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

        flightDAO.create(flight1);
        flightDAO.create(flight2);

        assertEquals(Optional.of(flight1), flightDAO.findById(flight1.getFlightId()));
    }

    @Test
    public void findById2() {
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

        flightDAO.create(flight1);
        flightDAO.create(flight2);

        assertEquals(Optional.empty(), flightDAO.findById(3));
    }

    @Test
    public void create1() {
        Flight flight1 = new Flight("AB2345",
                100,
                LocalDateTime.now(),
                Airport.FRANKFURT,
                LocalDateTime.now().plusHours(5),
                Airport.LONDON);

        assertTrue(flightDAO.create(flight1));

    }

    @Test
    public void create2() {
        Flight flight1 = new Flight("AB2345",
                100,
                LocalDateTime.now(),
                Airport.FRANKFURT,
                LocalDateTime.now().plusHours(5),
                Airport.LONDON);
        flightDAO.create(flight1);
        assertFalse(flightDAO.create(flight1));
    }

    @Test
    public void remove1() {
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

        flightDAO.create(flight1);
        flightDAO.create(flight2);

        assertTrue(flightDAO.remove(flight1.getFlightId()));
    }

    @Test
    public void remove2() {
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

        flightDAO.create(flight1);
        flightDAO.create(flight2);

        assertFalse(flightDAO.remove(3));
    }
}