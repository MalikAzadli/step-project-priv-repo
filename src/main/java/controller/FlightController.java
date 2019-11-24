package controller;

import model.Airport;
import model.Flight;
import service.FlightServiceImpl;

import java.io.File;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class FlightController {

    private final FlightServiceImpl service;

    public FlightController() {
        this(new File("./db", "flights.txt"));
    }

    public FlightController(File file) {
        service = new FlightServiceImpl(file);
    }

    public List<Flight> findAllFlights() {
        return service.findAllFlights();
    }

    public List<Flight> findFlightsMatchedByCriteria(Airport origin,
                                                     Airport destination,
                                                     LocalDate departure,
                                                     int passengers) {

        return service.findFlightsMatchedByCriteria(origin, destination, departure, passengers);
    }

    public List<Flight> findAllWithin(int day) {
        return service.findAllWithin(day);
    }

    public Optional<Flight> findFlightByFlightId(int flightId) { return service.findFlightByFlightId(flightId); }

    public void create(Flight flight) {
        service.create(flight);
    }

    public void remove(int flightId) {
        service.remove(flightId);
    }

    public void refresh(){
        service.refresh();
    }

    public void load() {
        service.load();
    }

    public void save() {
        service.save();
    }

}
