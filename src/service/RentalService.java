/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.CarDAO;
import dao.CustomerDAO;
import dao.RentalDAO;
import dao.impl.CarDAOImpl;
import dao.impl.CustomerDAOImpl;
import dao.impl.RentalDAOImpl;
import model.Car;
import model.Customer;
import model.Rental;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.sql.*;
/**
 *
 * @author harsh
 */
public class RentalService {
    
 private CarDAO carDAO = new CarDAOImpl();
    private CustomerDAO customerDAO = new CustomerDAOImpl();
    private RentalDAO rentalDAO = new RentalDAOImpl();

    private final int MINIMUM_AGE = 18;
    private final double LATE_FINE_PER_DAY = 100;

    public String rentCar(int carId, int customerId, int days) throws SQLException,Exception{
        try{
            if (days <= 0) return "Days must be greater than 0.";
           Car car = carDAO.getCarById(carId);
        if(car == null){
            return "Error: Car not found.";
           
        }
        if(!car.isAvailable()){
            return "Car is currently not available.";
        }

        Customer customer = customerDAO.getCustomerById(customerId);
        if(customer == null){
            return "Error: Customer not found. Please register the customer first.";
        }
        if(customer.getAge() < MINIMUM_AGE){
            return "Customer must be at least " + MINIMUM_AGE + " years old to rent a car.";
        }
        if (customer.getLicenseNo() == null || customer.getLicenseNo().trim().isEmpty()) {
            return "Customer must have a valid driving license number to rent a car.";
            
        }
        double totalCost = days * car.getPricePerDay();
        rentalDAO.createRental(carId, customerId, new Date(), days, totalCost);
        carDAO.updateCarAvailability(carId, false);
        return "Car rented successfully! Expected cost: ₹" + totalCost + "\nRental period: " + days + " day(s).";
         
        }
        catch(SQLException e){
            throw new SQLException(e.getMessage() + "lund");
        }
        catch(Exception e){
            throw new Exception(e.getMessage());
        }
        
    }

    public String returnCar(int carId)throws SQLException,Exception{
        try{
            Rental rental = rentalDAO.getActiveRentalByCarId(carId);
        if(rental == null){
            return "No active rental found for car id " + carId + ".";
        }
        Date today = new Date();
        Date expectedEnd = rental.getEndDate();

        long diffMillis = today.getTime() - expectedEnd.getTime();
        long extraDays = 0;
        if(diffMillis > 0){
            long daysLate = TimeUnit.DAYS.convert(diffMillis, TimeUnit.MILLISECONDS);
            if (diffMillis % (24 * 60 * 60 * 1000) != 0) daysLate++;
            extraDays = daysLate;
        }

        double fine = 0;
        if(extraDays > 0){
            fine = extraDays * LATE_FINE_PER_DAY;
        }

        double finalCost = rental.getTotalCost() + fine;

        rentalDAO.markAsReturned(rental.getRentalId(), finalCost);
        carDAO.updateCarAvailability(carId, true);

        if (extraDays > 0) {
            return """
                   Car returned successfully.
                   Late return by """ + extraDays + " day(s). Fine: ₹" + fine + "\nFinal total charged: ₹" + finalCost;
        } 
            return """
                   Car returned successfully on time. No fine.
                   Final total charged: \u20b9""" + finalCost;
  
        }
        catch(SQLException e){
            throw new SQLException(e.getMessage());
        }
        catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }
}

