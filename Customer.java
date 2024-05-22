import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Customer extends User {
    private int CustomerId ;
    private String DestinationCity ;
    public static int Customer_Num = 0 ;

    Customer( String Name , String Gender , String phone_no , String email , String Address , String Username , String Password , String UserType) throws SQLException {
        super(Name , Gender , phone_no , email , Address , Username , Password , UserType) ;
        Customer_Num++ ;
    }

    Customer( String Name , String Gender, String phone_no , String email , String Address , String Username , String Password , String UserType , int customer_id) throws SQLException {
        super(Name , Gender , phone_no , email , Address , Username , Password , UserType) ;
        this.CustomerId = customer_id  ;
        Customer_Num++ ;
    }

    Customer( String Name , String Gender, String phone_no , String email , String Address , String Username , String Password , String UserType , int customer_id , String Destination_city) throws SQLException {
        super(Name , Gender , phone_no , email , Address , Username , Password , UserType) ;
        this.CustomerId = customer_id  ;
        this.DestinationCity = Destination_city  ;
        Customer_Num++ ;
    }


                                  // Getters and Setters
    //CustomerId :
    public int get_CustomerId(){
        return this.CustomerId;
    }
    public void set_CustomerId(int customerId) {
        this.CustomerId = customerId;
    }

    //Destination City :
    public String get_DestinationCity() {
        return DestinationCity ;
    }
    public void set_DestinationCity(String destinationCity) {
        this.DestinationCity = destinationCity;
    }

    //Methods :
    public void add_Customer_in_SQL(Customer obj){
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement stat3 = connection.prepareStatement("Insert into hotelmanagementsystem.user (Name , Gender , Phone_number , Email , Address , Username , Password , User_Type) Values (?,?,?,?,?,?,?,?)");
            stat3.setString(1,obj.get_Name());
            stat3.setString(2,obj.get_Gender());
            stat3.setString(3,obj.get_PhoneNumber());
            stat3.setString(4,obj.get_Email());
            stat3.setString(5,obj.get_Address());
            stat3.setString(6,obj.get_Username());
            stat3.setString(7,obj.get_Password());
            stat3.setString(8,obj.get_UserType());
            stat3.executeUpdate();
            System.out.println("Customer Added in MySQL !");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    // Method to book a room
    public void bookARoom(String destinationCity) {
        // SQL query to get hotels in the destination city
        String query = "SELECT * FROM hotel WHERE location_id IN (SELECT location_id FROM location WHERE city_id IN (SELECT city_id FROM city WHERE name = '" + destinationCity + "'));";
        // Execute query and display results
    }

    // Method to edit booking
    public void editBooking(Booking booking) {
        // SQL query to update booking details
        String query = "UPDATE booking_details SET check_in = ?, check_out = ?, booking_status = ?, payment_method = ? WHERE booking_id = ?;";
        // Execute query
    }

    // Method to display personal info
    public void displayPersonalInfo() {
        // SQL query to get customer info
        String query = "SELECT * FROM user WHERE user_id = " + get_CustomerId() + " AND user_type = 'customer';";
        // Execute query and display results
    }

    // Method to display all bookings
    public void displayAllBookings() {
        // SQL query to get all bookings of the customer
        String query = "SELECT * FROM booking WHERE user_id = " + get_CustomerId() + ";";
        // Execute query and display results
    }

    // Method to cancel booking
    public void cancelBooking(int bookingId) {
        // SQL query to delete booking
        String query = "DELETE FROM booking WHERE booking_id = " + bookingId + ";";
        // Execute query
    }
}
