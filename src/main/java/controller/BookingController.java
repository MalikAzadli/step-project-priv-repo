package controller;

import model.Booking;
import model.Flight;
import model.Passenger;
import model.User;
import service.BookingServiceImpl;

import java.io.File;
import java.util.List;
import java.util.Optional;

public class BookingController {
    private final BookingServiceImpl service;

    public BookingController() {
        this(new File("./db", "bookings.txt"));
    }

    public BookingController(File file) {
        service = new BookingServiceImpl(file);
    }

    public List<Booking> findAllBookings() {
        return service.findAllBookings();
    }

    public List<Booking> findAllBookingsOfPassenger(int passengerId) {
        return service.findAllBookingsOfPassengerById(passengerId);
    }

    public List<Booking> findAllBookingsOfUserById(int userId) {
        return service.findAllBookingsOfUserById(userId);
    }

    public List<Booking> findAllBookingsOfPassengerByName(String name) {
        return service.findAllBookingsOPassengerByName(name);
    }

    public List<Booking> findAllBookingsOfUserByName(String name) {
        return service.findAllBookingsOfUserByName(name);
    }

    public void createBooking(Flight flight, Passenger passenger, User user){
        service.createBooking(flight, passenger, user);
    }

    public Optional<Booking> findBookingById(int bookingId) {
        return service.findBookingById(bookingId);
    }

    public void cancelBooking(int bookingId) {
        service.cancelBooking(bookingId);
    }

    public void save() {
        service.save();
    }

    public void load() {
        service.load();
    }
}
