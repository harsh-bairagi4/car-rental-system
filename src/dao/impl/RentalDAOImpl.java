/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.impl;
import dao.RentalDAO;
import model.Rental;
import util.DBConnection;

import java.sql.*;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author harsh
 */
public class RentalDAOImpl implements RentalDAO {
    @Override
    public void createRental(int carId, int customerId, Date startDate, int days, double totalCost) throws Exception{
        String sql = "insert into rental (car_id, customer_id, start_date, end_date, total_cost, returned)" +
                " values (?,?,?, date_add(?, interval ? day), ?, false)";
        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){
            java.sql.Date sqlDate = new java.sql.Date(startDate.getTime());
            ps.setInt(1, carId);
            ps.setInt(2, customerId);
            ps.setDate(3, sqlDate);
            ps.setDate(4, sqlDate);
            ps.setInt(5, days);
            ps.setDouble(6, totalCost);
            ps.executeUpdate();
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
    @Override
    public Rental getActiveRentalByCarId(int carId)throws Exception{
        Rental rental = null;
        String sql = "select * from rental where car_id = ? and returned = false";
        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){
             ps.setInt(1, carId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    rental = new Rental();
                    rental.setRentalId(rs.getInt("rental_id"));
                    rental.setCarId(rs.getInt("car_id"));
                    rental.setCustomerId(rs.getInt("customer_id"));
                    rental.setStartDate(rs.getDate("start_date"));
                    rental.setEndDate(rs.getDate("end_date"));
                    rental.setTotalCost(rs.getDouble("total_cost"));
                    rental.setReturned(rs.getBoolean("returned"));
                }
            }

        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return rental;
    }
    @Override
    public void markAsReturned(int rentalId, double finalCost) throws Exception{
        String sql = "update rental set returned = true, total_cost = ? where rental_id = ? ";
        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){
            ps.setDouble(1, finalCost);
            ps.setInt(2, rentalId);
            ps.executeUpdate();
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }  
    @Override
     public List<Rental> getAllActiveRentals()throws Exception{
        Rental rental = null;
        List<Rental> al = new ArrayList<>();
        String sql = "select * from rental";
        try(Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql)){
            while(rs.next()){
                rental = new Rental();
                rental.setRentalId(rs.getInt("rental_id"));
                rental.setCarId(rs.getInt("car_id"));
                rental.setCustomerId(rs.getInt("customer_id"));
                rental.setStartDate(rs.getDate("start_date"));
                rental.setEndDate(rs.getDate("end_date"));
                rental.setTotalCost(rs.getDouble("total_cost"));
                rental.setReturned(rs.getBoolean("returned"));
                al.add(rental);
            }
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
        
        return al;
    }
    @Override
    public void deleteRental(int id)throws Exception{
        String sql = "delete from rental where rental_id = ?";
        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, id);
            ps.executeUpdate();
        }
        catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
