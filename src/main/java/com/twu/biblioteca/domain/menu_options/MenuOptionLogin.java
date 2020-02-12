package com.twu.biblioteca.domain.menu_options;

import com.twu.biblioteca.console.Input;
import com.twu.biblioteca.controller.Login;
import com.twu.biblioteca.domain.Library;

import static com.twu.biblioteca.domain.DomainConstants.*;

public class MenuOptionLogin implements MenuOptions {
    private Login login;

    public MenuOptionLogin(Login login) {
        this.login = login;
    }

    @Override
    public void execute(Library library) {
        if (!login.isAnyOneLoggedIn()) {
            screen.displayMessage(GET_USER_CODE);
            String userCode = Input.createInstance().getStringInput();
            screen.displayMessage(GET_PASSWORD);
            String password = Input.createInstance().getStringInput();
            login.doLogin(userCode, password);
        } else {
            login.logout();
        }
    }
}
