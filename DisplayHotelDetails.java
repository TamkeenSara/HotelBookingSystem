package com.hotel.view;


public class DisplayHotelDetails {
    public DisplayHotelDetails(String text) {

        if (text== "Invoked By Admin"){
            AdminDashboardView admindashboard = new AdminDashboardView("Display Hotel Details Functionality For Admin","displayHotelDetails");
        }
        else {
            CustomerDashboardView customerdashboard = new CustomerDashboardView("Display Hotel Details Functionality For Customer","displayHotelDetails");

        }
    }
}
