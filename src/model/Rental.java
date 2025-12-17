/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.util.Date;
/**
 *
 * @author harsh
 */

public class Rental {
    private int rentalId;
    private int carId;
    private int customerId;
    private Date startDate;
    private Date endDate;
    private double totalCost;
    private boolean returned;

    public Rental() {}

    public int getRentalId() {
        return rentalId;
    }
    public void setRentalId(int rentalId) {
        this.rentalId = rentalId;
    }

    public int getCarId() {
        return carId;
    }
    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getCustomerId() {
        return customerId;
    }
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public double getTotalCost() {
        return totalCost;
    }
    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public boolean isReturned() {
        return returned;
    }
    public void setReturned(boolean returned) {
        this.returned = returned;
    }
}
