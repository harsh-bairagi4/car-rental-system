/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author harsh
 */
public class Car {
    
 private int carId;
    private String model;
    private String brand;
    private double pricePerDay;
    private boolean available;

    public Car() {
    }
    public Car(int carId, String model, String brand, double pricePerDay, boolean available){
        this.carId = carId;
        this.model = model;
        this.brand = brand;
        this.pricePerDay = pricePerDay;
        this.available = available;
    }
    public Car(String model, String brand, double pricePerDay, boolean available){
        this.model = model;
        this.brand = brand;
        this.pricePerDay = pricePerDay;
        this.available = available;
    }
    public int getCarId() {
        return carId;
    }
    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }
    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public boolean isAvailable() {
        return available;
    }
    public void setAvailable(boolean available) {
        this.available = available;
    }

}

