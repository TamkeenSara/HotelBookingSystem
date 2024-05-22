package com.hotel.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerLoginWindow extends LoginWindow{
    GridBagConstraints gbc;
    JLabel usernameLabel ,passwordLabel;
    JTextField usernameField;
    JPasswordField passwordField;
    JButton signUpButton,signInButton;
    CustomerDashboardView customerDashboardView;

    public CustomerLoginWindow() {
        // Create a panel for the form
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Username label and text field
        usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("SansSerif", Font.BOLD, 18)); // Set font size
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(usernameLabel, gbc);
        usernameLabel.setForeground(Color.WHITE);

        usernameField = new JTextField(20);
        gbc.gridx = 1;
        usernameField.setPreferredSize(new Dimension(200, 30)); // Set preferred size
        this.add(usernameField, gbc);

        // Password label and text field
        passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("SansSerif", Font.BOLD, 18)); // Set font size
        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(passwordLabel, gbc);
        passwordLabel.setForeground(Color.WHITE);
        passwordField = new JPasswordField(20);
        gbc.gridx = 1;
        passwordField.setPreferredSize(new Dimension(200, 30)); // Set preferred size
        this.add(passwordField, gbc);

        // Sign Up button
        signUpButton = new JButton("Sign Up");
        signUpButton.setPreferredSize(new Dimension(140, 30)); // Increase button size
        signUpButton.setFont(signUpButton.getFont().deriveFont(Font.BOLD, 16)); // Optional: Increase font size
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(signUpButton, gbc);

        // Sign In button
        signInButton = new JButton("Sign In");
        signInButton.setPreferredSize(new Dimension(140, 30)); // Increase button size
        signInButton.setFont(signInButton.getFont().deriveFont(Font.BOLD, 16)); // Optional: Increase font size
        gbc.gridy = 3;
        this.add(signInButton, gbc);

        // Add action listeners to the buttons
        signUpButton.addActionListener(e -> {
            signInButton.setEnabled(false);
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            // Add sign-up logic here
            JOptionPane.showMessageDialog(null, "Signed Up with Username: " + username);
            Main.userSelectionWindow.endFrame();
            customerDashboardView = new CustomerDashboardView("Welcome Dear Customer!","customerloginwindow");
        });

        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                signUpButton.setEnabled(false);
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                // Add sign-in logic here
                JOptionPane.showMessageDialog(null, "Signed In with Username: " + username);
                Main.userSelectionWindow.endFrame();
                customerDashboardView = new CustomerDashboardView("Welcome Back Dear Customer!","customerloginwindow");
            }
        });

    }
}