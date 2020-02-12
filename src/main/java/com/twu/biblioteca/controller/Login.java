package com.twu.biblioteca.controller;

import com.twu.biblioteca.console.Screen;
import com.twu.biblioteca.domain.User;

import java.util.List;
import java.util.Optional;

import static com.twu.biblioteca.controller.ControllerConstants.*;

public class Login {

    private List<User> allUsers;
    private Screen screen;
    private User loggedInUser;

    public Login(List<User> allUsers, Screen screen) {
        this.allUsers = allUsers;
        this.screen = screen;
        loggedInUser = null;
    }

    public boolean isAnyOneLoggedIn() {
        return loggedInUser != null;
    }

    public void login(String libraryCode, String password) {
        Optional<User> loggedInUser = allUsers.stream().filter(user -> user.isSameCredentials(libraryCode, password)).findFirst();

        if (loggedInUser.isPresent()) {
            this.loggedInUser = loggedInUser.get();
            notifyUser(LOGIN_SUCCESS);
        } else {
            notifyUser(LOGIN_FAIL);
        }
    }

    private void notifyUser(String message) {
        screen.displayMessage(message);
    }

    public void logout() {
        if (loggedInUser == null) {
            notifyUser(LOGOUT_FAIL);
        } else {
            loggedInUser = null;
            notifyUser(LOGOUT_SUCCESS);
        }
    }
}
