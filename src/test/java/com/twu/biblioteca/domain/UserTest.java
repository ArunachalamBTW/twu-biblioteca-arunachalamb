package com.twu.biblioteca.domain;

import org.junit.jupiter.api.Test;

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

    public User getUser() {
        return new User("Arun", "arun@abc.com", Double.parseDouble("9999999999"), "123-4567", hellomd5);
    }

}