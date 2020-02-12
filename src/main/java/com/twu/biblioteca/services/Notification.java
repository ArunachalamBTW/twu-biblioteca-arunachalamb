package com.twu.biblioteca.services;

import com.twu.biblioteca.domain.User;

public interface Notification {
    void loggedIn(User user);
    void loggedOut();
}
