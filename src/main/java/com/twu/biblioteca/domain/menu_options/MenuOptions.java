package com.twu.biblioteca.domain.menu_options;

import com.twu.biblioteca.console.Screen;
import com.twu.biblioteca.domain.Library;

public interface MenuOptions {
    Screen screen = Screen.getInstance();
    void execute(Library library);
}
