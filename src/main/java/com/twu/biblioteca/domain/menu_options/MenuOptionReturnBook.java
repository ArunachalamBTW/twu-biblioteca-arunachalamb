package com.twu.biblioteca.domain.menu_options;

import com.twu.biblioteca.console.Input;
import com.twu.biblioteca.console.Screen;
import com.twu.biblioteca.domain.Library;

import static com.twu.biblioteca.domain.DomainConstants.*;

public class MenuOptionReturnBook implements MenuOptions {
    @Override
    public void execute(Library library) {
        Screen.getInstance().notifyUser(GET_BOOK_NAME);
        library.returnBook(Input.createInstance().getStringInput());
    }
}
