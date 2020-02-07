package com.twu.biblioteca;

import com.twu.biblioteca.config.CONSTANTS;

public class BibliotecaApp {

    public static void main(String[] args) {
        new BibliotecaApp().start();
//        System.out.println(displayWelcomeMessage());
    }

    void start() {
        System.out.println(displayWelcomeMessage());
    }

    public static String displayWelcomeMessage() {
        return CONSTANTS.WELCOME_MESSAGE;
    }

}
