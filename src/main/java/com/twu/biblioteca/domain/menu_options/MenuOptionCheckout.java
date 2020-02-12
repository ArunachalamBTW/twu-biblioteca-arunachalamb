package com.twu.biblioteca.domain.menu_options;

import com.twu.biblioteca.console.Input;
import com.twu.biblioteca.domain.Library;

import static com.twu.biblioteca.domain.DomainConstants.*;

public class MenuOptionCheckout implements MenuOptions {
    @Override
    public void execute(Library library) {
        screen.notifyUser(GET_BOOK_NAME);
        library.checkoutBook(Input.createInstance().getStringInput());
    }
}
