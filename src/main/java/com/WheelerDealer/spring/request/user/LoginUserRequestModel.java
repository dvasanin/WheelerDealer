package com.WheelerDealer.spring.request.user;

public class LoginUserRequestModel {
    private final String identification;
    private final String password;

    public LoginUserRequestModel(String identification, String password) {
        this.identification = identification;
        this.password = password;
    }

    public String getIdentification() {
        return identification;
    }

    public String getPassword() {
        return password;
    }
}
