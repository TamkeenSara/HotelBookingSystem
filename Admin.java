import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Admin extends User {

                               //Attributes :
    private int AdminId ;
    public static int Admin_Num = 0  ;
    private ArrayList<Hotel> hotelsadded = new ArrayList<>();
    Scanner input = new Scanner(System.in);
    Connection connection = DatabaseConnection.getConnection();


                               //Constructor :
    Admin(String Name, String Gender ,String phone_no, String email, String Address , String Username, String Password, String UserType , int admin_id) throws SQLException {
        super(Name, Gender, phone_no, email ,Address ,Username, Password, UserType);
        this.AdminId = admin_id ;
        Admin_Num ++ ;
    }
    Admin(String Name, String Gender ,String phone_no, String email, String Address , String Username, String Password, String UserType ) throws SQLException {
        super(Name, Gender, phone_no, email ,Address ,Username, Password, UserType);
        Admin_Num ++ ;
    }



                              // Getters and Setters :
    //Admin_id :
    public int get_AdminId() {
        return this.AdminId;
    }
    public void setAdminId(int adminId) {
        this.AdminId = adminId;
    }



                                   //Methods :


    // Method to delete admin
    protected void delete_Admin(int adminId) throws SQLException {
        int Idadmin = adminId ;
        PreparedStatement stat = connection.prepareStatement("Select hotelmanagementsystem.Admin_Search(?) as Result;");
        stat.setInt(1,Idadmin);
        ResultSet rs = stat.executeQuery();
        int i = 0;
        while(rs.next()){
            i = rs.getInt("Result");
        }
        if(i==1){
            PreparedStatement stat1 = connection.prepareStatement("Delete from hotelmanagementsystem.user where User_Id = ? ;");
            stat1.setInt(1,Idadmin);
            stat1.executeUpdate();
            System.out.println("Admin Deleted Successfully !");
        }
        else{
            while(i==0){
                System.out.println("\n\tInvalid Admin-Id !\n\tAdmin doesn't Exit Error...!");
                System.out.println("PLease enter a valid Admin-Id : ");
                Idadmin = input.nextInt();
                delete_Admin(Idadmin) ;
            }
        }

    }





    // Method to register a new hotel
    protected void register_Hotel() throws SQLException {

        String h_name , h_address , h_city , h_country ;
        int num_1_seater = 0, num_2_seater = 0, num_3_seater = 0, totalrooms ;
        System.out.println("Enter Hotel Details : ");
        System.out.print("\tName : ");
        h_name = input.nextLine();
        System.out.print("\tAddress : ");
        h_address = input.nextLine();
        System.out.print("\tCity : ");
        h_city = input.nextLine();
        System.out.print("\tCountry : ");
        h_country = input.nextLine();
        System.out.print("\tNumber of One Seater Rooms : ");
        num_1_seater = input.nextInt();
        System.out.print("\tNumber of Two Seater Rooms  : ");
        num_2_seater = input.nextInt();
        System.out.print("\tNumber of Three Seater Rooms : ");
        num_3_seater = input.nextInt();
        while(((num_1_seater==0)&&(num_2_seater==0)&&(num_3_seater==0))){
            System.out.println("\tERROR !\nHotel MUST contain atleast one room !\n\t");
            System.out.print("\tNumber of One Seater Rooms : ");
            num_1_seater = input.nextInt();
            System.out.print("\tNumber of Two Seater Rooms  : ");
            num_2_seater = input.nextInt();
            System.out.print("\tNumber of Three Seater Rooms : ");
        }
        totalrooms = num_1_seater + num_2_seater + num_3_seater ;
        hotelsadded.add(new Hotel(h_name ,h_address ,h_city ,h_country, num_1_seater ,num_2_seater , num_3_seater , totalrooms)) ;
        System.out.println("Hotel Added Successfully !");


    }







    // Method to add a new admin
    protected void add_Admin() throws SQLException {
        String Name , Gender , Phone_Number , Email , Address , Username , Password , temp ;
        System.out.println("Enter Admin Details : ");
        System.out.print("\tName : ");
        Name = input.nextLine();
        System.out.print("\tGender : ");
        Gender = input.nextLine();
        System.out.print("\tPhone Number : ");
        Phone_Number = input.nextLine();

        PreparedStatement stat0 = connection.prepareStatement("SELECT Phone_number FROM hotelmanagementsystem.user ;");
        ResultSet rs0 = stat0.executeQuery();
        while(rs0.next()){
            temp = rs0.getString("Phone_number");
            while(temp==Phone_Number){
                System.out.println("\tPhone Number already exist...Error !\n\tIt must be unique !\n\tPlease Enter a new Number : ");
                Phone_Number = input.nextLine();
                rs0.beforeFirst();
            }
        }

        System.out.print("\tEmail : ");
        Email = input.nextLine();
        System.out.print("\tAddress : ");
        Address = input.nextLine();
        System.out.print("\tUsername : ");
        Username = input.nextLine();

        PreparedStatement stat1 = connection.prepareStatement("SELECT Username FROM hotelmanagementsystem.user ;");
        ResultSet rs1 = stat1.executeQuery();
        while(rs1.next()){
            temp = rs1.getString("Username");
            while(temp.equals(Username)){
                System.out.println("\tUsername already exist...Error !\n\tIt must be unique !\n\tPlease enter a new Username : ");
                Username = input.nextLine();
                rs1.beforeFirst();
            }
        }

        System.out.print("\tPassword : ");
        Password = input.nextLine();
        while(Password.length()<6){
            System.out.println("\tPassword is too short !\n\tIt must contain at least 6 Characters !");
            System.out.print("\tPassword : ");
            Password = input.nextLine();
        }

        PreparedStatement stat2 = connection.prepareStatement("Insert into user (Name , Gender , Phone_number , Email , Address , Username , Password , User_Type) values (?,?,?,?,?,?,?,?); ");
        stat2.setString(1,Name);
        stat2.setString(2,Gender);
        stat2.setString(3,Phone_Number);
        stat2.setString(4,Email);
        stat2.setString(5,Address);
        stat2.setString(6,Username);
        stat2.setString(7,Password);
        stat2.setString(8,"Admin");
        stat2.executeUpdate();
        System.out.println("Admin Added Successfully !");

    }






    // Method to delete a hotel
    protected void delete_Hotel(int hotelId) throws SQLException {
        int temp = hotelId ;
        PreparedStatement stat3 = connection.prepareStatement("Select hotelmanagementsystem.Hotel_Search(?) as Hotel_Id ;");
        stat3.setInt(1,hotelId);
        ResultSet rs3 = stat3.executeQuery();
        if(rs3.next()){
            temp = rs3.getInt("Hotel_Id");
               for(Hotel h : hotelsadded){
                   if(h.get_HotelId()==temp){
                       hotelsadded.remove(h);
                   }
               }

            PreparedStatement stat4 = connection.prepareStatement("delete from hotel_owner where Owner_Id = (Select Owner_Id from hotel where Hotel_Id = ?);");
            stat4.setInt(1, hotelId);
            stat4.executeUpdate();

            stat4 = connection.prepareStatement("delete from location where Location_Id = (Select Location_Id from hotel where Hotel_Id = ?);");
            stat4.setInt(1, hotelId);
            stat4.executeUpdate();

            stat4 = connection.prepareStatement("delete from room where Room_Id in (select Room_Id from hotel_rooms where Hotel_Id = ?);");
            stat4.setInt(1, hotelId);
            stat4.executeUpdate();

            stat4 = connection.prepareStatement("delete from hotel_rooms where Hotel_Id = ?;");
            stat4.setInt(1, hotelId);
            stat4.executeUpdate();

            stat4 = connection.prepareStatement("delete from pricing where Hotel_Id = ?;");
            stat4.setInt(1, hotelId);
            stat4.executeUpdate();

            stat4 = connection.prepareStatement("delete from hotel_cancellation_policy where Hotel_Id = ?;");
            stat4.setInt(1, hotelId);
            stat4.executeUpdate();

            stat4 = connection.prepareStatement("delete from booking_day where Booking_Id in (select Booking_Id from booking where Hotel_Id = ?);");
            stat4.setInt(1, hotelId);
            stat4.executeUpdate();

            stat4 = connection.prepareStatement("delete from booking_details where Booking_Id in (select Booking_Id from booking where Hotel_Id = ?);");
            stat4.setInt(1, hotelId);
            stat4.executeUpdate();

            stat4 = connection.prepareStatement("delete from booking where Hotel_Id = ?;");
            stat4.setInt(1, hotelId);
            stat4.executeUpdate();

            stat4 = connection.prepareStatement("delete from hotel where Hotel_Id = ?;");
            stat4.setInt(1, hotelId);
            stat4.executeUpdate();

            // Enable foreign key checks
            connection.createStatement().execute("SET FOREIGN_KEY_CHECKS = 1");            stat4.setInt(1,hotelId);
            stat4.setInt(2,hotelId);
            stat4.setInt(3,hotelId);
            stat4.setInt(4,hotelId);
            stat4.setInt(5,hotelId);
            stat4.setInt(6,hotelId);
            stat4.setInt(7,hotelId);
            stat4.setInt(8,hotelId);
            stat4.setInt(9,hotelId);
            stat4.setInt(10,hotelId);
            stat4.executeUpdate();
            System.out.println("Hotel Deleted Successfully ! ");
        }
        else{
            System.out.println("Hotel Doesn't Exist !\n\tPlease enter a valid Hotel ID : ");
            temp= input.nextInt();
            delete_Hotel(temp);
        }

    }




}

