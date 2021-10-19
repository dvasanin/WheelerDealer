package com.WheelerDealer.spring.misc;

import java.util.UUID;

public class RegisterUserModel {
    private final UUID id;
    private final String username;
    private final String email;
    private final String password;
    private final boolean admin;

    public RegisterUserModel(String username, String email, String password) {
        this.id = UUID.randomUUID();
        this.username = username;
        this.email = email;
        this.password = password;
        this.admin = false;
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAdmin() {
        return admin;
    }
}