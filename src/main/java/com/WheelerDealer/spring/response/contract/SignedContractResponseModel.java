package com.WheelerDealer.spring.response.contract;

public class SignedContractResponseModel {
    private final boolean successful;
    private final String info;

    public SignedContractResponseModel(boolean successful, String info) {
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
