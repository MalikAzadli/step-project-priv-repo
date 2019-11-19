package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

public class Flight implements Serializable {

    private static final long serialVersionUID = 1L;
    static int counter = 1;

    private Integer flightId;
    private String flightNo;
    private int capacity;
    private LocalDateTime departure;
    private Airline airline;
    private Airport origin;
    private LocalDateTime arrival;
    private Airport destination;
    private List<Passenger> passengers = new ArrayList<>();

    public Flight(String flightNo, int capacity) {
        this.flightId = counter++;
        this.flightNo = flightNo;
        this.capacity = capacity;
    }

    public Flight(
                  String flightNo,
                  int capacity,
                  LocalDateTime departure,
                  Airport origin,
                  LocalDateTime arrival,
                  Airport destination) {

        this.flightId = counter++;
        this.flightNo = flightNo;
        this.capacity = capacity;
        this.departure = departure;
        this.origin = origin;
        this.arrival = arrival;
        this.destination = destination;
    }

    public Flight(String flightNo,
                  Airline airline,
                  int capacity,
                  Airport origin,
                  LocalDateTime departure,
                  Airport destination,
                  LocalDateTime arrival) {
        this.flightId = counter++;
        this.flightNo = flightNo;
        this.airline = airline;
        this.capacity = capacity;
        this.origin = origin;
        this.departure = departure;
        this.destination = destination;
        this.arrival = arrival;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public Airport getOrigin() {
        return origin;
    }

    public void setOrigin(Airport origin) {
        this.origin = origin;
    }

    public Airport getDestination() {
        return destination;
    }

    public void setDestination(Airport destination) {
        this.destination = destination;
    }

    public LocalDateTime getDeparture() {
        return departure;
    }

    public void setDeparture(LocalDateTime departure) {
        this.departure = departure;
    }

    public LocalDateTime getArrival() {
        return arrival;
    }

    public void setArrival(LocalDateTime arrival) {
        this.arrival = arrival;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void addPassenger(Passenger passenger) {
        if(!isFull()) this.passengers.add(passenger);
    }

    public void removePassenger(Passenger passenger) {
        this.passengers.remove(passenger);
    }

    public String getFlightNo() {
        return flightNo;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public int availableSeats() {
        return capacity - passengers.size();
    }

    public boolean isFull(){
        return capacity == passengers.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return flightId == flight.flightId &&
                Objects.equals(flightNo, flight.flightNo) &&
                Objects.equals(departure, flight.departure) &&
                Objects.equals(origin, flight.origin) &&
                Objects.equals(arrival, flight.arrival) &&
                Objects.equals(destination, flight.destination);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flightId, flightNo, departure, origin, arrival, destination);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Formatter fmt = new Formatter(sb);

        return fmt.format(
                "%s %-3s %s %-5s %s %-20s %s %-12s %s %-15s %s %-12s %s %-15s %s %-3s",
                "|", flightId,
                "|", flightNo,
                "|", airline,
                "|", origin,
                "|", departure.toString().replace("T", " "),
                "|", destination,
                "|", arrival.toString().replace("T", " "),
                "|", (availableSeats()),
                "|"
        ).toString();
    }
}
