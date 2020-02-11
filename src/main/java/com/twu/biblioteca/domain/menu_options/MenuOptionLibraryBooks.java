package com.twu.biblioteca.domain.menu_options;

import com.twu.biblioteca.console.Screen;
import com.twu.biblioteca.domain.Library;

public class MenuOptionLibraryBooks implements MenuOptions {
    @Override
    public void execute(Library library) {
        Screen.getInstance().displayMessage(library.getBooks());
    }
}
