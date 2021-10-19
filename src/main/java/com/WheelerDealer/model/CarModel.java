package com.WheelerDealer.model;

import java.util.UUID;

public class CarModel {
    private UUID car_id;
    private String license_plate;
    private String make;
    private String model;
    private int year;
    private int engine_capacity;
    private String color;
    private double price;
    private int doors;
    private String size;
    private int power;
    private boolean automatic;
    private String fuel;
    private String image;

    public CarModel(UUID car_id, String license_plate,
                    String make, String model, int year,
                    int engine_capacity, String color,
                    double price, int doors, String size, int power, boolean automatic,
                    String fuel, String image) {

        this.car_id = car_id;
        this.license_plate = license_plate;
        this.make = make;
        this.model = model;
        this.year = year;
        this.engine_capacity = engine_capacity;
        this.color = color;
        this.price = price;
        this.doors = doors;
        this.size = size;
        this.power = power;
        this.automatic = automatic;
        this.fuel = fuel;
        this.image = image;

    }

    public UUID getCar_id() {
        return car_id;
    }

    public void setCar_id(UUID car_id) {
        this.car_id = car_id;
    }

    public String getLicense_plate() {
        return license_plate;
    }

    public void setLicense_plate(String license_plate) {
        this.license_plate = license_plate;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getEngine_capacity() {
        return engine_capacity;
    }

    public void setEngine_capacity(int engine_capacity) {
        this.engine_capacity = engine_capacity;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getDoors() {
        return doors;
    }

    public void setDoors(int doors) {
        this.doors = doors;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public boolean isAutomatic() {
        return automatic;
    }

    public void setAutomatic(boolean automatic) {
        this.automatic = automatic;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Car: " + make + ", Model: " + model + ", Year: " + year + ", Engine capacity: " + engine_capacity
                + ", Color: " + color + ", Price: " + price + ", Doors: " + doors + ", Size: " + size +
                ", Power: " + power + ", Shift: " + automatic + ", Fuel: " + fuel;

    }
}
