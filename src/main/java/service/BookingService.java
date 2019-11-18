package service;

import model.Booking;
import model.Flight;
import model.Passenger;
import model.User;

import java.util.List;
import java.util.Optional;

public interface BookingService {
    List<Booking> findAllBookings();
    List<Booking> findAllBookingsOfPassenger(int passengerId);
    List<Booking> findAllBookingsOfUserById(int userId);
    List<Booking> findAllBookingsOfUserByName(String name);
    Optional<Booking> findBookingById(int bookingId);
    void cancelBooking(int bookingId);
    void createBooking(Flight flight, User user, Passenger[] passengers);
    void save();
    void load();
}
