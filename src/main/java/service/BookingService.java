package service;

import model.Booking;
import model.Flight;
import model.Passenger;
import model.User;

import java.util.List;
import java.util.Optional;

public interface BookingService {
    List<Booking> findAllBookings();
    List<Booking> findAllBookingsOfPassengerById(int passengerId);
    List<Booking> findAllBookingsOPassengerByName(String name);
    List<Booking> findAllBookingsOfUserById(int userId);
    List<Booking> findAllBookingsOfUserByName(String name);
    Optional<Booking> findBookingById(int bookingId);
    void cancelBooking(int bookingId);
    void createBooking(Flight flight, Passenger passenger, User user);
    void save();
    void load();
}
