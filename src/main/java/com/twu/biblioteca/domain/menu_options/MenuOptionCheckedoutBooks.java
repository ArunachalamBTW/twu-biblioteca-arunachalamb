package com.twu.biblioteca.domain.menu_options;

import com.twu.biblioteca.domain.Library;

public class MenuOptionCheckedoutBooks implements MenuOptions {
    @Override
    public void execute(Library library) {
        library.showCheckedoutBooks();
    }
}
