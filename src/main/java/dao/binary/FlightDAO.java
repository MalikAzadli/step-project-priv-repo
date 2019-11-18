package dao.binary;

import dao.DAO;
import model.Flight;
import util.BinaryIO;

import java.io.*;
import java.util.*;

public class FlightDAO implements DAO<Flight> {

    private File file;
    private List<Flight> flights;
    private BinaryIO<Flight> io;

    public FlightDAO() {
        this(new File("./db", "flights.txt"));
    }

    public FlightDAO(File file) {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.file = file;
        this.flights = new ArrayList<>();
        this.io = new BinaryIO<>(file, flights);
    }

    @Override
    public List<Flight> findAll() {
        return flights;
    }

    @Override
    public Optional<Flight> findById(int id) {
        return Optional.ofNullable(flights.get(id));
    }

    @Override
    public boolean create(Flight flight) {
        if (flight == null) throw new IllegalArgumentException("Null flight.");
        if (flights.contains(flight)) return false;
        flights.add(flight);
        return true;
    }

    @Override
    public boolean remove(int id) {
        try {
            return flights.removeIf(flight -> id == flight.getFlightId());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("No such user found.");
        }
    }

    @Override
    public void load() {
        io.load();

    }

    @Override
    public void save() {
        io.save();
    }
}
