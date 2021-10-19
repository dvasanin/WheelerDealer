package com.WheelerDealer.spring.response.contract;

import java.time.LocalDate;

public class ContractResponseModel {

    private final String contractId;
    private final String userId;
    private final String carId;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final double totalPrice;
    private final boolean signed;
    private final boolean approved;

    public ContractResponseModel(String contractId, String userId, String carId,
                                 LocalDate startDate,
                                 LocalDate endDate, double totalPrice,
                                 boolean signed, boolean approved) {
        this.contractId = contractId;
        this.userId = userId;
        this.carId = carId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalPrice = totalPrice;
        this.signed = signed;
        this.approved = approved;
    }

    public String getContractId() {
        return contractId;
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

    public boolean isApproved() {
        return approved;
    }
}
