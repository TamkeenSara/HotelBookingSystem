import java.sql.SQLException;

public class Customer_Controller implements Customer_Functions {
    Customer customer ;
    Customer_Controller(String Name , String Gender , String phoneno ,String email , String Address , String Username , String Password , String UserType) throws SQLException {
        customer = new Customer(Name , Gender , phoneno , email ,Address ,Username ,Password ,UserType) ;
    }

    Customer_Controller(Customer obj){
        this.customer = obj ;
    }

    @Override
    public void create_Booking(Booking booking) {

    }

    @Override
    public void edit_Booking(Booking booking) {

    }

    @Override
    public void cancel_Booking(Booking booking) {

    }

    @Override
    public void display_all_bookings(int Customer_id) {

    }

}
