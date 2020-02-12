package com.twu.biblioteca.controller;

import com.twu.biblioteca.domain.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LoginTest {

    private String hellomd5 = "8B1A9953C4611296A827ABF8C47804D7";

    @Test
    void shouldReturnFalseIfNoUserLoggedIn() {
        Login login = new Login(getUsers());
        assertFalse(login.isAnyOneLoggedIn());
    }

    public List<User> getUsers() {
        User user1 = new User("Arun", "arun@abc.com", "9999999999", "123-4567", hellomd5);
        User user2 = new User("Bala", "bala@abc.com", "9999999999", "012-1234", hellomd5);
        return new ArrayList<>(Arrays.asList(user1, user2));
    }

}