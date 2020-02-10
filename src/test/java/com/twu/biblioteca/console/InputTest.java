package com.twu.biblioteca.console;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class InputTest {

    @AfterEach
    public void reset() {
        Input.reset();
    }

    @Test
    void shouldGetAnIntegerValueOne() {
        int magicNumber = 1;
        InputStream sysInBackup = System.in;
        ByteArrayInputStream input1 = new ByteArrayInputStream(String.valueOf(magicNumber).getBytes());
        System.setIn(input1);
        int number = Input.createInstance().getIntegerInput();

        assertEquals(number, magicNumber);
        System.setIn(sysInBackup);
    }

    @Test
    void shouldGetAStringValue() {
        String magicString = "Hello";
        InputStream sysInBackup = System.in;
        ByteArrayInputStream input1 = new ByteArrayInputStream(magicString.getBytes());
        System.setIn(input1);
        String hello = Input.createInstance().getStringInput();

        assertEquals(hello, magicString);
        System.setIn(sysInBackup);
    }
}