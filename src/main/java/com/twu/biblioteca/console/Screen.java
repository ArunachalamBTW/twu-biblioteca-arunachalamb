package com.twu.biblioteca.console;

import java.io.PrintStream;

import static com.twu.biblioteca.console.ConsoleConstants.*;

public class Screen {

    private static Screen screen = null;
    private static PrintStream out = null;

    private Screen() {
    }

    public static Screen getInstance() {
        if (out == null) {
            out = System.out;
            screen = new Screen();
        }
        return screen;
    }

    public static void reset() {
        if (out != null) {
            out.close();
            out = null;
            screen = null;
        }
    }

    public void displayWelcomeMessage() {
        out.println(WELCOME_MESSAGE);
    }

    public void displayMenu() {
        out.println(MENU_OPTIONS); // TODO - static imports
    }

    public void notifyUser(String message) {
        out.println(message);
    }

    public void displayMessage(String message) {
        out.println(message);
    }

    public void displayMessageOneLine(String message) {
        out.print(message);
    }
}
