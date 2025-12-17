/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;
import dao.impl.CustomerDAOImpl;
import model.Customer;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.sql.*;

/**
 *
 * @author harsh
 */

public class CustomerFrame extends JFrame {

    private JTextField txtName, txtAge, txtLicense, txtPhone, txtAddress;
    private JTable table;

    public CustomerFrame() {
        setTitle("Customer Management");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel form = new JPanel(new GridLayout(6, 2, 10, 10));
        form.setBorder(BorderFactory.createTitledBorder("Manage Customers"));

        txtName = new JTextField();
        txtAge = new JTextField();
        txtLicense = new JTextField();
        txtPhone = new JTextField();
        txtAddress = new JTextField();

        form.add(new JLabel("Name:")); form.add(txtName);
        form.add(new JLabel("Age:")); form.add(txtAge);
        form.add(new JLabel("License No:")); form.add(txtLicense);
        form.add(new JLabel("Phone:")); form.add(txtPhone);
        form.add(new JLabel("Address:")); form.add(txtAddress);

        JButton btnAdd = new JButton("Add Customer");
        JButton btnDelete = new JButton("Delete Customer");
        
        btnAdd.addActionListener(e -> addCustomer());
        btnDelete.addActionListener(e -> deleteCustomer());
        
        form.add(btnAdd);
        form.add(btnDelete);

        // Table for viewing customers
        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Customer List"));

        setLayout(new BorderLayout(10, 10));
        add(form, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        refreshCustomerTable();
        setVisible(true);
    }

    private void addCustomer() {
        try {
            Customer c = new Customer();
            if(txtName.getText().length() == 0){
                JOptionPane.showMessageDialog(this, "Please enter the name.");
                return;
            }
            if(txtAge.getText().length() == 0){
                JOptionPane.showMessageDialog(this, "Please enter the age.");
                return;
            }
            if(txtLicense.getText().length() == 0){
                JOptionPane.showMessageDialog(this, "Please enter the license number.");
                return;
            }
            if(txtPhone.getText().length() == 0){
                JOptionPane.showMessageDialog(this, "Please enter the phone number.");
                return;
            }
            if(txtAddress.getText().length() == 0){
                JOptionPane.showMessageDialog(this, "Please enter the address.");
                return;
            }
            String name = capatilize(txtName.getText());
            String address = capatilize(txtAddress.getText());
            
            c.setName(name);
            c.setAge(Integer.parseInt(txtAge.getText()));
            c.setLicenseNo(txtLicense.getText());
            c.setPhone(txtPhone.getText());
            c.setAddress(address);

            if (c.getAge() < 18) {
                JOptionPane.showMessageDialog(this, "Customer must be 18 or older.");
                return;
            }
            if (c.getAge() > 90) {
                JOptionPane.showMessageDialog(this, "Car can't be rented to person having this age");
                return;
            }
            if(c.getLicenseNo().length() > 17){
                 JOptionPane.showMessageDialog(this, "Invalid License Number");
                return;
            }
            if (c.getPhone().length() < 10 || c.getPhone().length() > 10) {
                JOptionPane.showMessageDialog(this, "Invalid Phone Number");
                return;
            }
            

            new CustomerDAOImpl().addCustomer(c);
            JOptionPane.showMessageDialog(this, "Customer added successfully!");
            refreshCustomerTable();
            txtName.setText("");
            txtAge.setText("");
            txtLicense.setText("");
            txtPhone.setText(""); 
            txtAddress.setText("");
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric values for age.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Unexpected Error: " + ex.getMessage());
        }
        
    }
    
     private void deleteCustomer(){
        int selectedRow = table.getSelectedRow();
        if(selectedRow == -1){
             JOptionPane.showMessageDialog(this, "Please select a customer to delete.");
            return;
        }
         int customerId = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
          int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete Customer ID: " + customerId + "?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                new CustomerDAOImpl().deleteCustomer(customerId);
                JOptionPane.showMessageDialog(this, "Customer deleted successfully!");
                refreshCustomerTable();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        }
    }
   
    private String capatilize(String input){
        if (input == null || input.isEmpty()) {
            return input; 
        }
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }

    private void refreshCustomerTable() {
        try {
            List<Customer> customers = new CustomerDAOImpl().getAllCustomers();
            String[] cols = {"ID", "Name", "Age", "License", "Phone", "Address"};
            String[][] data = new String[customers.size()][6];
            for (int i = 0; i < customers.size(); i++) {
                Customer c = customers.get(i);
                data[i][0] = String.valueOf(c.getCustomerId());
                data[i][1] = c.getName();
                data[i][2] = String.valueOf(c.getAge());
                data[i][3] = c.getLicenseNo();
                data[i][4] = c.getPhone();
                data[i][5] = c.getAddress();
            }
            table.setModel(new javax.swing.table.DefaultTableModel(data, cols));
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage());
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Could not load customers: " + e.getMessage());
        }
    }
}

