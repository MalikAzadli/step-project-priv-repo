package service;

import model.User;

import java.util.Optional;

public interface UserService {

    boolean create(User user);

    boolean isUsername(String line);

    boolean isEmail(String line);

    boolean isPassword(String line);

    Optional<User> getUser(String username, String password);

    String getPassword(String username);

    void save();

    void load();
}
