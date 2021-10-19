package com.WheelerDealer.model;

import java.util.UUID;

public class UserModel {
    private final UUID id;
    private final String username;
    private final String email;
    private final String password;
    private final boolean admin;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String personalNumber;
    private String image;

    public UserModel(String username, String email, String password) {
        this.id = UUID.randomUUID();
        this.username = username;
        this.email = email;
        this.password = password;
        this.admin = false;
    }

    public UserModel(UUID id, String username, String email, String password, String firstName, String lastName, String phoneNumber, String personalNumber, String image, boolean admin) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.personalNumber = personalNumber;
        this.image = image;
        this.admin = admin;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPersonalNumber() {
        return personalNumber;
    }

    public String getImage() {
        return image;
    }

    public boolean isAdmin() {
        return admin;
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
}
