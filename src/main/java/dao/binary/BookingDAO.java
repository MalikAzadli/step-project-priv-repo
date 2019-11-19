package dao.binary;

import dao.DAO;
import model.Booking;
import util.BinaryIO;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class BookingDAO implements DAO<Booking> {

    private File file;
    private List<Booking> bookings;
    private BinaryIO<Booking> io;

    public BookingDAO(){
        this(new File("./db", "bookings.txt"));
    }

    public BookingDAO(File file) {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.file = file;
        this.bookings = new ArrayList<>();
        this.io = new BinaryIO<>(file, bookings);
    }

    @Override
    public List<Booking> findAll() {
        return bookings;
    }

    @Override
    public Optional<Booking> findById(int id) {
        return bookings.stream().filter(booking -> booking.getBookingId()==id).findFirst();
    }

    @Override
    public boolean create(Booking booking) {
        if (booking == null) throw new IllegalArgumentException("Null booking.");
        if (bookings.contains(booking)) return false;
        bookings.add(booking);
        booking.getFlight().addPassenger(booking.getPassenger());
        return true;
    }

    @Override
    public boolean remove(int id) {
        try{
            Booking chosen = bookings.stream().filter(booking -> booking.getBookingId() == id).findFirst().get();
            chosen.getFlight().removePassenger(chosen.getPassenger());
            boolean removed = bookings.remove(chosen);
            refreshIds();
            return removed;
        } catch (IllegalArgumentException e){
            e.printStackTrace();
            throw new IllegalArgumentException("No such user found.");
        }
    }

    public synchronized void refreshIds(){
        IntStream.range(0, bookings.size()).forEach(i -> bookings.get(i).setBookingId(i+1));
    }

    public void load() {
        io.load();
    }

    public void save() {
        io.save();
    }

}
