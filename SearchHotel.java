package com.hotel.view;


public class SearchHotel {
    public SearchHotel(String text) {
        if(text=="Invoked By Admin") {
            AdminDashboardView admindashboard = new AdminDashboardView("Search Hotel Functionality For Admin", "SearchHotel");
        }
        else {
            CustomerDashboardView customerdashboard = new CustomerDashboardView("Search HotelFunctionality For Customer ", "SearchHotel");
        }

    }
}
