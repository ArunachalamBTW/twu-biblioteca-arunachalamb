package com.twu.biblioteca.domain;

import static com.twu.biblioteca.config.GlobalConstants.NEW_LINE;

public class User {
    private String name;
    private String email;
    private String phoneNumber;
    private String libraryCode;
    private String password;

    public User(String name, String email, String phoneNumber, String libraryCode, String password) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.libraryCode = libraryCode;
        this.password = password;
    }

    public boolean isSamePassword(String password) {
        return this.password.equalsIgnoreCase(password);
    }

    public String getDetails() {
        return "Name: " + name + NEW_LINE + "Email: " + email + NEW_LINE + "Phone Number: " + phoneNumber;
    }

    public boolean isSameCredentials(String libraryCode, String password) {
        return this.libraryCode.equals(libraryCode) && this.password.equals(password);
    }

    public String getName() {
        return name;
    }
}
