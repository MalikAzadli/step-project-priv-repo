package controller;

import model.User;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class UserControllerTest {

    private UserController userController;

    @Before
    public void before(){
        this.userController = new UserController();
        User user1 = new User("malikk",
                "T@ylorGone5",
                "Malik",
                "Ibadov",
                "malikk@gmail.com");
        userController.create(user1);
    }

    @Test
    public void create() {
        User user1 = new User(
                "raminn",
                "R@minismayilov5",
                "Ramin",
                "Ismayilov",
                "raminn@yahoo.com");

        assertTrue(userController.create(user1));
    }

    @Test
    public void isUsername() {
        assertTrue(userController.isUsername("malikk"));
        assertFalse(userController.isUsername("malik"));
    }

    @Test
    public void isEmail() {
        assertTrue(userController.isEmail("malikk@gmail.com"));
        assertFalse(userController.isEmail("malik@mail.com"));
    }

    @Test
    public void isPassword() {
        assertTrue(userController.isPassword("T@ylorGone5"));
        assertFalse(userController.isPassword("T@ylrGone5"));
    }

    @Test
    public void getUser() {
        User user1 = new User(
                "raminn",
                "R@minismayilov5",
                "Ramin",
                "Ismayilov",
                "raminn@yahoo.com");
        userController.create(user1);
        assertEquals(Optional.of(user1), userController.getUser("raminn", "R@minismayilov5"));
        assertEquals(Optional.empty(), userController.getUser("james", "J@maescaa#5"));
    }

    @Test
    public void getPassword() {
        assertEquals("T@ylorGone5", userController.getPassword("malikk"));
        assertEquals("", userController.getPassword("malik"));
    }
}