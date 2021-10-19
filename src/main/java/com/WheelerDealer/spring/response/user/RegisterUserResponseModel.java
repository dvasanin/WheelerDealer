package com.WheelerDealer.spring.response.user;

public class RegisterUserResponseModel {
    private final boolean successful;
    private final String message;

    public RegisterUserResponseModel(boolean successful, String message) {
        this.successful = successful;
        this.message = message;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public String getMessage() {
        return message;
    }
}