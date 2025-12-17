/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;
import dao.impl.CarDAOImpl;
import model.Car;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.List;
/**
 *
 * @author harsh
 */
public class CarFrame extends JFrame {
    private JTextField txtModel, txtBrand, txtPricePerDay;
    private JCheckBox chkAvailable;
    private JTable table;

    public CarFrame() {
        setTitle("Car Management");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel form = new JPanel(new GridLayout(6, 2, 10, 10));
        form.setBorder(BorderFactory.createTitledBorder("Manage Cars"));

        txtModel = new JTextField();
        txtBrand = new JTextField();
        txtPricePerDay = new JTextField();
        chkAvailable = new JCheckBox("Available", true);

        form.add(new JLabel("Model:")); form.add(txtModel);
        form.add(new JLabel("Brand:")); form.add(txtBrand);
        form.add(new JLabel("Price Per Day:")); form.add(txtPricePerDay);
        form.add(new JLabel("Available:")); form.add(chkAvailable);

        JButton btnAdd = new JButton("Add Car");
        JButton btnDelete = new JButton("Delete Car");
        
        btnAdd.addActionListener(e -> addCar());
        btnDelete.addActionListener(e -> deleteCar());
        
        form.add(btnAdd);
        form.add(btnDelete);

        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Car List"));

        setLayout(new BorderLayout(10, 10));
        add(form, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        refreshCarTable();
        setVisible(true);
    }

    private void addCar() {
        try {
            Car c = new Car();
             if(txtModel.getText().length() == 0){
                JOptionPane.showMessageDialog(this, "Please enter the model name.");
                return;
            }
            if(txtBrand.getText().length() == 0){
                JOptionPane.showMessageDialog(this, "Please enter the brand name.");
                return;
            }
            if(txtPricePerDay.getText().length() == 0){
                JOptionPane.showMessageDialog(this, "Please enter any price");
                return;
            }
            c.setModel(txtModel.getText());
            c.setBrand(txtBrand.getText());
            c.setPricePerDay(Double.parseDouble(txtPricePerDay.getText()));
            c.setAvailable(chkAvailable.isSelected());
            if(c.getPricePerDay() <= 100){
                  JOptionPane.showMessageDialog(this, "Price can't be lower than 100");
                  return;
            }
            new CarDAOImpl().addCar(c);
            JOptionPane.showMessageDialog(this, "Car added successfully!");
            refreshCarTable();
            txtModel.setText("");
            txtBrand.setText("");
            txtPricePerDay.setText("");
        } 
       catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage());
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
    
    private void deleteCar(){
        int selectedRow = table.getSelectedRow();
        if(selectedRow == -1){
             JOptionPane.showMessageDialog(this, "Please select a car to delete.");
            return;
        }
         int carId = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
          int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete Car ID: " + carId + "?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                boolean status = new CarDAOImpl().deleteCar(carId);
                if(!status){
                    JOptionPane.showMessageDialog(this, "Car is rented right now");
                }
                else{
                    JOptionPane.showMessageDialog(this, "Car deleted successfully!");
                }
                
                refreshCarTable();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        }
    }

    private void refreshCarTable() {
        try {
            List<Car> cars = new CarDAOImpl().getAllCars();
            String[] cols = {"ID", "Model", "Brand", "Rent/Day", "Available"};
            String[][] data = new String[cars.size()][5];
            for (int i = 0; i < cars.size(); i++) {
                Car c = cars.get(i);
                data[i][0] = String.valueOf(c.getCarId());
                data[i][1] = c.getModel();
                data[i][2] = c.getBrand();
                data[i][3] = String.valueOf(c.getPricePerDay());
                data[i][4] = c.isAvailable() ? "Yes" : "No";
            }
            table.setModel(new javax.swing.table.DefaultTableModel(data, cols));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage());
        }catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Could not load cars: " + e.getMessage());
        }
    }
}

