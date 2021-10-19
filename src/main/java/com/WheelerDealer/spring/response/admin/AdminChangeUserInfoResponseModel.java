package com.WheelerDealer.spring.response.admin;

public class AdminChangeUserInfoResponseModel {
    private final boolean successful;
    private final String info;

    public AdminChangeUserInfoResponseModel(boolean successful, String info) {
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
