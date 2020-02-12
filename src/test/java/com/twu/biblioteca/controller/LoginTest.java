package com.twu.biblioteca.controller;

import com.twu.biblioteca.console.Screen;
import com.twu.biblioteca.domain.Library;
import com.twu.biblioteca.domain.Menu;
import com.twu.biblioteca.domain.User;
import com.twu.biblioteca.services.Notification;
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
import static org.mockito.Mockito.*;

class LoginTest {

    private String hellomd5 = "8B1A9953C4611296A827ABF8C47804D7";
    private ByteArrayOutputStream consoleOutContent;
    private PrintStream printStream;
    private Notification mockedLibrary;
    private Notification mockedMenu;
    private List<Notification> notifiers;

    @BeforeEach
    public void setUpStreams() {
        printStream = System.out;
        consoleOutContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(consoleOutContent));

        mockedLibrary = mock(Library.class);
        mockedMenu = mock(Menu.class);

        notifiers = new ArrayList<>();
        notifiers.add(mockedLibrary);
        notifiers.add(mockedMenu);
    }

    @AfterEach
    public void setOutStreams() {
        System.setOut(printStream);
//        Input.reset();
        Screen.reset();
    }

    @Test
    void shouldReturnFalseIfNoUserLoggedIn() {
        Login login = new Login(getUsers(), notifiers, Screen.getInstance());
        assertFalse(login.isAnyOneLoggedIn());
    }

    @Test
    void shouldLoginARegisteredUser() {
        Login login = new Login(getUsers(), notifiers, Screen.getInstance());

        login.doLogin("123-4567", hellomd5);

        assertEquals(LOGIN_SUCCESS + NEW_LINE, consoleOutContent.toString());
    }

    @Test
    void shouldNotLoginAUnRegisteredUser() {
        Login login = new Login(getUsers(), notifiers, Screen.getInstance());

        login.doLogin("123-45678", hellomd5);

        assertEquals(LOGIN_FAIL + NEW_LINE, consoleOutContent.toString());
    }

    @Test
    void shouldNotLoginAUserWithInvalidCredentials() {
        Login login = new Login(getUsers(), notifiers, Screen.getInstance());

        login.doLogin("123-4567", hellomd5 + "hello");

        assertEquals(LOGIN_FAIL + NEW_LINE, consoleOutContent.toString());
    }

    @Test
    void shouldLogoutWhenUserIsLoggedIn() {
        Login login = new Login(getUsers(), notifiers, Screen.getInstance());
        login.doLogin("123-4567", hellomd5);

        login.logout();

        assertEquals(LOGIN_SUCCESS + NEW_LINE + LOGOUT_SUCCESS + NEW_LINE, consoleOutContent.toString());
    }

    @Test
    void shouldNotLogoutWhenUserIsNotLoggedIn() {
        Login login = new Login(getUsers(), notifiers, Screen.getInstance());
        login.doLogin("123-45678", hellomd5);

        login.logout();

        assertEquals(LOGIN_FAIL + NEW_LINE + LOGOUT_FAIL + NEW_LINE, consoleOutContent.toString());
    }

    @Test
    void shouldNotifyAllIfUserLogInSuccessfully() {
        User user1 = getUsers().get(0);
        User user2 = getUsers().get(1);
        Login login = new Login(new ArrayList<>(Arrays.asList(user1, user2)), notifiers, Screen.getInstance());

        login.doLogin("123-4567", hellomd5);

        verify(mockedLibrary, times(1)).loggedIn(user1);
        verify(mockedMenu, times(1)).loggedIn(user1);
    }

    @Test
    void shouldNotNotifyAllIfUserLogInFailed() {
        User user1 = getUsers().get(0);
        User user2 = getUsers().get(1);
        Login login = new Login(new ArrayList<>(Arrays.asList(user1, user2)), notifiers, Screen.getInstance());

        login.doLogin("123-45678", hellomd5);

        verify(mockedLibrary, times(0)).loggedIn(user1);
        verify(mockedMenu, times(0)).loggedIn(user1);
    }

    @Test
    void shouldNotifyAllIfUserLogoutSuccessfully() {
        User user1 = getUsers().get(0);
        User user2 = getUsers().get(1);
        Login login = new Login(new ArrayList<>(Arrays.asList(user1, user2)), notifiers, Screen.getInstance());

        login.doLogin("123-4567", hellomd5);
        login.logout();

        verify(mockedLibrary, times(1)).loggedIn(user1);
        verify(mockedMenu, times(1)).loggedIn(user1);
        verify(mockedLibrary, times(1)).loggedOut();
        verify(mockedMenu, times(1)).loggedOut();
    }

    @Test
    void shouldNotNotifyAllIfUserLogoutWithoutLogin() {
        User user1 = getUsers().get(0);
        User user2 = getUsers().get(1);
        Login login = new Login(new ArrayList<>(Arrays.asList(user1, user2)), notifiers, Screen.getInstance());

        login.doLogin("123-45678", hellomd5);

        verify(mockedLibrary, times(0)).loggedIn(user1);
        verify(mockedMenu, times(0)).loggedIn(user1);
        verify(mockedLibrary, times(0)).loggedOut();
        verify(mockedMenu, times(0)).loggedOut();
    }

    public List<User> getUsers() {
        User user1 = new User("Arun", "arun@abc.com", "9999999999", "123-4567", hellomd5);
        User user2 = new User("Bala", "bala@abc.com", "9999999999", "012-1234", hellomd5);
        return new ArrayList<>(Arrays.asList(user1, user2));
    }
}