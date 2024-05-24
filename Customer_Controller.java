import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Customer_Controller implements Customer_Functions {

                                            //Attributes :
    Customer customer ;

                                    //Input and DB Connection objects :
    Scanner input = new Scanner(System.in);
    Connection connection = DatabaseConnection.getConnection();


                                            //Constructors :

    //taking parameter of customer as parameters :
    Customer_Controller(String Name , String Gender , String phone_no ,String email , String Address , String Username , String Password , String UserType) throws SQLException {
        customer = new Customer(Name , Gender , phone_no , email ,Address ,Username ,Password ,UserType) ;
    }

    //taking Object as parameter :
    Customer_Controller(Customer obj) throws SQLException {
        this.customer = obj ;
        this.Controller();
    }


    public void Controller(){
        int op = 0 , h_id = 0, customer_id = 0 ;
        String place , City , h_name  ;
        do {
            System.out.println("----------------------------------------------------------");
            System.out.println("\tCustomer Functionalities : ");
            System.out.println("----------------------------------------------------------");
            System.out.println("\t\t1. Search Hotel By Place ");
            System.out.println("\t\t2. Search Hotel By City  ");
            System.out.println("\t\t3. Search Hotel By Name ");
            System.out.println("\t\t4. Display Hotel Details by Hotel ID");
            System.out.println("\t\t5. Display All Hotels ");
            System.out.println("\t\t6. Display Personal Information ");
            System.out.println("\t\t7. Create Booking ");
            System.out.println("\t\t8. Print Booking Slip ");
            System.out.println("\t\t9. Display All Bookings ");
            System.out.println("\t\t10. Exit");
            System.out.println("----------------------------------------------------------");
            System.out.print("Enter the Operation you want to perform : ");
            op = input.nextInt();
            System.out.println("----------------------------------------------------------");

            while((op != 1) && (op != 2) && (op != 3) && (op != 4) && (op != 5) && (op != 6) && (op != 7) && (op != 8) && (op != 9) && (op != 10)){
                System.out.println("\tInvalid Operation Number !\n\tPlease enter valid Operation Number (1-10) : ");
                op = input.nextInt();
            }

            if(op==10){
                System.out.println("\tExiting.......!\n\tGoodBYE...!");
                System.out.println("----------------------------------------------------------");
                break ;
            }

            switch(op){

                case 1:
                    System.out.println("SEARCH HOTELS (BY Place) : ");
                    place = input.nextLine();
                    System.out.println("----------------------------------------------------------");
                    this.Display_All_Hotels_By_Place(place);
                    break;

                case 2:
                    System.out.println("Enter the city you want to SEARCH HOTELS of : ");
                    input.nextLine() ;
                    City = input.nextLine();
                    System.out.println("----------------------------------------------------------");
                    this.Search_Hotels_By_City(City);
                    break;

                case 3:
                    System.out.println("Enter the Hotel Name you want to SEE DETAILS of : ");
                    input.nextLine() ;
                    h_name = input.nextLine();
                    System.out.println("----------------------------------------------------------");
                    this.Search_Hotel_By_Name(h_name);
                    break;

                case 4:
                    System.out.println("Enter the Hotel ID you want to SEE DETAILS of: ");
                    h_id = input.nextInt();
                    while (h_id <= 0) {
                        System.out.println("\tInvalid Hotel ID !\n\tPlease Enter a valid Hotel ID : ");
                        h_id = input.nextInt();
                    }
                    this.Display_Hotel_Details(h_id);
                    System.out.println("----------------------------------------------------------");
                    break;

                case 5:
                    this.Display_All_Hotels();
                    break;

                case 6:
                    this.Display_Personal_Information();
                    break;

                case 7:
                    this.Create_Booking(h_id);
                    break;

                case 8:
                    int booking_id ;
                    System.out.println("----------------------------------------------------------");
                    System.out.println("Enter the Booking ID : ");
                    booking_id = input.nextInt() ;
                    while (booking_id <= 0) {
                        System.out.println("\tInvalid Hotel ID !\n\tPlease Enter a valid Hotel ID : ");
                        booking_id = input.nextInt();
                    }
                    System.out.println("----------------------------------------------------------");
                    this.Print_Booking_Slip(booking_id);
                    break;

                case 9:
                    Display_all_bookings();
                    break;
            }
            System.out.println("\n\n");
        } while(op!=11);


    }


    //Method to create booking :
    @Override
    public boolean Create_Booking(int h_Id){
        boolean booking_made ;
        try {
            booking_made = customer.Book_A_Room(h_Id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return booking_made ;
    }


    //Method to display all booking of a customer :
    @Override
    public void Display_all_bookings(){
        try {
            customer.display_all_bookings();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    //Method to display personal information :
    public void Display_Personal_Information(){
        try {
            customer.display_personal_info();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    //Method to display all hotels :
    public void Display_All_Hotels(){
        try {
            customer.display_all_Hotel();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    //Method to display all hotels :
    public void Print_Booking_Slip(int b_id ){
        try {
            customer.Print_Slip(b_id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Method to display all hotels by place :
    public void Display_All_Hotels_By_Place(String place){
        try {
            customer.display_all_Hotel_by_place(place);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    //Method to Search hotel by name :
    public void Search_Hotel_By_Name(String name){
        try {
            customer.search_Hotel(name);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    //Method to Search hotel by city :
    public void Search_Hotels_By_City(String city){
        try {
            customer.display_hotels_by_City(city);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    //Method to display hotel details :
    public void Display_Hotel_Details(int h_id){
        try {
            customer.display_hotel_details(h_id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
