package com.hotel.view;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerDashboardView extends JFrame implements ActionListener {
    JPanel topPanel, logoPanel ,menuPanel,middlePanel;
    JLabel textLabel ,textLabel1 ,textLabel2 ,textLabel3 ,textLabel4, textLabel5,imageLabel;
    JButton bookButton;
    ClassLoader classLoader;

    public CustomerDashboardView(String labelText,String source) {
        // Create the frame
        this.setTitle("Customer Dashboard");
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
        classLoader = CustomerDashboardView.class.getClassLoader();
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
        Font menuFont = new Font("SansSerif", Font.BOLD, 18);
        menuBar.setBackground(new Color(0, 53, 128)); // Dark blue background

        // Create the menus
        JMenu bookingMenu = new JMenu("Booking Management");
        JMenu profileMenu = new JMenu("Profile");
        JMenu hotelMenu = new JMenu("Hotel");
        bookingMenu.setFont(menuFont);
        profileMenu.setFont(menuFont);
        hotelMenu.setFont(menuFont);

        // Set menu text color
        bookingMenu.setForeground(Color.WHITE);
        profileMenu.setForeground(Color.WHITE);
        hotelMenu.setForeground(Color.WHITE);


        // Create menu items for booking management
        JMenuItem bookRoomItem = new JMenuItem("Book a Room");
        JMenuItem editBookingItem = new JMenuItem("Edit Booking");
        JMenuItem viewAllBookingsItem = new JMenuItem("View All Bookings");
        JMenuItem cancelBookingItem = new JMenuItem("Cancel Booking");

        // Add menu items to the booking management menu
        bookingMenu.add(bookRoomItem);
        bookingMenu.add(editBookingItem);
        bookingMenu.add(viewAllBookingsItem);
        bookingMenu.add(cancelBookingItem);

        // Create menu items for profile
        JMenuItem viewProfileItem = new JMenuItem("View Profile");
        JMenuItem logoutItem = new JMenuItem("Log out");
        JMenuItem exitItem = new JMenuItem("Exit");


        // Add menu items to the profile menu
        profileMenu.add(viewProfileItem);
        profileMenu.add(logoutItem);
        profileMenu.add(exitItem);

        // Create menu items for hotel
        JMenuItem displayAllHotelsItem = new JMenuItem("Display All Hotels");
        JMenuItem searchHotelItem = new JMenuItem("Search Hotel");
        JMenuItem searchHotelByLocationItem = new JMenuItem("Display Hotel By Location");
        JMenuItem displayHotelDetailsItem = new JMenuItem("Display Hotel Details");
        JMenuItem displayHotelByCityItem = new JMenuItem("Display Hotel By City");

        // Add menu items to the hotel menu
        hotelMenu.add(displayAllHotelsItem);
        hotelMenu.add(searchHotelItem);
        hotelMenu.add(searchHotelByLocationItem);
        hotelMenu.add(displayHotelByCityItem);
        hotelMenu.add(displayHotelDetailsItem);

        // Add action listeners for menu items
        bookRoomItem.addActionListener(this);
        editBookingItem.addActionListener(this);
        viewAllBookingsItem.addActionListener(this);
        cancelBookingItem.addActionListener(this);
        viewProfileItem.addActionListener(this);
        logoutItem.addActionListener(this);
        exitItem.addActionListener(this);
        displayAllHotelsItem.addActionListener(this);
        searchHotelByLocationItem.addActionListener(this);
        searchHotelItem.addActionListener(this);
        displayHotelDetailsItem.addActionListener(this);
        displayHotelByCityItem.addActionListener(this);


        bookRoomItem.setFont(menuFont);
        editBookingItem.setFont(menuFont);
        viewAllBookingsItem.setFont(menuFont);
        cancelBookingItem.setFont(menuFont);
        viewProfileItem.setFont(menuFont);
        logoutItem.setFont(menuFont);
        exitItem.setFont(menuFont);
        displayAllHotelsItem.setFont(menuFont);
        searchHotelItem.setFont(menuFont);
        searchHotelByLocationItem.setFont(menuFont);
        displayHotelDetailsItem.setFont(menuFont);
        displayHotelByCityItem.setFont(menuFont);


        // Add menus to the menubar
        menuBar.add(bookingMenu);
        menuBar.add(hotelMenu);
        menuBar.add(profileMenu);

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
        textLabel2.setFont(new Font("SansSerif", Font.BOLD, 18)); // Set font size
        textLabel2.setAlignmentX(Component.CENTER_ALIGNMENT);
        textLabel2.setForeground(new Color(0, 53, 128));
        middlePanel.add(textLabel2);

        if(source == "customerloginwindow"){
            displayDashboard();
        }

        // Add the middle panel to the frame
        this.add(middlePanel, BorderLayout.CENTER);

        // Set preferred size of the menu bar (if necessary)
        menuBar.setPreferredSize(new Dimension(800, 50));

        // Add the menu bar to the frame
        topPanel.add(menuBar);

        // Set the frame visibility to true
        this.setVisible(true);
    }

    public void displayDashboard(){
        textLabel5 = new JLabel(" ");
        textLabel5.setFont(new Font("SansSerif", Font.BOLD, 18)); // Set font size
        textLabel5.setAlignmentX(Component.CENTER_ALIGNMENT);
        textLabel5.setForeground(new Color(0, 53, 128));
        middlePanel.add(textLabel5);

        textLabel3 = new JLabel("Find your next stay!");
        textLabel3.setFont(new Font("SansSerif", Font.ITALIC, 40)); // Set font size
        textLabel3.setAlignmentX(Component.LEFT_ALIGNMENT);
        textLabel3.setForeground(new Color(0, 53, 128));
        middlePanel.add(textLabel3);

        textLabel4 = new JLabel("Search deals on hotels, and much more...");
        textLabel4.setFont(new Font("SansSerif", Font.PLAIN, 18)); // Set font size
        textLabel4.setAlignmentX(Component.LEFT_ALIGNMENT);
        textLabel4.setForeground(new Color(102, 102, 102));
        middlePanel.add(textLabel4);

        // Create a button
        bookButton = new JButton("Book a Room");
        bookButton.setFont(bookButton.getFont().deriveFont(Font.BOLD, 16));
        bookButton.setPreferredSize(new Dimension(100, 40));
        bookButton.setBackground(Color.WHITE);
        bookButton.setFocusable(false);
        bookButton.getAlignmentX();
        bookButton.addActionListener(this);
        middlePanel.add(bookButton);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "Book a Room":
                this.dispose();
                new BookRoomFrame();
                break;
            case "Edit Booking":
                this.dispose();
                new EditBookingFrame();
                break;
            case "View All Bookings":
                this.dispose();
                new ViewAllBookingsFrame();
                break;
            case "Cancel Booking":
                this.dispose();
                new CancelBookingFrame();
                break;
            case "View Profile":
                this.dispose();
                new ViewProfileFrame("Invoked By Customer");
                break;
            case "Display All Hotels":
                this.dispose();
                new DisplayAllHotelsFrame("Invoked By Customer");
                break;
            case "Search Hotel":
                this.dispose();
                new SearchHotel("Invoked By Customer");
                break;
            case "Display Hotel By Location":
                this.dispose();
                new DisplayHotelByLocation("Invoked By Customer");
                break;
            case "Display Hotel By City":
                this.dispose();
                new DisplayHotelByCity("Invoked By Customer");
                break;
            case "Display Hotel Details":
                this.dispose();
                new DisplayHotelDetails("Invoked By Customer");
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

}}
