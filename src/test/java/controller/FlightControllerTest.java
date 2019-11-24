package controller;

import model.Airport;
import model.Flight;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class FlightControllerTest {

    private FlightController flightController;

    @Before
    public void before() {
        this.flightController = new FlightController();
    }

    @Test
    public void findAllFlights() {
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
        flightController.create(flight1);
        flightController.create(flight2);

        ArrayList<Flight> flights = new ArrayList<>(Arrays.asList(flight1, flight2));
        assertEquals(flights, flightController.findAllFlights());
    }

    @Test
    public void findFlightsMatchedByCriteria() {
        Flight flight1 = new Flight("AB2345",
                100,
                LocalDateTime.now().plusDays(3),
                Airport.FRANKFURT,
                LocalDateTime.now().plusDays(1).plusHours(5),
                Airport.LONDON);
        Flight flight2 = new Flight("TS1256",
                120,
                LocalDateTime.now().plusDays(1),
                Airport.BAKU,
                LocalDateTime.now().plusDays(1).plusHours(5),
                Airport.NEWYORK);
        flightController.create(flight1);
        flightController.create(flight2);
        ArrayList<Flight> expected = new ArrayList<>(Arrays.asList(flight1));
        List<Flight> actual = flightController.findFlightsMatchedByCriteria(Airport.FRANKFURT, Airport.LONDON, LocalDate.now().plusDays(3), 5);
        assertEquals(expected, actual);
    }

    @Test
    public void findAllWithin() {
        Flight flight1;
        Flight flight2;
        Flight flight3;
        {
            flight1 = new Flight("AB2345",
                    100,
                    LocalDateTime.now().plusHours(10),
                    Airport.FRANKFURT,
                    LocalDateTime.now().plusHours(17),
                    Airport.LONDON);
            flight2 = new Flight("EC2389",
                    130,
                    LocalDateTime.now().plusDays(1),
                    Airport.KYIV,
                    LocalDateTime.now().plusDays(1).plusHours(6),
                    Airport.MOSCOW);
            flight3 = new Flight("TS1265",
                    140,
                    LocalDateTime.now().plusDays(3),
                    Airport.LONDON,
                    LocalDateTime.now().plusDays(3).plusHours(3),
                    Airport.NEWYORK);
        }
        flightController.create(flight1);
        flightController.create(flight2);
        flightController.create(flight3);

        ArrayList<Flight> flights = new ArrayList<>(Arrays.asList(flight1, flight2));

        assertEquals(flights, flightController.findAllWithin(2));
    }

    @Test
    public void findFlightByFlightId() {
        Flight flight1;
        Flight flight2;
        {
            flight1 = new Flight("AB2345",
                    100,
                    LocalDateTime.now().plusHours(10),
                    Airport.FRANKFURT,
                    LocalDateTime.now().plusHours(17),
                    Airport.LONDON);
            flight2 = new Flight("EC2389",
                    130,
                    LocalDateTime.now().plusDays(1),
                    Airport.KYIV,
                    LocalDateTime.now().plusDays(1).plusHours(6),
                    Airport.MOSCOW);
        }

        flightController.create(flight1);
        flightController.create(flight2);

        assertEquals(Optional.of(flight1), flightController.findFlightByFlightId(flight1.getFlightId()));
        assertEquals(Optional.empty(), flightController.findFlightByFlightId(flight2.getFlightId()+1));
    }
}