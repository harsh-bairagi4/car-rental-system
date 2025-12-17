/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.impl;
import dao.CustomerDAO;
import model.Customer;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author harsh
 */
public class CustomerDAOImpl implements CustomerDAO{
     @Override
    public void addCustomer(Customer customer)throws SQLException {
        String sql = "insert into customer(name, age, license_number, phone, address) values(?, ?, ?, ?, ?)";
        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, customer.getName());
            ps.setInt(2, customer.getAge());
            ps.setString(3, customer.getLicenseNo());
            ps.setString(4, customer.getPhone());
            ps.setString(5, customer.getAddress());
            ps.executeUpdate();
        }
        catch (SQLException e){
             throw new SQLException(e.getMessage());
        }
    }
    @Override
    public Customer getCustomerById(int id)throws SQLException{
        Customer customer = null;
        String sql = "select * from customer where customer_id = ?";
        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                customer = new Customer(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6));
            }

        }
        catch (SQLException e){
            throw new SQLException(e.getMessage());
        }
        return customer;
    }
    @Override
    public void deleteCustomer(int customerId) throws SQLException{
        String sql = "delete from customer where customer_id = ? ";
        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, customerId);
            ps.executeUpdate();
        }
        catch(SQLException e){
            throw new SQLException(e.getMessage());
        }
    }
    @Override
    public List<Customer> getAllCustomers()throws SQLException{
        List<Customer> al = new ArrayList<>();
        String sql = "select * from customer";
        try(Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql)){
            while(rs.next()){
                al.add(new Customer(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6)));
            }
        }
        catch (SQLException e){
            throw new SQLException(e.getMessage());
        }
        return al;
    }
}
