package com.hotel.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminDashboardView extends JFrame implements ActionListener {
    JPanel topPanel ,logoPanel,menuPanel ,middlePanel;
    JLabel textLabel ,textLabel1, textLabel2,textLabel3 ,textLabel4, textLabel5 ,imageLabel;

    JButton registerButton;

    ClassLoader classLoader;

    public AdminDashboardView(String labelText,String source) {
        // Create the frame
        this.setTitle("Admin Dashboard");
        this.setSize(1000, 700); // Increased size for better layout
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.getContentPane().setBackground(Color.WHITE);

        // Create a panel for the top section with image and text
        topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        topPanel.setBackground(Color.WHITE);

        // Create a panel for the logo and text
        logoPanel = new JPanel();
        logoPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10)); // Left-aligned layout with padding
        logoPanel.setBackground(Color.WHITE);

        // Load the image
        classLoader = AdminDashboardView.class.getClassLoader();
        ImageIcon icon = new ImageIcon(classLoader.getResource("Resources/BookingLogo.png"));

        // Create a label with the image icon
        imageLabel = new JLabel(icon);
        logoPanel.add(imageLabel);

        // Create a panel for the text to the right of the image
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setBackground(Color.WHITE);

        textLabel = new JLabel("BookItSmart");
        textLabel.setFont(new Font("SansSerif", Font.BOLD, 24)); // Set font size
        textLabel.setForeground(new Color(0, 53, 128));
        textPanel.add(textLabel);

        textLabel1 = new JLabel("Smart bookings, smart Living");
        textLabel1.setFont(new Font("SansSerif", Font.PLAIN, 18)); // Set font size
        textLabel1.setForeground(new Color(102, 102, 102));
        textPanel.add(textLabel1);

        logoPanel.add(textPanel);

        // Add the logo panel to the top panel
        topPanel.add(logoPanel, BorderLayout.NORTH);

        // Create the menubar and menu panel
        menuPanel = new JPanel(new BorderLayout());
        menuPanel.setBackground(new Color(0, 53, 128)); // Dark blue background

        JMenuBar menuBar = new JMenuBar();
        // Set custom font for the menu bar and menu items
        Font menuFont = new Font("SansSerif", Font.BOLD, 18);
        menuBar.setBackground(new Color(0, 53, 128)); // Dark blue background

        // Create the menus
        JMenu hotelMenu = new JMenu("Hotel Management");
        JMenu adminMenu = new JMenu("Admin Management");
        JMenu profileMenu = new JMenu("Profile");

        // Set menu text color
        hotelMenu.setForeground(Color.WHITE);
        adminMenu.setForeground(Color.WHITE);
        profileMenu.setForeground(Color.WHITE);
        adminMenu.setFont(menuFont);
        profileMenu.setFont(menuFont);
        hotelMenu.setFont(menuFont);


        // Create menu items for hotel management
        JMenuItem addHotelItem = new JMenuItem("Add Hotel");
        JMenuItem deleteHotelItem = new JMenuItem("Delete Hotel");
        JMenuItem viewAllHotelsItem = new JMenuItem("View All Hotels");
        JMenuItem searchHotelItem = new JMenuItem("Search Hotel");
        JMenuItem searchHotelByLocationItem = new JMenuItem("Display Hotel By Location");
        JMenuItem displayHotelByCityItem = new JMenuItem("Display Hotel By City");
        JMenuItem displayHotelDetailsItem = new JMenuItem("Display Hotel Details");

        // Add menu items to the hotel management menu
        hotelMenu.add(addHotelItem);
        hotelMenu.add(deleteHotelItem);
        hotelMenu.add(viewAllHotelsItem);
        hotelMenu.add(searchHotelItem);
        hotelMenu.add(searchHotelByLocationItem);
        hotelMenu.add(displayHotelByCityItem);
        hotelMenu.add(displayHotelDetailsItem);

        // Create menu items for admin management
        JMenuItem addAdminItem = new JMenuItem("Add Admin");
        JMenuItem deleteAdminItem = new JMenuItem("Delete Admin");

        // Add menu items to the admin management menu
        adminMenu.add(addAdminItem);
        adminMenu.add(deleteAdminItem);

        // Create menu items for profile
        JMenuItem viewProfileItem = new JMenuItem("View Profile");
        JMenuItem logoutItem = new JMenuItem("Log out");
        JMenuItem exitItem = new JMenuItem("Exit");

        // Add menu items to the profile menu
        profileMenu.add(viewProfileItem);
        profileMenu.add(logoutItem);
        profileMenu.add(exitItem);

        // Add action listeners for menu items
        addHotelItem.addActionListener(this);
        deleteHotelItem.addActionListener(this);
        viewAllHotelsItem.addActionListener(this);
        searchHotelItem.addActionListener(this);
        searchHotelByLocationItem.addActionListener(this);
        displayHotelByCityItem.addActionListener(this);
        displayHotelDetailsItem.addActionListener(this);
        addAdminItem.addActionListener(this);
        deleteAdminItem.addActionListener(this);
        viewProfileItem.addActionListener(this);
        logoutItem.addActionListener(this);
        exitItem.addActionListener(this);

        addHotelItem.setFont(menuFont);
        deleteHotelItem.setFont(menuFont);
        viewAllHotelsItem.setFont(menuFont);
        searchHotelItem.setFont(menuFont);
        searchHotelByLocationItem.setFont(menuFont);
        displayHotelByCityItem.setFont(menuFont);
        displayHotelDetailsItem.setFont(menuFont);
        addAdminItem.setFont(menuFont);
        deleteAdminItem.setFont(menuFont);
        viewProfileItem.setFont(menuFont);
        logoutItem.setFont(menuFont);
        exitItem.setFont(menuFont);

        // Add menus to the menubar
        menuBar.add(hotelMenu);
        menuBar.add(adminMenu);
        menuBar.add(profileMenu);

        // Set preferred size of the menu bar (if necessary)
        menuBar.setPreferredSize(new Dimension(800, 50));

        // Add the menu bar to the frame
        topPanel.add(menuBar);

        // Add the menubar to the menu panel
        menuPanel.add(menuBar, BorderLayout.CENTER);

        // Add the menu panel to the top panel
        topPanel.add(menuPanel, BorderLayout.SOUTH);

        // Add the top panel to the frame
        this.add(topPanel, BorderLayout.NORTH);

        // Create a panel for the middle section with the welcome message
        middlePanel = new JPanel();
        middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.Y_AXIS));
        middlePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        middlePanel.setBackground(Color.WHITE);

        textLabel2 = new JLabel(labelText);
        textLabel2.setFont(new Font("SansSerif", Font.BOLD, 20)); // Set font size
        textLabel2.setAlignmentX(Component.CENTER_ALIGNMENT);
        textLabel2.setForeground(new Color(0, 53, 128));
        middlePanel.add(textLabel2);

        if(source == "adminloginWindow"){
            displayDashboard();
        }

        // Add the middle panel to the frame
        this.add(middlePanel, BorderLayout.CENTER);

        // Set the frame visibility to true
        this.setVisible(true);
    }

    public void displayDashboard(){
        textLabel5 = new JLabel(" ");
        textLabel5.setFont(new Font("SansSerif", Font.BOLD, 18)); // Set font size
        textLabel5.setAlignmentX(Component.CENTER_ALIGNMENT);
        textLabel5.setForeground(new Color(0, 53, 128));
        middlePanel.add(textLabel5);

        textLabel3 = new JLabel("Do You Want To Register a Hotel?");
        textLabel3.setFont(new Font("SansSerif", Font.ITALIC, 30)); // Set font size
        textLabel3.setAlignmentX(Component.LEFT_ALIGNMENT);
        textLabel3.setForeground(new Color(0, 53, 128));
        middlePanel.add(textLabel3);

        textLabel4 = new JLabel("Click Below!");
        textLabel4.setFont(new Font("SansSerif", Font.PLAIN, 18)); // Set font size
        textLabel4.setAlignmentX(Component.LEFT_ALIGNMENT);
        textLabel4.setForeground(new Color(102, 102, 102));
        middlePanel.add(textLabel4);

        // Create a button
        registerButton = new JButton("Add Hotel");
        registerButton.setFont(registerButton.getFont().deriveFont(Font.BOLD, 16));
        registerButton.setPreferredSize(new Dimension(100, 40));
        registerButton.setBackground(Color.WHITE);
        registerButton.setFocusable(false);
        registerButton.getAlignmentX();
        registerButton.addActionListener(this);
        middlePanel.add(registerButton);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "Add Hotel":
                this.dispose();
                new AddHotelFrame();
                break;
            case "Delete Hotel":
                this.dispose();
                new DeleteHotelFrame();
                break;
            case "View All Hotels":
                this.dispose();
                new DisplayAllHotelsFrame("Invoked By Admin");
                break;
            case "Search Hotel":
                this.dispose();
                new SearchHotel("Invoked By Admin");
                break;
            case "Display Hotel By Location":
                this.dispose();
                new DisplayHotelByLocation("Invoked By Admin");
                break;
            case "Display Hotel By City":
                this.dispose();
                new DisplayHotelByCity("Invoked By Admin");
                break;
            case "Display Hotel Details":
                this.dispose();
                new DisplayHotelDetails("Invoked By Admin");
                break;
            case "Add Admin":
                this.dispose();
                new AddAdminFrame();
                break;
            case "Delete Admin":
                this.dispose();
                new DeleteAdmin();
                break;
            case "View Profile":
                this.dispose();
                new ViewProfileFrame("Invoked By Admin");
                break;
            case "Log out":
                int response = JOptionPane.showConfirmDialog(null,"Do You Want To Log out?","Confirmation",JOptionPane.YES_NO_OPTION);
                if(response == 0){
                    this.dispose();
                    UserSelectionWindow userSelectionWindow = new UserSelectionWindow();}
                break;
            case "Exit":
                int exitresponse = JOptionPane.showConfirmDialog(null,"Do You Want To Exit From Application?","Confirmation",JOptionPane.YES_NO_OPTION);
                if(exitresponse == 0){
                    this.dispose();
                }
                break;
        }
    }
}