package com.twu.biblioteca;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static com.twu.biblioteca.config.CONSTANTS.MENU_OPTIONS;
import static com.twu.biblioteca.config.CONSTANTS.WELCOME_MESSAGE;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BibliotecaAppTest {

    private final ByteArrayOutputStream consoleOutContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(consoleOutContent));
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
        ByteArrayInputStream in = new ByteArrayInputStream("1".getBytes());

        System.setIn(in);

        BibliotecaApp bibliotecaApp = new BibliotecaApp();
        bibliotecaApp.start();

        assertEquals(WELCOME_MESSAGE + "\n" + MENU_OPTIONS+"\n"+defaultBooksListString(), consoleOutContent.toString().trim());
        System.setIn(sysInBackup);
    }

    public String defaultBooksListString() {
        return "1. Programming Book 1\t|\t2000\t|\tAuthor Not Found\n" +
                "2. Programming Book 2\t|\t2001\t|\tAuthor Not Found";
    }

}