package com.hotel.view;

import javax.swing.*;
import java.awt.*;
public class AdminLoginWindow extends LoginWindow {
    GridBagConstraints gbc;
    JLabel usernameLabel,passwordLabel;
    JTextField usernameField;
    JPasswordField passwordField;
    JButton signInButton;

    public AdminLoginWindow() {
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
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Sign In button
        signInButton = new JButton("Sign In");
        signInButton.setPreferredSize(new Dimension(140, 30)); // Increase button size
        signInButton.setFont(signInButton.getFont().deriveFont(Font.BOLD, 16)); // Optional: Increase font size
        gbc.gridy = 3;
        this.add(signInButton, gbc);

        // Add action listeners to the buttons

        signInButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            // Add sign-in logic here
            JOptionPane.showMessageDialog(null, "Signed In with Username: " + username);
            Main.userSelectionWindow.endFrame();
            AdminDashboardView adminDashboardView = new AdminDashboardView("Welcome Back!","adminloginWindow");
        });

    }
}


