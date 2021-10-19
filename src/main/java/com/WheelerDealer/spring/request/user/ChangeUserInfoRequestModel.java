package com.WheelerDealer.spring.request.user;

public class ChangeUserInfoRequestModel {
    private final String username;
    private final String password;
    private final String newPassword;
    private final String firstName;
    private final String lastName;
    private final String phoneNumber;
    private final String image;

    public ChangeUserInfoRequestModel(String username, String password, String newPassword,
                                      String firstName, String lastName, String phoneNumber, String image) {
        this.username = username;
        this.password = password;
        this.newPassword = newPassword;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.image = image;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getNewPassword() {
        return newPassword;
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

    public String getImage() {
        return image;
    }
}
