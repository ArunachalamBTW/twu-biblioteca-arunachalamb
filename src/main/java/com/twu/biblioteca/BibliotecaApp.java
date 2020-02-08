package com.twu.biblioteca;

import com.twu.biblioteca.config.CONSTANTS;

public class BibliotecaApp {

    public static void main(String[] args) {
        new BibliotecaApp().start();
    }

    void start() {
        displayWelcomeMessage();
    }

    private static void displayWelcomeMessage() {
        System.out.println(CONSTANTS.WELCOME_MESSAGE);
    }

}
