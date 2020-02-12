package com.twu.biblioteca.controller;

import com.twu.biblioteca.console.Input;
import com.twu.biblioteca.console.Screen;
import com.twu.biblioteca.domain.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.twu.biblioteca.config.GlobalConstants.*;
import static com.twu.biblioteca.controller.ControllerConstants.*;
import static org.junit.jupiter.api.Assertions.*;

class LoginTest {

    private String hellomd5 = "8B1A9953C4611296A827ABF8C47804D7";
    private ByteArrayOutputStream consoleOutContent;
    private PrintStream printStream;

    @BeforeEach
    public void setUpStreams() {
        printStream = System.out;
        consoleOutContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(consoleOutContent));
    }

    @AfterEach
    public void setOutStreams() {
        System.setOut(printStream);
//        Input.reset();
        Screen.reset();
    }

    @Test
    void shouldReturnFalseIfNoUserLoggedIn() {
        Login login = new Login(getUsers(), Screen.getInstance());
        assertFalse(login.isAnyOneLoggedIn());
    }

    @Test
    void shouldLoginARegisteredUser() {
        Login login = new Login(getUsers(), Screen.getInstance());

        login.login("123-4567", hellomd5);

        assertEquals(LOGIN_SUCCESS + NEW_LINE, consoleOutContent.toString());
    }

    @Test
    void shouldNotLoginAUnRegisteredUser() {
        Login login = new Login(getUsers(), Screen.getInstance());

        login.login("123-45678", hellomd5);

        assertEquals(LOGIN_FAIL + NEW_LINE, consoleOutContent.toString());
    }

    public List<User> getUsers() {
        User user1 = new User("Arun", "arun@abc.com", "9999999999", "123-4567", hellomd5);
        User user2 = new User("Bala", "bala@abc.com", "9999999999", "012-1234", hellomd5);
        return new ArrayList<>(Arrays.asList(user1, user2));
    }

}