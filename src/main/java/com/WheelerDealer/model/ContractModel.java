package com.WheelerDealer.model;

import java.time.LocalDate;
import java.util.UUID;

public class ContractModel {
    private UUID contract_id;
    private UUID user_id;
    private UUID car_id;
    private LocalDate start_date;
    private LocalDate end_date;
    private double total_price;
    private boolean signed;
    private boolean approved;

    public ContractModel(UUID contract_id, UUID user_id, UUID car_id, LocalDate start_date, LocalDate end_date,
                         double total_price, boolean signed, boolean approved) {

        this.contract_id = contract_id;
        this.user_id = user_id;
        this.car_id = car_id;
        this.start_date = start_date;
        this.end_date = end_date;
        this.total_price = total_price;
        this.signed = signed;
        this.approved = approved;

    }

    public UUID getContract_id() {
        return contract_id;
    }

    public void setContract_id(UUID contract_id) {
        this.contract_id = contract_id;
    }

    public UUID getUser_id() {
        return user_id;
    }

    public void setUser_id(UUID user_id) {
        this.user_id = user_id;
    }

    public UUID getCar_id() {
        return car_id;
    }

    public void setCar_id(UUID car_id) {
        this.car_id = car_id;
    }

    public LocalDate getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }

    public LocalDate getEnd_date() {
        return end_date;
    }

    public void setEnd_date(LocalDate end_date) {
        this.end_date = end_date;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public boolean isSigned() {
        return signed;
    }

    public void setSigned(boolean signed) {
        this.signed = signed;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    @Override
    public String toString() {
        return "ContractID: " + contract_id + "\n" + "UserID: " + user_id + "\n" + "CarID: " + car_id + "\n" +
                "From: " + start_date + ", To: " + end_date + "\n" +
                "Total price: " + total_price + "(currency) \n" +
                "Signed: " + (signed ? "✓" : "-") + ", Approved: " + (approved ? "✓" : "-") + " \n";
    }
}