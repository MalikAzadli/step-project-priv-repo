package service;

import model.Airport;
import model.Flight;
import model.Passenger;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class FlightServiceImplTest {

    private FlightService flightService;

    @Before
    public void before() {
        this.flightService = new FlightServiceImpl();
    }

    @Test
    public void findAllFlights() {
        Flight flight1 = new Flight("AB2345",
                100,
                LocalDateTime.now(),
                Airport.FRANKFURT,
                LocalDateTime.now().plusHours(5),
                Airport.LONDON);
        Flight flight2 = new Flight("EC2389",
                130,
                LocalDateTime.now(),
                Airport.KYIV,
                LocalDateTime.now().plusHours(1),
                Airport.MOSCOW);

        flightService.create(flight1);
        flightService.create(flight2);

        ArrayList<Flight> flights = new ArrayList<>();
        flights.add(flight1);
        flights.add(flight2);

        assertEquals(flights, flightService.findAllFlights());
    }

    @Test
    public void findPassengersOfFlight1() {
        Flight flight1 = new Flight("AB2345",
                100,
                LocalDateTime.now(),
                Airport.FRANKFURT,
                LocalDateTime.now().plusHours(5),
                Airport.LONDON);
        Flight flight2 = new Flight("EC2389",
                130,
                LocalDateTime.now(),
                Airport.KYIV,
                LocalDateTime.now().plusHours(1),
                Airport.MOSCOW);

        Passenger passenger1 = new Passenger("Leonard", "Cohen");
        Passenger passenger2 = new Passenger("David", "Bowie");

        flight1.addPassenger(passenger1);
        flight2.addPassenger(passenger2);

        flightService.create(flight1);
        flightService.create(flight2);

        ArrayList<Flight> flights = new ArrayList<>();
        flights.add(flight1);
        flights.add(flight2);

        List<Passenger> found = flights.stream()
                .filter(flight -> flight.getFlightId() == flight1.getFlightId())
                .findFirst()
                .get()
                .getPassengers();

        assertEquals(found, flightService.findPassengersOfFlight(flight1.getFlightId()));
    }

    @Test
    public void findPassengersOfFlight2() {
        Flight flight1 = new Flight("AB2345",
                100,
                LocalDateTime.now(),
                Airport.FRANKFURT,
                LocalDateTime.now().plusHours(5),
                Airport.LONDON);
        Flight flight2 = new Flight("EC2389",
                130,
                LocalDateTime.now(),
                Airport.KYIV,
                LocalDateTime.now().plusHours(1),
                Airport.MOSCOW);

        Passenger passenger1 = new Passenger("Leonard", "Cohen");
        Passenger passenger2 = new Passenger("David", "Bowie");

        flight1.addPassenger(passenger1);
        flight2.addPassenger(passenger2);

        flightService.create(flight1);
        flightService.create(flight2);

        assertEquals(new ArrayList<Passenger>(), flightService.findPassengersOfFlight(flight2.getFlightId() + 3));
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
        flightService.create(flight1);
        flightService.create(flight2);
        flightService.create(flight3);

        ArrayList<Flight> flights = new ArrayList<>(Arrays.asList(flight1, flight2));

        assertEquals(flights, flightService.findAllWithin(2));
    }

    @Test
    public void findFlightsMatchedByCriteria() {
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
                    Airport.FRANKFURT,
                    LocalDateTime.now().plusDays(3).plusHours(3),
                    Airport.LONDON);
        }

        flightService.create(flight1);
        flightService.create(flight2);
        flightService.create(flight3);

        ArrayList<Flight> expected = new ArrayList<>(Arrays.asList(flight3));
        List<Flight> actual = flightService.findFlightsMatchedByCriteria(Airport.FRANKFURT,
                Airport.LONDON,
                LocalDate.now().plusDays(3),
                3);
        assertEquals(expected, actual);
    }

    @Test
    public void findFlightByFlightId1() {
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
                    Airport.FRANKFURT,
                    LocalDateTime.now().plusDays(3).plusHours(3),
                    Airport.LONDON);
        }

        flightService.create(flight1);
        flightService.create(flight2);
        flightService.create(flight3);

        assertEquals(Optional.of(flight1), flightService.findFlightByFlightId(flight1.getFlightId()));
    }


    @Test
    public void findFlightByFlightId2() {
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
                    Airport.FRANKFURT,
                    LocalDateTime.now().plusDays(3).plusHours(3),
                    Airport.LONDON);
        }

        flightService.create(flight1);
        flightService.create(flight2);
        flightService.create(flight3);

        assertEquals(Optional.empty(), flightService.findFlightByFlightId(flight3.getFlightId()+5));
    }
}