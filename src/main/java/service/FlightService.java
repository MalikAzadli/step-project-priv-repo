package service;

import model.Airport;
import model.Flight;
import model.Passenger;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface FlightService {
    List<Flight> findAllFlights();
    Optional<Flight> findFlightByFlightId(int flightId);
    List<Passenger> findPassengersOfFlight(int flightId);
    List<Flight> findAllWithin(int day);
    List<Flight> findFlightsMatchedByCriteria(Airport origin,
                                              Airport destination,
                                              LocalDate departure,
                                              int passengers);
    void create(Flight flight);
    void remove(int flightId);
    void save();
    void load();
}
