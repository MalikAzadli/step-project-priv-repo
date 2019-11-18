package service;

import dao.binary.FlightDAO;
import model.Airport;
import model.Flight;
import model.Passenger;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class FlightServiceImpl implements FlightService {

    private final FlightDAO flightDAO;
    private List<Flight> currentTable;

    public FlightServiceImpl() {
        flightDAO = new FlightDAO();
        flightDAO.load();
    }

    public FlightServiceImpl(File file) {
        flightDAO = new FlightDAO(file);
    }

    public List<Flight> findAllFlights() {
        currentTable = flightDAO.findAll();
        return currentTable;
    }

    @Override
    public List<Passenger> findPassengersOfFlight(int flightId) {
        return findFlightByFlightId(flightId).map(f -> f.getPassengers()).get();
    }

    public List<Flight> findAllWithin(int day) {
        return flightDAO.findAll().stream()
                .filter(f -> f.getDeparture().isBefore(LocalDateTime.now().plusDays(day)))
                .collect(Collectors.toList());
    }

    @Override
    public List<Flight> findFlightsMatchedByCriteria(Airport origin,
                                                     Airport destination,
                                                     LocalDate departure,
                                                     int passengers) {

        currentTable = flightDAO.findAll()
                .stream()
                .filter(
                        f -> origin.equals(f.getOrigin()) &&
                                destination.equals(f.getDestination()) &&
                                (f.getDeparture().toLocalDate().equals(departure)) &&
                                f.availableSeats() >= passengers
                )
                .collect(Collectors.toList());

        return currentTable;
    }

    @Override
    public void create(Flight flight) {
        flightDAO.create(flight);
    }

    public Optional<Flight> findFlightByFlightNo(String flightNo) {
        return currentTable.stream().filter(f -> flightNo.equals(f.getFlightNo())).findFirst();
    }

    @Override
    public Optional<Flight> findFlightByFlightId(int flightId) {
        return currentTable.stream().filter(flight -> flight.getFlightId() == flightId).findFirst();
    }

    @Override
    public void remove(int flightId) {
        flightDAO.remove(flightId);
    }

    public void refresh() {
        currentTable = findAllFlights();
    }

    @Override
    public void load() {
        flightDAO.load();
        findAllFlights();
    }

    @Override
    public void save() {
        flightDAO.save();
    }
}
