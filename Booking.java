import java.time.LocalDate;

public class Booking {
    private int Booking_Id ;
    private LocalDate Check_In ;    //new Date(int month, int day, int year)
    private LocalDate Check_Out ;    //new Date(int month, int day, int year)
    private String Booking_Status ; // "active", "out of date", "cancelled"
    private int Hotel_Id ;
    private int Room_Id ;
    private int Customer_Id ;
    private double Booking_price ;
    private String Payment_method ;
    private int Booking_days ;
    public static int Booking_Num = 0 ;

    //Constructor :
    Booking(int B_id , LocalDate C_In , LocalDate C_Out , String B_status , int H_id , int R_id ,int bookdays ,double price , int customer_id){
        this.Booking_Id = B_id ;
        this.Booking_Status = B_status ;
        this.Hotel_Id = H_id ;
        this.Room_Id = R_id ;
        this.Booking_days = bookdays ;
        this.Booking_price = price ;
        this.Customer_Id = customer_id ;
        this.Check_In = C_In ;
        this.Check_Out = C_Out ;
        Booking_Num++ ;
    }

    // Getters and Setters
    public int get_BookingId() {
        return Booking_Id;
    }
    public void set_BookingId(int bookingId) {
        this.Booking_Id = bookingId;
    }

    //Booking price ;
    public double get_Booking_price(){
        return this.Booking_price;
    }
    public void set_Booking_price(double booking_price) {
        this.Booking_price = booking_price;
    }

    //Check_In date :
    public LocalDate get_CheckIn() {
        return this.Check_In;
    }
    public void set_CheckIn(LocalDate checkIn){
        this.Check_In = checkIn;
    }


    //Check_out date :
    public LocalDate get_CheckOut() {
        return this.Check_Out;
    }
    public void set_CheckOut(LocalDate checkOut) {
        this.Check_Out = checkOut;
    }


    //Booking_status ;
    public String get_BookingStatus() {
        return Booking_Status;
    }
    public void set_BookingStatus(String bookingStatus) {
        this.Booking_Status = bookingStatus;
    }


    //Hotel_id :
    public int get_HotelId() {
        return this.Hotel_Id ;
    }
    public void set_HotelId(int hotelId) {
        this.Hotel_Id = hotelId;
    }


    //Room_id  ;
    public int get_RoomId() {
        return this.Room_Id;
    }
    public void set_RoomId(int roomId) {
        this.Room_Id = roomId;
    }


    //Payment_method :
    public String get_Paymentmethod() {
        return this.Payment_method;
    }
    public void set_Paymentmethod(String payment_method){
        this.Payment_method = payment_method;
    }


    //Customer_id :
    public int get_CustomerId() {
        return this.Customer_Id;
    }
    public void set_CustomerId(int customerId) {
        this.Customer_Id = customerId;
    }


    //Booking days :
    public int get_Booking_days() {
        return this.Booking_days;
    }
    public void set_Booking_days(int booking_days){
        this.Booking_days = booking_days;
    }


}
