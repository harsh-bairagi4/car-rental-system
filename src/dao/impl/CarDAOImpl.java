/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.impl;

import dao.CarDAO;
import model.Car;
import util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author harsh
 */
public class CarDAOImpl implements CarDAO{
      @Override
    public void addCar(Car car)throws SQLException{
        String sql = "insert into car (model, brand, price_per_day, available) values (?, ?, ?, ?)";
        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, car.getModel());
            ps.setString(2, car.getBrand());
            ps.setDouble(3, car.getPricePerDay());
            ps.setBoolean(4, car.isAvailable());
            ps.executeUpdate();
        }
        catch (SQLException e){
            throw new SQLException(e.getMessage());
        }
        
    }
    @Override
    public Car getCarById(int id)throws SQLException{
        Car car = null;
        String sql = "select * from car where car_id = ?";
        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                car = new Car(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getBoolean(5));
            }
        }
        catch (SQLException e){
             throw new SQLException(e.getMessage());
        }
        return car;
    }
    @Override
    public List<Car> getAllCars()throws SQLException{
        List<Car> al = new ArrayList<>();
        String sql = "select * from car";
        try(Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql)){
            while(rs.next()){
                al.add(new Car(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getBoolean(5)));
            }
        }
        catch (SQLException e){
            throw new SQLException(e.getMessage());
        }
        return al;
    }
    @Override
    public void updateCarAvailability(int id, boolean available)throws SQLException{
        String sql = "update car set available = ? where car_id = ? ";
        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){
            ps.setBoolean(1, available);
            ps.setInt(2, id);
            ps.executeUpdate();
        }
        catch (SQLException e){
             throw new SQLException(e.getMessage());
        }
    }
    @Override
    public boolean deleteCar(int carId) throws SQLException {
    String sql = "delete from car where car_id = ? and available = true";
    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, carId);
        int x = ps.executeUpdate();
        
        if(x != 0){
            return true;
        }
        return false;
    }
    catch(SQLException e){
        throw new SQLException(e.getMessage());
    }
}

}
