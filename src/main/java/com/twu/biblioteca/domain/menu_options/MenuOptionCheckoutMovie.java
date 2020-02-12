package com.twu.biblioteca.domain.menu_options;

import com.twu.biblioteca.console.Input;
import com.twu.biblioteca.domain.Library;

import static com.twu.biblioteca.domain.DomainConstants.*;

public class MenuOptionCheckoutMovie implements MenuOptions {

    @Override
    public void execute(Library library) {
        screen.notifyUser(GET_MOVIE_NAME);
        library.checkoutMovie(Input.createInstance().getStringInput());
    }
}
