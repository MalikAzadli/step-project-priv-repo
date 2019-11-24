package service;

import dao.binary.BookingDAO;
import model.Booking;
import model.Flight;
import model.Passenger;
import model.User;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BookingServiceImpl implements BookingService {
    private final BookingDAO bookingDAO;

    public BookingServiceImpl() {
        bookingDAO = new BookingDAO();
    }

    public BookingServiceImpl(File file) {
        bookingDAO = new BookingDAO(file);
    }

    @Override
    public List<Booking> findAllBookings() {
        return bookingDAO.findAll();
    }

    @Override
    public List<Booking> findAllBookingsOfPassengerById(int passengerId) {
        return bookingDAO.findAll().stream()
                .filter(b -> b.getPassenger().getId() == passengerId)
                .collect(Collectors.toList());
    }

    @Override
    public List<Booking> findAllBookingsOfUserById(int userId) {
        return bookingDAO.findAll().stream()
                .filter(b -> b.getUser().getId() == userId)
                .collect(Collectors.toList());
    }

    @Override
    public List<Booking> findAllBookingsOPassengerByName(String name) {
        try {
            return bookingDAO.findAll().stream()
                    .filter(b -> (name).equalsIgnoreCase(b.getPassenger().getFullName()))
                    .collect(Collectors.toList());
        } catch (NullPointerException ex) {
            return new ArrayList<>();
        }
    }

    @Override
    public List<Booking> findAllBookingsOfUserByName(String name) {
        List<Booking> collect = bookingDAO.findAll().stream()
                .filter(b -> (name).equalsIgnoreCase(b.getUser().getFullName()))
                .collect(Collectors.toList());

        if (collect.isEmpty()) return new ArrayList<>();
        return collect;
    }

    @Override
    public Optional<Booking> findBookingById(int bookingId) {
        return bookingDAO.findById(bookingId);
    }

    @Override
    public void cancelBooking(int bookingId) {
        bookingDAO.remove(bookingId);
    }

    @Override
    public void createBooking(Flight flight, Passenger passenger, User user) {
        int id = bookingDAO.findAll().size();
        bookingDAO.create(new Booking(flight, passenger, user, id + 1));
    }

    @Override
    public void save() {
        bookingDAO.save();
    }

    @Override
    public void load() {
        bookingDAO.load();
    }
}
