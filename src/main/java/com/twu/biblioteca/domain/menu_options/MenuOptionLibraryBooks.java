package com.twu.biblioteca.domain.menu_options;

import com.twu.biblioteca.domain.Library;

public class MenuOptionLibraryBooks implements MenuOptions {
    @Override
    public void execute(Library library) {
        screen.displayMessage(library.getBooks());
    }
}
