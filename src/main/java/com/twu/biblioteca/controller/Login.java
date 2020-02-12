package com.twu.biblioteca.controller;

import com.twu.biblioteca.console.Screen;
import com.twu.biblioteca.domain.User;
import com.twu.biblioteca.services.Notification;

import java.util.List;
import java.util.Optional;

import static com.twu.biblioteca.controller.ControllerConstants.*;

public class Login {

    private List<User> allUsers;
    private Screen screen;
    private User loggedInUser;
    private static Login login;
    private List<Notification> notifiers;

    public Login(List<User> allUsers, List<Notification> notifiers, Screen screen) {
        this.allUsers = allUsers;
        this.screen = screen;
        this.notifiers = notifiers;
        loggedInUser = null;
    }

//    public static Login getInstance(List<User> allUsers, List<Notification> notifiers, Screen screen) {
//        if (login == null) {
//            login = new Login(allUsers, notifiers, screen);
//        }
//        return login;
//    }

//    public static Login getInstance() {
//        return login;
//    }

    public boolean isAnyOneLoggedIn() {
        return loggedInUser != null;
    }

    public void doLogin(String libraryCode, String password) {
        Optional<User> loggedInUser = allUsers.stream().filter(user -> user.isSameCredentials(libraryCode, password)).findFirst();

        if (loggedInUser.isPresent()) {
            this.loggedInUser = loggedInUser.get();
            notifyUser(LOGIN_SUCCESS);
            for (Notification notification : notifiers) {
                notification.loggedIn(loggedInUser.get());
            }
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
            for (Notification notification : notifiers) {
                notification.loggedOut();
            }
        }
    }
}
