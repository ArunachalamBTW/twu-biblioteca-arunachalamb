package com.twu.biblioteca;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static com.twu.biblioteca.config.CONSTANTS.WELCOME_MESSAGE;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BibliotecaAppTest {

    private final ByteArrayOutputStream consoleOutContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(consoleOutContent));
    }

    @Test
    public void shouldWriteWelcomeMessageToConsoleWhenTheAppIsStart() {
        new BibliotecaApp().start();
        assertEquals(WELCOME_MESSAGE, consoleOutContent.toString().trim());
    }
}