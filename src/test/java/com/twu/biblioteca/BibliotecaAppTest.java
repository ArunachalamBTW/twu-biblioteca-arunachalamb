package com.twu.biblioteca;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static com.twu.biblioteca.config.CONSTANTS.*;
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
    }

    @Test
    public void shouldWriteWelcomeMessageToConsole() {
        new BibliotecaApp().displayWelcomeMessage();
        assertEquals(WELCOME_MESSAGE, consoleOutContent.toString().trim());
    }

    @Test
    void shouldDisplayMenuOfOptionsToConsole() {
        new BibliotecaApp().displayMenu();
        assertEquals(MENU_OPTIONS, consoleOutContent.toString().trim());
    }

    @Test
    void shouldSelectFirstMenuOptionToListBooks() {
        InputStream sysInBackup = System.in; // backup System.in to restore it later
        ByteArrayInputStream input1 = new ByteArrayInputStream("1 2".getBytes());
        System.setIn(input1);
        BibliotecaApp bibliotecaApp = new BibliotecaApp();

        bibliotecaApp.start();

        assertEquals(WELCOME_MESSAGE + "\n" + MENU_OPTIONS + "\n" + defaultBooksListString(), consoleOutContent.toString().trim());
        System.setIn(sysInBackup);
    }

    @Test
    void shouldNotifyWhenInvalidOptionIsSelected() {
        InputStream sysInBackup = System.in;
        ByteArrayInputStream input1 = new ByteArrayInputStream("3 2".getBytes());
        System.setIn(input1);
        BibliotecaApp bibliotecaApp = new BibliotecaApp();

        bibliotecaApp.start();

        assertEquals(WELCOME_MESSAGE + "\n" + MENU_OPTIONS + "\n" + INVALID_OPTION + "\n" + MENU_OPTIONS, consoleOutContent.toString().trim());
        System.setIn(sysInBackup);
    }

    public String defaultBooksListString() {
        return "1. Programming Book 1\t|\t2000\t|\tAuthor Not Found\n" +
                "2. Programming Book 2\t|\t2001\t|\tAuthor Not Found";
    }

}