package com.twu.biblioteca.domain;

import org.junit.jupiter.api.Test;

import static com.twu.biblioteca.config.GlobalConstants.NEW_LINE;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private String hellomd5 = "8B1A9953C4611296A827ABF8C47804D7";

    @Test
    void shouldReturnTrueIfPasswordsMatch() {
        User user = getUser();
        assertTrue(user.isSamePassword(hellomd5));
    }

    @Test
    void shouldReturnFalseIfPasswordsDoesNotMatch() {
        User user = getUser();
        assertFalse(user.isSamePassword(hellomd5 + "hello"));
    }

    @Test
    void shouldReturnLoggedInUserDetails() {
        User user = getUser();
        assertEquals(getUserDetails(), user.getDetails());
    }

    public User getUser() {
        return new User("Arun", "arun@abc.com", "9999999999", "123-4567", hellomd5);
    }

    public String getUserDetails() {
        return "Name: Arun" + NEW_LINE + "Email: arun@abc.com" + NEW_LINE + "Phone Number: 9999999999";
    }

}