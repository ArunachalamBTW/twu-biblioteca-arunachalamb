package com.twu.biblioteca;

public class BibliotecaApp {

    public static void main(String[] args) {
        System.out.println(displayWelcomeMessage());
    }

    public static String displayWelcomeMessage() {
        return CONSTANTS.WELCOME_MESSAGE;
    }

}
