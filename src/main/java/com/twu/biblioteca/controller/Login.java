package com.twu.biblioteca.controller;

import com.twu.biblioteca.domain.User;

import java.util.List;

public class Login {

    private List<User> allUsers;
    private User loggedInUser;

    public Login(List<User> allUsers) {
        this.allUsers = allUsers;
        loggedInUser = null;
    }


    public boolean isAnyOneLoggedIn() {
        return loggedInUser != null;
    }
}
