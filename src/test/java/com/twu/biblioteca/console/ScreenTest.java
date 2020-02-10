package com.twu.biblioteca.console;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static com.twu.biblioteca.console.ConsoleConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

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