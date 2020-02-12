package com.twu.biblioteca.controller;

import com.twu.biblioteca.console.Screen;
import com.twu.biblioteca.domain.*;
import com.twu.biblioteca.services.Notification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// TODO - think about - SRP, OCP for this class. List it down.
public class BibliotecaApp {

    private Library library;
    private Menu menu;

    public BibliotecaApp() {
        initializeLibrary();
    }

    public static void main(String[] args) {
        new BibliotecaApp().start();
    }

    void start() {
        Screen.getInstance().displayWelcomeMessage();
//        Use Menu implementation here - TODO what does that mean? Try doing it and show it again.
        menu = new Menu(library, Screen.getInstance());

        Login login = new Login(getDefaultUsers(), getDefaultNotifiers(), Screen.getInstance());

        menu.mainMenu(login);
    }

    private void initializeLibrary() {
        // TODO - why not inline?
        library = new Library(getDefaultBooks(), getDefaultMovies(), Screen.getInstance());
    }

    private List<Book> getDefaultBooks() {
        Book book1 = new Book("Programming Book 1", 2000, "Martin Fowler");
        Book book2 = new Book("Programming Book 2", 2001, "Martin Fowler");
        List<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);
        return books;
    }

    private List<Movie> getDefaultMovies() {
        Movie movie1 = new Movie("Interstellar", 2020, "Christopher Nolan", 10);
        Movie movie2 = new Movie("2.0", 2019, "Shankar", 10);
        List<Movie> movies = new ArrayList<>();
        movies.add(movie1);
        movies.add(movie2);
        return movies;
    }

    private List<User> getDefaultUsers() {
        User user1 = new User("Arun", "arun@abc.com", "9999999999", "123-4567", "hello");
        User user2 = new User("Bala", "bala@abc.com", "9999999999", "012-1234", "hello");
        return new ArrayList<>(Arrays.asList(user1, user2));
    }

    private List<Notification> getDefaultNotifiers() {
        return new ArrayList<>(Arrays.asList(library, menu));
    }

}
