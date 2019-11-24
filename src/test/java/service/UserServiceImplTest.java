package service;

import model.User;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class UserServiceImplTest {

    private UserService userService;

    @Before
    public void before() {
        this.userService = new UserServiceImpl();
        User user1 = new User("malikk",
                "T@ylorGone5",
                "Malik",
                "Ibadov",
                "malikk@gmail.com");
        userService.create(user1);
    }

    @Test
    public void create() {
        User user1 = new User("raminn",
                "R@minTS5",
                "Ramin",
                "Ismayilov",
                "ramin@gmail.com");

        assertTrue(userService.create(user1));

    }

    @Test
    public void isUsername1() {
        assertTrue(userService.isUsername("malikk"));
    }

    @Test
    public void isUsername2() {
        assertFalse(userService.isUsername("james"));
    }

    @Test
    public void isEmail1() {
        assertTrue(userService.isEmail("malikk@gmail.com"));
    }

    @Test
    public void isEmail2() {
        assertFalse(userService.isEmail("malik@gmail.com"));
    }

    @Test
    public void isPassword1() {
        assertTrue(userService.isPassword("T@ylorGone5"));
    }

    @Test
    public void isPassword2() {
        assertFalse(userService.isPassword("T@ylorgone5"));
    }

    @Test
    public void getUser1() {
        User user1 = new User("raminn",
                "R@minTS5",
                "Ramin",
                "Ismayilov",
                "ramin@gmail.com");
        userService.create(user1);

        assertEquals(Optional.of(user1), userService.getUser("raminn", "R@minTS5"));
    }

    @Test
    public void getUser2() {
        assertEquals(Optional.empty(), userService.getUser("malik", "T@ylorGone5"));
    }

    @Test
    public void getPassword1() {
        assertEquals("T@ylorGone5", userService.getPassword("malikk"));
    }

    @Test
    public void getPassword2() {
        assertEquals("", userService.getPassword("james"));
    }
}