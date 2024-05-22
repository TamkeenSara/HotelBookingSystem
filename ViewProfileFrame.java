package com.hotel.view;

import javax.swing.*;
import java.awt.*;
public class ViewProfileFrame {
    public ViewProfileFrame(String text) {
        if (text== "Invoked By Admin") {
            AdminDashboardView admindashboard = new AdminDashboardView("View Profile Functionality For Admin", "ViewProfile");
        }
        else {
            CustomerDashboardView customerdashboard = new CustomerDashboardView("View Profile Functionality For Customer", "ViewProfile");
        }
    }
}
