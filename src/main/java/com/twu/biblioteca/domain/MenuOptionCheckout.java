package com.twu.biblioteca.domain;

import com.twu.biblioteca.console.Input;
import com.twu.biblioteca.console.Screen;

import static com.twu.biblioteca.domain.DomainConstants.*;

public class MenuOptionCheckout implements MenuOptions {
    @Override
    public void execute(Library library) {
        Screen.getInstance().notifyUser(GET_BOOK_NAME);
        library.checkout(Input.createInstance().getStringInput());
    }
}
