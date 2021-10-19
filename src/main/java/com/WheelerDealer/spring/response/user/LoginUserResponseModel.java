package com.WheelerDealer.spring.response.user;

public class LoginUserResponseModel {
    private final boolean successful;
    private final String info;

    public LoginUserResponseModel(boolean successful, String info) {
        this.successful = successful;
        this.info = info;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public String getInfo() {
        return info;
    }
}
