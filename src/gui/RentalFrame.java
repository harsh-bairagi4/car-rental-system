/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;
import dao.impl.RentalDAOImpl;
import model.Rental;
import service.RentalService;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author harsh
 */

public class RentalFrame extends JFrame {
    private JTextField txtCarId, txtCustomerId, txtDays;
    private JTable table;

    public RentalFrame() {
        setTitle("Rent & Return Cars");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel form = new JPanel(new GridLayout(6, 2, 10, 10));
        form.setBorder(BorderFactory.createTitledBorder("Rent / Return Car"));

        txtCarId = new JTextField();
        txtCustomerId = new JTextField();
        txtDays = new JTextField();

        form.add(new JLabel("Car ID:")); form.add(txtCarId);
        form.add(new JLabel("Customer ID:")); form.add(txtCustomerId);
        form.add(new JLabel("Days to Rent:")); form.add(txtDays);

        JButton btnRent = new JButton("Rent Car");
        JButton btnReturn = new JButton("Return Car");
        JButton btnDelete = new JButton("Delete Rental");
        
        form.add(btnRent);
        form.add(btnReturn);
        form.add(btnDelete);

        btnRent.addActionListener(e -> rentCar());
        btnReturn.addActionListener(e -> returnCar());
        btnDelete.addActionListener(e -> deleteRental());

        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Active Rentals"));

        setLayout(new BorderLayout(10, 10));
        add(form, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        refreshRentalTable();
        setVisible(true);
    }

    private void rentCar() {
        try {
            int carId = Integer.parseInt(txtCarId.getText());
            int customerId = Integer.parseInt(txtCustomerId.getText());
            int days = Integer.parseInt(txtDays.getText());

            String message = new RentalService().rentCar(carId, customerId, days);
            JOptionPane.showMessageDialog(this, message);
            refreshRentalTable();
        } 
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage());
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void returnCar() {
        try {
            int carId = Integer.parseInt(txtCarId.getText());
            String message = new RentalService().returnCar(carId);
            JOptionPane.showMessageDialog(this, message);
            refreshRentalTable();
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage());
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
     private void deleteRental(){
        int selectedRow = table.getSelectedRow();
        if(selectedRow == -1){
             JOptionPane.showMessageDialog(this, "Please select a rental to delete.");
            return;
        }
         int rentalId = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
          int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete Rental ID: " + rentalId + "?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                new RentalDAOImpl().deleteRental(rentalId);
                JOptionPane.showMessageDialog(this, "Rental deleted successfully!");
                refreshRentalTable();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        }
    }

    private void refreshRentalTable() {
        try {
            List<Rental> rentals = new RentalDAOImpl().getAllActiveRentals();
            String[] cols = {"Rental ID", "Car ID", "Customer ID", "Start Date", "End Date", "Total Cost", "Returned"};
            String[][] data = new String[rentals.size()][7];
            for (int i = 0; i < rentals.size(); i++) {
                Rental r = rentals.get(i);
                data[i][0] = String.valueOf(r.getRentalId());
                data[i][1] = String.valueOf(r.getCarId());
                data[i][2] = String.valueOf(r.getCustomerId());
                data[i][3] = String.valueOf(r.getStartDate());
                data[i][4] = String.valueOf(r.getEndDate());
                data[i][5] = String.valueOf(r.getTotalCost());
                data[i][6] = r.isReturned() ? "Yes" : "No";
            }
            table.setModel(new javax.swing.table.DefaultTableModel(data, cols));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Could not load rentals: " + e.getMessage());
        }
    }
}

