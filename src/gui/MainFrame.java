/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import javax.swing.*;
import java.awt.*;
/**
 *
 * @author harsh
 */

public class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("Car Rental System Dashboard");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(33, 150, 243));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);

        JLabel heading = new JLabel("Car Rental Management System");
        heading.setFont(new Font("Arial", Font.BOLD, 26));
        heading.setForeground(Color.WHITE);
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panel.add(heading, gbc);

        JButton btnCustomers = new JButton("Manage Customers");
        JButton btnCars = new JButton("Manage Cars");
        JButton btnRentals = new JButton("Rent / Return Cars");

        Font btnFont = new Font("Arial", Font.BOLD, 16);
        btnCustomers.setFont(btnFont);
        btnCars.setFont(btnFont);
        btnRentals.setFont(btnFont);

        gbc.gridwidth = 1;
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(btnCustomers, gbc);
        gbc.gridx = 1;
        panel.add(btnCars, gbc);
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        panel.add(btnRentals, gbc);

        btnCustomers.addActionListener(e -> new CustomerFrame());
        btnCars.addActionListener(e -> new CarFrame());
        btnRentals.addActionListener(e -> new RentalFrame());

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); }
        catch (Exception e) { e.printStackTrace(); }
        SwingUtilities.invokeLater(MainFrame::new);
    }
}

