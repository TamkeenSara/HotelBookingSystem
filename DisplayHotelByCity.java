package com.hotel.view;

public class DisplayHotelByCity {

    public DisplayHotelByCity(String text) {
        if (text== "Invoked By Admin"){
            AdminDashboardView admindashboard = new AdminDashboardView("Display Hotels By City Functionality For Admin","displayAllHotels");
        }
        else {
            CustomerDashboardView customerdashboard = new CustomerDashboardView("Display Hotels By City Functionality For Customer","displayAllHotels");

        }
    }
}
