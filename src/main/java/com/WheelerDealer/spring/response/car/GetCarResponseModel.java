package com.WheelerDealer.spring.response.car;

public class GetCarResponseModel {

    private final String carId;
    private final String licencePlate;
    private final String make;
    private final String model;
    private final String color;
    private final String size;
    private final String fuel;
    private final String image;
    private final int year;
    private final int engineCapacity;
    private final int doors;
    private final int power;
    private final double price;
    private final boolean automatic;

    public GetCarResponseModel(String carId, String licencePlate, String make, String model,
                               int year, int engineCapacity, String color, double price, int doors,
                               String size, int power, boolean automatic, String fuel, String image) {
        this.carId = carId;
        this.licencePlate = licencePlate;
        this.make = make;
        this.model = model;
        this.color = color;
        this.size = size;
        this.fuel = fuel;
        this.image = image;
        this.year = year;
        this.engineCapacity = engineCapacity;
        this.doors = doors;
        this.power = power;
        this.price = price;
        this.automatic = automatic;
    }

    public String getCarId() {
        return carId;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public String getSize() {
        return size;
    }

    public String getFuel() {
        return fuel;
    }

    public String getImage() {
        return image;
    }

    public int getYear() {
        return year;
    }

    public int getEngineCapacity() {
        return engineCapacity;
    }

    public int getDoors() {
        return doors;
    }

    public int getPower() {
        return power;
    }

    public double getPrice() {
        return price;
    }

    public boolean isAutomatic() {
        return automatic;
    }
}