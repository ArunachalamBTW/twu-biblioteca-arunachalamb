package com.twu.biblioteca;

import org.junit.Test;

import static org.junit.Assert.*;

public class BibliotecaAppTest {

    @Test
    public void shouldReturnWelcomeMessage() {
        assertEquals(CONSTANTS.WELCOME_MESSAGE, BibliotecaApp.displayWelcomeMessage());
    }



}