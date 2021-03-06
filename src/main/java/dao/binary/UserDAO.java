package dao.binary;

import dao.DAO;
import model.User;
import util.BinaryIO;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class UserDAO implements DAO<User> {
    private File file;
    private List<User> users;
    private BinaryIO<User> io;

    public UserDAO() {
        this(new File("./db", "users.txt"));
    }

    public UserDAO(File file) {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.file = file;
        users = new ArrayList<>();
        this.io = new BinaryIO<>(file, users);
    }

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public Optional<User> findById(int id) {
        return users.stream().filter(user -> user.getId() == id).findFirst();
    }

    @Override
    public boolean create(User user) {
        if (user == null) throw new IllegalArgumentException("Null user.");
        if (getAllUsername().contains(user.getUsername())) return false;
        users.add(user);
        return true;
    }

    public Set<String> getAllUsername() {
        return users.stream().map(user -> user.getUsername()).collect(Collectors.toSet());
    }

    @Override
    public boolean remove(int id) {
        Optional<User> found = users.stream().filter(user -> user.getId() == id).findFirst();
        if(found.isPresent()){
            return users.remove(found.get());
        }

        return false;
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
