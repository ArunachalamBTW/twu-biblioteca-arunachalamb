package com.twu.biblioteca.domain;

import com.twu.biblioteca.console.Screen;

public class MenuOptionLibraryBooks implements MenuOptions {
    @Override
    public void execute(Library library) {
        Screen.getInstance().displayMessage(library.getBooks());
    }
}
