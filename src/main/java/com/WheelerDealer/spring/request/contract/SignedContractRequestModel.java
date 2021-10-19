package com.WheelerDealer.spring.request.contract;

import java.time.LocalDate;
import java.util.UUID;

public class SignedContractRequestModel {
    private final UUID contract_id;
    private final String userId;
    private final String carId;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final double totalPrice;
    private final boolean signed;

    public SignedContractRequestModel(String userId, String carId, LocalDate startDate,
                                      LocalDate endDate, double totalPrice, boolean signed) {
        this.contract_id = UUID.randomUUID();
        this.userId = userId;
        this.carId = carId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalPrice = totalPrice;
        this.signed = signed;
    }

    public UUID getContract_id() {
        return contract_id;
    }

    public String getUserId() {
        return userId;
    }

    public String getCarId() {
        return carId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public boolean isSigned() {
        return signed;
    }
}
