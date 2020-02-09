package com.twu.biblioteca.io;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static com.twu.biblioteca.config.CONSTANTS.MENU_OPTIONS;
import static com.twu.biblioteca.config.CONSTANTS.WELCOME_MESSAGE;
import static org.junit.jupiter.api.Assertions.*;

class ScreenTest {

    private final ByteArrayOutputStream consoleOutContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(consoleOutContent));
    }

    @Test
    public void shouldWriteWelcomeMessageToConsole() {
        Screen.displayWelcomeMessage();
        assertEquals(WELCOME_MESSAGE, consoleOutContent.toString().trim());
    }

    @Test
    void shouldDisplayMenuOfOptionsToConsole() {
        Screen.displayMenu();
        assertEquals(MENU_OPTIONS.trim(), consoleOutContent.toString().trim());
    }
}