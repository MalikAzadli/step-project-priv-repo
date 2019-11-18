package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Connection {

    private List<Flight> connections = new ArrayList<>();

    public Connection add(Flight flight) {
        if (connections.isEmpty()) {
            connections.add(flight);
        } else {
            Flight lastFlight = connections.get(connections.size() - 1);
            if (flight.getOrigin().equals(lastFlight.getDestination()) &&
                    flight.getDeparture().isAfter(lastFlight.getArrival())) {
                connections.add(flight);
            }
        }

        return this;
    }

    @Override
    public String toString() {
        return connections.stream()
                .map(Flight::toString)
                .collect(Collectors.joining(" ---> "));
    }
}
