package com.WheelerDealer.spring.response.user;

public class GetUserResponseModel {

    private final String username;
    private final String email;
    private final String firstName;
    private final String lastName;
    private final String phoneNumber;
    private final String personalNumber;
    private final String image;

    public GetUserResponseModel(String username, String email, String firstName,
                                String lastName, String phoneNumber, String personalNumber, String image) {
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.personalNumber = personalNumber;
        this.image = image;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
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
}
