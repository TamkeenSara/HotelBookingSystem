package com.hotel.view;

import com.hotel.view.AdminDashboardView;


public class DisplayAllHotelsFrame{

    public DisplayAllHotelsFrame(String text) {
        if (text== "Invoked By Admin"){
            AdminDashboardView admindashboard = new AdminDashboardView("Display All Hotels Functionality For Admin","displayAllHotels");
        }
        else {
            CustomerDashboardView customerdashboard = new CustomerDashboardView("Display All Hotels Functionality For Customer","displayAllHotels");

        }

    }

}
