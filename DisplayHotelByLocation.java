package com.hotel.view;


public class DisplayHotelByLocation {
    public DisplayHotelByLocation(String text) {
        if (text== "Invoked By Admin"){
        AdminDashboardView admindashboard = new AdminDashboardView("Display Hotel By Location Functionality For Admin","displayHotelByLocation");
    }
        else {
            CustomerDashboardView customerdashboard = new CustomerDashboardView("Display Hotel By Location Functionality For Customer","displayHotelByLocation");

        }
    }
}
