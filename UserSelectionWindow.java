package com.hotel.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserSelectionWindow extends JFrame {

    private JPanel topPanel, middlePanel;
    private JLabel textLabel, textLabel1, welcomeLabel, welcomeLabel1, imageLabel;
    private JRadioButton adminRadioButton, customerRadioButton;
    private ButtonGroup userTypeGroup;
    private JButton submitButton, backButton;
    private CustomerLoginWindow customerLoginWindow;
    private AdminLoginWindow adminLoginWindow;
    private ClassLoader classLoader;

    public UserSelectionWindow() {
        // Create the frame
        this.setTitle("Hotel Booking System");
        this.setSize(1000, 700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.getContentPane().setBackground(Color.WHITE);

        // Create a panel for the top section with image and text
        topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        topPanel.setBackground(Color.WHITE);

        // Create a panel for the logo and text
        JPanel logoPanel = new JPanel();
        logoPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        logoPanel.setBackground(Color.WHITE);

        // Load the image
        classLoader = UserSelectionWindow.class.getClassLoader();
        ImageIcon icon = new ImageIcon(classLoader.getResource("Resources/BookingLogo.png"));

        // Create a label with the image icon
        imageLabel = new JLabel(icon);
        logoPanel.add(imageLabel);

        // Create a panel for the text to the right of the image
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setBackground(Color.WHITE);

        textLabel = new JLabel("BookItSmart");
        textLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        textLabel.setForeground(new Color(0, 53, 128));
        textPanel.add(textLabel);

        textLabel1 = new JLabel("Smart bookings, smart Living");
        textLabel1.setFont(new Font("SansSerif", Font.PLAIN, 18));
        textLabel1.setForeground(new Color(102, 102, 102));
        textPanel.add(textLabel1);

        logoPanel.add(textPanel);

        // Add the logo panel to the top panel
        topPanel.add(logoPanel, BorderLayout.NORTH);

        // Add the top panel to the frame
        this.add(topPanel, BorderLayout.NORTH);

        // Create a panel for the middle section with the welcome message and radio buttons
        middlePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundIcon = new ImageIcon(classLoader.getResource("Resources/bookingBackgroung.png"));
                Image backgroundImage = backgroundIcon.getImage();
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.Y_AXIS));
        middlePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        middlePanel.setBackground(new Color(0, 0, 0, 0));

        // Create a label for the welcome message
        welcomeLabel = new JLabel("Welcome To Hotel Booking System!");
        welcomeLabel.setFont(welcomeLabel.getFont().deriveFont(Font.BOLD, 30));
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcomeLabel.setForeground(Color.WHITE);
        middlePanel.add(welcomeLabel);

        welcomeLabel1 = new JLabel("Choose Your User Type:");
        welcomeLabel1.setFont(welcomeLabel1.getFont().deriveFont(Font.ITALIC, 20));
        welcomeLabel1.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcomeLabel1.setForeground(Color.WHITE);
        middlePanel.add(welcomeLabel1);

        // Create radio buttons for user types
        adminRadioButton = new JRadioButton("Admin");
        adminRadioButton.setFont(new Font("SansSerif", Font.BOLD, 20));
        adminRadioButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        adminRadioButton.setOpaque(false); // Make the button transparent
        adminRadioButton.setForeground(Color.WHITE); // Set text color to white
        adminRadioButton.setFocusable(false);

        customerRadioButton = new JRadioButton("Customer");
        customerRadioButton.setFont(new Font("SansSerif", Font.BOLD, 20));
        customerRadioButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        customerRadioButton.setOpaque(false); // Make the button transparent
        customerRadioButton.setForeground(Color.WHITE); // Set text color to white
        customerRadioButton.setFocusable(false);

        // Group the radio buttons
        userTypeGroup = new ButtonGroup();
        userTypeGroup.add(adminRadioButton);
        userTypeGroup.add(customerRadioButton);

        // Add radio buttons to the middle panel
        middlePanel.add(adminRadioButton);
        middlePanel.add(customerRadioButton);

        // Create a panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setOpaque(false);

        // Create a Back button
        backButton = new JButton("Back");
        backButton.setFont(backButton.getFont().deriveFont(Font.BOLD, 16));
        backButton.setPreferredSize(new Dimension(100, 40));
        backButton.setBackground(Color.WHITE);
        backButton.setFocusable(false);
        backButton.setEnabled(false);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (adminRadioButton.isSelected()) {
                    removePanel(adminLoginWindow);
                } else if (customerRadioButton.isSelected()) {
                    removePanel(customerLoginWindow);
                }
                backButton.setEnabled(false);
                adminRadioButton.setEnabled(true);
                customerRadioButton.setEnabled(true);
                submitButton.setEnabled(true);
            }
        });
        buttonPanel.add(backButton);

        // Create a submit button
        submitButton = new JButton("Submit");
        submitButton.setFont(submitButton.getFont().deriveFont(Font.BOLD, 16));
        submitButton.setPreferredSize(new Dimension(100, 40));
        submitButton.setBackground(Color.WHITE);
        submitButton.setFocusable(false);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Check which radio button is selected
                if (adminRadioButton.isSelected()) {
                    adminLoginWindow = new AdminLoginWindow();
                    addAdminPanel(adminLoginWindow);
                    submitButton.setEnabled(false);
                    adminRadioButton.setEnabled(false);
                    customerRadioButton.setEnabled(false);
                    backButton.setEnabled(true);
                } else if (customerRadioButton.isSelected()) {
                    customerLoginWindow = new CustomerLoginWindow();
                    addCustomerPanel(customerLoginWindow);
                    submitButton.setEnabled(false);
                    adminRadioButton.setEnabled(false);
                    customerRadioButton.setEnabled(false);
                    backButton.setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(UserSelectionWindow.this, "Please select a user type");
                }
            }
        });
        buttonPanel.add(submitButton);

        // Create an exit button
        JButton exitButton = new JButton("Exit");
        exitButton.setFont(exitButton.getFont().deriveFont(Font.BOLD, 16));
        exitButton.setPreferredSize(new Dimension(100, 40));
        exitButton.setBackground(Color.WHITE);
        exitButton.setFocusable(false);
        exitButton.setEnabled(true);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int exitResponse = JOptionPane.showConfirmDialog(null, "Do You Want To Exit From Application?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (exitResponse == 0) {
                    Main.userSelectionWindow.dispose();
                }
            }
        });
        buttonPanel.add(exitButton);

        // Add the button panel to the middle panel
        middlePanel.add(buttonPanel);

        // Add the middle panel to the frame
        this.add(middlePanel, BorderLayout.CENTER);

        // Set the frame visibility to true
        this.setVisible(true);
    }

    public void addAdminPanel(AdminLoginWindow panel) {
        this.add(panel, BorderLayout.AFTER_LINE_ENDS);
      //  panel.setBackground(Color.WHITE);
        panel.setOpaque(false);
        middlePanel.add(panel);
        this.revalidate();
        this.repaint();
    }

    public void addCustomerPanel(CustomerLoginWindow panel) {
        this.add(panel, BorderLayout.AFTER_LINE_ENDS);
       // panel.setBackground(Color.WHITE);
        panel.setOpaque(false);
        middlePanel.add(panel);
        this.revalidate();
        this.repaint();
    }

    private void removePanel(LoginWindow loginWindow) {
        middlePanel.remove(loginWindow);
        this.revalidate();
        this.repaint();
    }

    public void endFrame() {
        this.dispose();
    }

}
