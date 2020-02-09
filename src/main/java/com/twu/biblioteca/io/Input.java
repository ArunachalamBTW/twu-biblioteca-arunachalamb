package com.twu.biblioteca.io;

import java.util.Scanner;

public class Input {
    private static Input input;
    private static Scanner scanner;

    private Input() {}

    public static Input createInstance() {
        if (scanner == null) {
            input = new Input();
            scanner = new Scanner(System.in);
        }
        return input;
    }

    public static void reset() {
        scanner.close();
        scanner = null;
    }

    public int getIntegerInput() {
        return scanner.nextInt();
    }

    public String getStringInput() {
        return scanner.nextLine();
    }
}
