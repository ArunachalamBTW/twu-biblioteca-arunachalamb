package com.twu.biblioteca.console;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static com.twu.biblioteca.config.GlobalConstants.NEW_LINE;
import static com.twu.biblioteca.console.ConsoleConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ScreenTest {

    private final ByteArrayOutputStream consoleOutContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(consoleOutContent));
    }

    @AfterEach
    public void tearDown() {
        Screen.reset();
    }

    @Test
    public void shouldWriteWelcomeMessageToConsole() {
        Screen.getInstance().displayWelcomeMessage();
        assertEquals(WELCOME_MESSAGE + NEW_LINE, consoleOutContent.toString());
    }

    @Test
    void shouldDisplayMenuOfOptionsToConsole() {
        Screen.getInstance().displayMenu();
        assertEquals(MENU_OPTIONS.trim(), consoleOutContent.toString().trim());
    }
}