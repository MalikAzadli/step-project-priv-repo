package dao.binary;

import model.User;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

import static org.junit.Assert.*;

public class UserDAOTest {

    private UserDAO userDAO;

    @Before
    public void before() {
        this.userDAO = new UserDAO();
    }

    @Test
    public void findAll() {
        User user1 = new User("malikk",
                "T@ylorGone5",
                "Malik",
                "Ibadov",
                "malikk@gmail.com");
        User user2 = new User(
                "raminn",
                "R@minismayilov5",
                "Ramin",
                "Ismayilov",
                "raminn@yahoo.com");

        userDAO.create(user1);
        userDAO.create(user2);

        ArrayList<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);

        assertEquals(users, userDAO.findAll());

    }

    @Test
    public void findById1() {
        User user1 = new User("malikk",
                "T@ylorGone5",
                "Malik",
                "Ibadov",
                "malikk@gmail.com");
        User user2 = new User(
                "raminn",
                "R@minismayilov5",
                "Ramin",
                "Ismayilov",
                "raminn@yahoo.com");

        userDAO.create(user1);
        userDAO.create(user2);

        assertEquals(user1, userDAO.findById(user1.getId()).get());
    }

    @Test
    public void findById2() {
        User user1 = new User("malikk",
                "T@ylorGone5",
                "Malik",
                "Ibadov",
                "malikk@gmail.com");
        User user2 = new User(
                "raminn",
                "R@minismayilov5",
                "Ramin",
                "Ismayilov",
                "raminn@yahoo.com");

        userDAO.create(user1);
        userDAO.create(user2);

        assertEquals(Optional.empty(), userDAO.findById(user2.getId()+1));
    }

    @Test
    public void create1() {
        User user1 = new User("malikk",
                "T@ylorGone5",
                "Malik",
                "Ibadov",
                "malikk@gmail.com");

        assertTrue(userDAO.create(user1));
    }

    @Test
    public void create2() {
        User user1 = new User("malikk",
                "T@ylorGone5",
                "Malik",
                "Ibadov",
                "malikk@gmail.com");

        userDAO.create(user1);
        assertFalse(userDAO.create(user1));
    }

    @Test
    public void remove1() {
        User user1 = new User("malikk",
                "T@ylorGone5",
                "Malik",
                "Ibadov",
                "malikk@gmail.com");

        userDAO.create(user1);
        assertTrue(userDAO.remove(user1.getId()));
    }

    @Test
    public void remove2() {
        User user1 = new User("malikk",
                "T@ylorGone5",
                "Malik",
                "Ibadov",
                "malikk@gmail.com");

        userDAO.create(user1);
        assertFalse(userDAO.remove(user1.getId()+1));
    }

    @Test
    public void getAllUsername() {
        User user1 = new User("malikk",
                "T@ylorGone5",
                "Malik",
                "Ibadov",
                "malikk@gmail.com");
        User user2 = new User(
                "raminn",
                "R@minismayilov5",
                "Ramin",
                "Ismayilov",
                "raminn@yahoo.com");

        userDAO.create(user1);
        userDAO.create(user2);

        HashSet<String> username = new HashSet<>();
        username.add(user1.getUsername());
        username.add(user2.getUsername());

        assertEquals(username, userDAO.getAllUsername());
    }
}