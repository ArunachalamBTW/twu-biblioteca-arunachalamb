package com.twu.biblioteca.domain;

import com.twu.biblioteca.console.Screen;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.twu.biblioteca.domain.DomainConstants.*;
import static com.twu.biblioteca.config.GlobalConstants.*;

public class Library {
    private List<Book> allBooks; // TODO - name - BOOKS - Confusing as well. Is this list of all books? List of unchecked books?
    private Screen screen;
    private List<Book> checkedOutBooks; // TODO - name again - probably will use past tense
    private List<Movie> allMovies;

    public Library(List<Book> allBooks, List<Movie> allMovies, Screen screen) {
        this.allBooks = allBooks;
        this.screen = screen;
        this.allMovies = allMovies;
        this.checkedOutBooks = new ArrayList<>();
    }

    public String getAllBooks() { // TODO - what's display? Liar. // TODO - why ALL?
        StringBuilder allBookDetails = new StringBuilder();
        int countOfBooks = 1;
        for (Book book : allBooks) {
            String bookDetails = "";
            if (!checkedOutBooks.contains(book)) {
                bookDetails += countOfBooks++ + PERIOD + SPACE + book.getDetails() + NEW_LINE;
            }
            allBookDetails.append(bookDetails);
        }
        return allBookDetails.toString();
    }

    public String getAllMovies() {
        StringBuilder allMovieDetails = new StringBuilder();
        int countOfMovies = 1;
        for (Movie movie : allMovies) {
            String movieDetails = "";
            movieDetails += countOfMovies++ + PERIOD + SPACE + movie.getDetails() + NEW_LINE;
            allMovieDetails.append(movieDetails);
        }
        return allMovieDetails.toString();
    }

    // TODO - checkout is probably one word
    public void checkout(String bookName) { // TODO - name again. What else will I checkout from library? Libraian?
        // TODO - read about streams, and try to use implicit loops - But don't spend too much time.

        Optional<Book> checkoutBook = allBooks.stream().filter(book -> book.isSameByName(bookName)).filter(book -> !checkedOutBooks.contains(book)).findFirst();

        if (checkoutBook.isPresent()) {
            checkedOutBooks.add(checkoutBook.get());
            notifyUser(SUCCESS_CHECKOUT_MESSAGE);
        } else {
            notifyUser(FAIL_CHECKOUT_MESSAGE);
        }
    }

    public void returnBook(String bookName) {
        Optional<Book> returnBook = checkedOutBooks.stream().filter(book -> book.isSameByName(bookName)).findFirst();

        if (returnBook.isPresent()) {
            checkedOutBooks.remove(returnBook.get());
            notifyUser(SUCCESS_RETURN_MESSAGE);
        } else {
            notifyUser(FAIL_RETURN_MESSAGE);
        }
    }

    public void notifyUser(String message) {
        screen.displayMessage(message);
    } // TODO - using statics. What is the problem? Library is a liar. - Its needs sout to work. Doesn't say so.

}
