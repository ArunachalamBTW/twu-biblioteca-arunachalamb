package com.twu.biblioteca.io;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class InputTest {

    private final ByteArrayOutputStream consoleOutContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(consoleOutContent));
    }

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
//        int number = new Input().getIntegerInput();
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
//        String hello = new Input().getStringInput();
        String hello = Input.createInstance().getStringInput();

        assertEquals(hello, magicString);
        System.setIn(sysInBackup);
    }
}