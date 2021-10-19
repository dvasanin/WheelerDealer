package com.WheelerDealer.spring.misc;

public class SearchCarModel {
    private final String make;
    private final String model;
    private final Integer year;
    private final Integer power;
    private final Integer doors;
    private final Double price;
    private final Boolean automatic;

    public SearchCarModel(String make, String model, Integer year, Integer power,
                          Integer doors, Double price, Boolean automatic) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.power = power;
        this.doors = doors;
        this.price = price;
        this.automatic = automatic;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public Integer getYear() {
        return year;
    }

    public Integer getPower() {
        return power;
    }

    public Integer getDoors() {
        return doors;
    }

    public Double getPrice() {
        return price;
    }

    public Boolean getAutomatic() {
        return automatic;
    }
}
