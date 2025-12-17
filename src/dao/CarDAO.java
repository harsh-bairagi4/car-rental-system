/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;
import model.Car;
import java.util.List;
import java.sql.*;
/**
 *
 * @author harsh
 */
public interface CarDAO {
    void addCar(Car car)throws SQLException;
    Car getCarById(int id)throws SQLException;
    List<Car> getAllCars()throws SQLException;
    boolean deleteCar(int carId) throws SQLException;
    void updateCarAvailability(int id, boolean available)throws SQLException;
}
