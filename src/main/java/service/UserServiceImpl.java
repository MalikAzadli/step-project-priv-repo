package service;

import dao.binary.BookingDAO;
import dao.binary.FlightDAO;
import dao.binary.UserDAO;
import model.User;

import java.io.File;
import java.util.stream.Collectors;

public class UserServiceImpl {
    private final UserDAO userDAO;

    public UserServiceImpl() {
        userDAO = new UserDAO();
        userDAO.load();
    }

    public UserServiceImpl(File file) {
        userDAO = new UserDAO(file);
    }

    public boolean create(User user) {
        return userDAO.create(user);
    }

    public boolean isUsername(String line) {
        return userDAO.getAllUsernames().contains(line);
    }

    public boolean isEmail(String line) {
        return !userDAO.findAll().stream()
                .filter(user -> user.getEmail().equalsIgnoreCase(line))
                .collect(Collectors.toList())
                .isEmpty();
    }

    public boolean isPassword(String line) {
        return !userDAO.findAll().stream()
                .filter(user -> user.getPassword().equals(line))
                .collect(Collectors.toList())
                .isEmpty();
    }

    public User getUser(String username, String password) {
        return userDAO.findAll().stream()
                .filter(user -> user.getPassword().equals(password))
                .filter(user -> user.getUsername().equalsIgnoreCase(username))
                .collect(Collectors.toList())
                .get(0);
    }

    public void load() {
        userDAO.load();
    }

    public void save(){
        userDAO.save();
    }
}
