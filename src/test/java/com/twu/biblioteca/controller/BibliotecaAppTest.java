package com.twu.biblioteca.controller;

import com.twu.biblioteca.console.Input;
import com.twu.biblioteca.console.Screen;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static com.twu.biblioteca.config.GlobalConstants.*;
import static com.twu.biblioteca.console.ConsoleConstants.*;
import static com.twu.biblioteca.controller.ControllerConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BibliotecaAppTest {

    private ByteArrayOutputStream consoleOutContent;
    private PrintStream printStream;

    @BeforeEach
    public void setUpStreams() {
        printStream = System.out;
        consoleOutContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(consoleOutContent));
    }

    @AfterEach
    public void setOutStreams() {
        System.setOut(printStream);
        Input.reset();
        Screen.reset();
    }

    @Test
    void shouldSelectFirstMenuOptionToListBooks() {
        InputStream sysInBackup = System.in; // backup System.in to restore it later
        String input = "1" + NEW_LINE + "4";
        ByteArrayInputStream input1 = new ByteArrayInputStream(input.getBytes());
        System.setIn(input1);
        BibliotecaApp bibliotecaApp = new BibliotecaApp();

        bibliotecaApp.start();

        assertEquals(WELCOME_MESSAGE + NEW_LINE + MENU_OPTIONS + NEW_LINE + defaultBooksListString() + NEW_LINE + NEW_LINE + MENU_OPTIONS + NEW_LINE, consoleOutContent.toString());
        System.setIn(sysInBackup);
    }

    @Test
    void shouldNotifyWhenInvalidOptionIsSelected() {
        InputStream sysInBackup = System.in;
        String input = "9" + NEW_LINE + "4";
        ByteArrayInputStream input1 = new ByteArrayInputStream(input.getBytes());
        System.setIn(input1);
        BibliotecaApp bibliotecaApp = new BibliotecaApp();

        bibliotecaApp.start();

        assertEquals(WELCOME_MESSAGE + NEW_LINE + MENU_OPTIONS + NEW_LINE + INVALID_OPTION + NEW_LINE + MENU_OPTIONS + NEW_LINE, consoleOutContent.toString());
        System.setIn(sysInBackup);
    }

    public String defaultBooksListString() {
        return "1. Programming Book 1" + BOOK_DETAILS_SEPARATORS + "2000" + BOOK_DETAILS_SEPARATORS + "Martin Fowler\n" +
                "2. Programming Book 2" + BOOK_DETAILS_SEPARATORS + "2001" + BOOK_DETAILS_SEPARATORS + "Martin Fowler";
    }

}