package com.twu.biblioteca.config;

public class CONSTANTS {
    public static final int MAIN_MENU_DISPLAY_ALL_BOOKS = 1;
    public static final int MAIN_MENU_CHECKOUT_A_BOOK = 2;
    public static final int MAIN_MENU_RETURN_A_BOOK = 3;
    public static final int MAIN_MENU_QUIT = 4;
    public static final String WELCOME_MESSAGE = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";
    public static final String MENU_OPTIONS = "\n" + MAIN_MENU_DISPLAY_ALL_BOOKS + ". List of Books\n" + MAIN_MENU_CHECKOUT_A_BOOK + ". Checkout a Book\n" + MAIN_MENU_RETURN_A_BOOK + ". Return a Book\n" + MAIN_MENU_QUIT + ". Quit\n\nEnter your choice:";
    public static final String INVALID_OPTION = "Please select a valid option!";
    public static final String SUCCESS_CHECKOUT_MESSAGE = "Thank you! Enjoy the book";
    public static final String FAIL_CHECKOUT_MESSAGE = "Sorry, that book is not available";
    public static final String SUCCESS_RETURN_MESSAGE = "Thank you for returning the book";
    public static final String FAIL_RETURN_MESSAGE = "That is not a valid book to return";
    public static final String BOOK_DETAILS_SEPARATORS = "\t|\t";
    public static final String NEW_LINE = "\n";
    public static final String TAB_SPACE = "\t";
}
