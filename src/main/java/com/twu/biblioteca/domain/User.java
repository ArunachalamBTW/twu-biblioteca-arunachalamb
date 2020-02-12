package com.twu.biblioteca.domain;

public class User {
    private String name;
    private String email;
    private double phoneNumber;
    private String libraryCode;
    private String password;

    public User(String name, String email, double phoneNumber, String libraryCode, String password) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.libraryCode = libraryCode;
        this.password = password;
    }


    public boolean isSamePassword(String password) {
        return this.password.equalsIgnoreCase(password);
    }
}
