package com.twu.biblioteca;

import java.util.Scanner;

public class UserInput {

    static UserInput userInput;
    Scanner scanner;

    private UserInput() {
        scanner = new Scanner(System.in);
    }

    public static UserInput getInputInstance() {
        if (userInput == null) {
            userInput = new UserInput();
        }
        return userInput;
    }

    public int getIntegerInput() {
        return scanner.nextInt();
    }

}
