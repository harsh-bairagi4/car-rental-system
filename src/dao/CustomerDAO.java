/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;
import model.Customer;
import java.util.List;
import java.sql.*;


/**
 *
 * @author harsh
 */
public interface CustomerDAO {
    void addCustomer(Customer customer) throws SQLException;
    Customer getCustomerById(int id) throws SQLException;
    void deleteCustomer(int customerId) throws SQLException;
    List<Customer> getAllCustomers() throws SQLException;
}
