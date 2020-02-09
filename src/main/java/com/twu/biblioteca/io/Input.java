package com.twu.biblioteca.io;

import java.util.Scanner;

public class Input {
    private static Input input;
    private static Scanner scanner;

    private Input() {
    }

    public static Input createInstance() {
        input = new Input();
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }
        return input;
    }

    public static void reset() {
        scanner.close();
        scanner = null;
        input = null;
    }

    public int getIntegerInput() {
        return Integer.parseInt(scanner.nextLine());
    }

    public String getStringInput() {
        return scanner.nextLine();
    }
}
