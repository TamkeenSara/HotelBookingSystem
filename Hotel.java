import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList ;
import java.util.Scanner;

public class Hotel {
    private int Hotel_Id ;
    private String Hotel_Name ;
    private String Hotel_Address ;
    private String Hotel_Country ;
    private String Hotel_City ;
    private String Cancellation_policy ;
    private int num_OneSeater_Rooms ;
    private int num_TwoSeater_Rooms ;
    private int num_ThreeSeater_Rooms ;
    private int Total_Rooms ;
    private ArrayList<String> hotelFacilities ;
    private static int Num_of_Hotels = 0 ;
    private One_SeaterRoom [] oneSeaterRooms ;
    private Two_SeaterRoom [] twoSeaterRooms ;
    private Three_SeaterRoom [] threeSeaterRooms ;
    private Hotel_Owners owner ;
    Scanner input = new Scanner(System.in);
    Connection connection = DatabaseConnection.getConnection();

                                       //Constructor :
    public Hotel(String h_name , String h_address , String h_city ,String country, int num_one_seater , int num_two_seater ,  int num_three_seater , int num_total) throws SQLException {

        this.Hotel_Name = h_name ;
        this.Hotel_Address = h_address ;
        this.Hotel_City = h_city ;
        this.Hotel_Country = country ;
        this.num_OneSeater_Rooms = num_one_seater ;
        this.num_TwoSeater_Rooms = num_two_seater ;
        this.num_ThreeSeater_Rooms = num_three_seater ;
        this.Total_Rooms = num_total ;

        oneSeaterRooms = new One_SeaterRoom[this.num_OneSeater_Rooms];
        twoSeaterRooms = new Two_SeaterRoom[this.num_TwoSeater_Rooms];
        threeSeaterRooms = new Three_SeaterRoom[this.num_ThreeSeater_Rooms];

        String O_Name , O_Gender , O_Phone_number , O_Email;
        int O_Age ;
        System.out.println("\n\tEnter Hotel Owner Information : ");
        System.out.println("\t\t Name : ");
        input.nextLine();
        O_Name = input.nextLine();
        System.out.println("\t\t Gender : ");
        O_Gender = input.nextLine();
        System.out.println("\t\t Age : ");
        O_Age = input.nextInt();
        System.out.println("\t\t Phone Number : ");
        O_Phone_number = input.nextLine();
        System.out.println("\t\t Email : ");
        O_Email = input.nextLine();
        this.owner = new Hotel_Owners(O_Name ,O_Age, O_Gender , O_Phone_number , O_Email);


        //pricing structure :
        double ppn1 , ppn2 , ppn3 ;
        System.out.println("Price of One Seater Room : ");
        ppn1 = input.nextDouble();
        System.out.println("Price of Two Seater Room : ");
        ppn2 = input.nextDouble();
        System.out.println("Price of Three Seater Room : ");
        ppn3 = input.nextDouble();


        //Cancellation Policy :
        System.out.println("\n");
        int op ;
        System.out.println("\tEnter Booking Cancellation Policy of your Hotel (1-3): ");
        System.out.println("\t\t1. No Cancellation ");
        System.out.println("\t\t2. Cancel Anytime ");
        System.out.println("\t\t3. Cancel till 24 hours before Check-In");
        System.out.println("\tSelection Number : ");
        op = input.nextInt();
        while((op!=1) && (op!=2) && (op!=3)){
            System.out.println("Invalid Selection !");
            System.out.println("Please enter valid number (1-3): ");
            op = input.nextInt();
        }
        if(op==1){
            this.set_Cancellationpolicy("No Cancellation");
        }
        else if (op==2) {
            this.set_Cancellationpolicy("Cancel Anytime");
        }
        else{
            this.set_Cancellationpolicy("Cancel till 24 hours before Check-In");
        }


        //Adding hotel in SQL :
        int country_id = 0 , city_id = 0 ,location_id = 0 ;;
        PreparedStatement check1 = connection.prepareStatement("select hotelmanagementsystem.Country_Search('?') as Country_Id ;");
        check1.setString(1,get_HotelCountry());
        ResultSet check2 = check1.executeQuery();
        while(check2.next()){
            country_id = check2.getInt("Country_Id");
        }

        if(country_id!=0){

            PreparedStatement check3 = connection.prepareStatement("SELECT City_Id FROM city where Country_Id = ? and Name = ? ;");
            check3.setInt(1,country_id);
            check3.setString(2,this.get_HotelCity());
            ResultSet check4 = check3.executeQuery();
            if(check4.next()){
                do {
                    city_id = check4.getInt("City_Id");
                } while (check4.next());
            }
            else{
                PreparedStatement check5 = connection.prepareStatement("Insert into City (Name,Country_Id)values(?,?);");
                check5.setString(1,this.get_HotelCity());
                check5.setInt(2,country_id);
                check5.executeUpdate();

                PreparedStatement check6 = connection.prepareStatement("SELECT City_Id FROM hotelmanagementsystem.city where Name = ? and Country_Id = ? ;");
                check6.setString(1,get_HotelCity());
                check6.setInt(2,country_id);
                ResultSet check7 = check6.executeQuery();
                while(check7.next()){
                    city_id = check7.getInt("City_Id");
                }

            }

            PreparedStatement check5 = connection.prepareStatement("Insert into location (Address , City_Id , Country_Id) values (?,?,?);");
            check5.setString(1,get_HotelAddress());
            check5.setInt(2,city_id);
            check5.setInt(3,country_id);
            check5.executeUpdate();

        }
        else{

            PreparedStatement stat = connection.prepareStatement("Insert into Country (Country_Name )values(?);");
            stat.setString(1,get_HotelCountry());
            stat.executeUpdate();

            PreparedStatement stat1 = connection.prepareStatement("SELECT Country_Id FROM hotelmanagementsystem.country Where Country_Name = ?;");
            stat1.setString(1,get_HotelCountry());
            ResultSet rs1 = stat1.executeQuery();
            while(rs1.next()){
                country_id = rs1.getInt("Country_Id");
            }

            PreparedStatement stat2 = connection.prepareStatement("Insert into city (Name , Country_Id) values (?,?);");
            stat2.setString(1,get_HotelCity());
            stat2.setInt(2,country_id);
            stat2.executeUpdate();


            PreparedStatement stat3 = connection.prepareStatement("SELECT City_Id FROM hotelmanagementsystem.city where Name = ? and Country_Id = ? ;");
            stat3.setString(1,get_HotelCity());
            stat3.setInt(2,country_id);
            ResultSet rs2 = stat3.executeQuery();
            while(rs2.next()){
                city_id = rs2.getInt("City_Id");
            }

            PreparedStatement stat4 = connection.prepareStatement("Insert into location (Address , City_Id , Country_Id) values (?,?,?);");
            stat4.setString(1,get_HotelAddress());
            stat4.setInt(2,city_id);
            stat4.setInt(3,country_id);
            stat4.executeUpdate();

            PreparedStatement stat5 = connection.prepareStatement("SELECT Location_Id FROM hotelmanagementsystem.location where Address = ? and City_Id = ? ; ");
            stat5.setString(1,get_HotelAddress());
            stat5.setInt(2,city_id);
            ResultSet rs3 = stat5.executeQuery();
            while(rs3.next()){
                location_id = rs3.getInt("Location_Id");
            }

        }

        PreparedStatement stat6 = connection.prepareStatement("Insert into hotel_owner (Name , Age , Gender ,Phone_Number , Email) values (?,?,?,?,?);");
        stat6.setString(1,owner.get_Name());
        stat6.setInt(2,owner.get_Age());
        stat6.setString(3,owner.get_Gender());
        stat6.setString(4,owner.get_PhoneNumber());
        stat6.setString(5,owner.get_Email());
        stat6.executeUpdate();

        PreparedStatement stat7 = connection.prepareStatement("SELECT Owner_Id FROM hotelmanagementsystem.hotel_owner where Name = ? and Phone_Number = ? ; ");
        stat7.setString(1,owner.get_Name());
        stat7.setString(2,owner.get_PhoneNumber());
        ResultSet rs4 = stat7.executeQuery();
        int O_id = 0 ;
        while(rs4.next()){
            O_id = rs4.getInt("Owner_Id");
        }
        owner.set_OwnerId(O_id);

        PreparedStatement stat8 = connection.prepareStatement("Insert into hotel (Name , Location_Id , Owner_Id , Total_Rooms ) values(?,?,?,?) ; ");
        stat8.setString(1,this.get_HotelName());
        stat8.setInt(2,location_id);
        stat8.setInt(3,owner.get_OwnerId());
        stat8.setInt(4,this.Total_Rooms);
        stat8.executeUpdate();

        PreparedStatement stat9 = connection.prepareStatement("Select Hotel_Id from hotelmanagementsystem.hotel where Name = ? and Owner_Id = ?; ");
        stat9.setString(1,this.Hotel_Name);
        stat9.setInt(2,owner.get_OwnerId());
        ResultSet rs5 = stat9.executeQuery();
        int h_id = 0 ;
        while(rs5.next()){
            h_id = rs5.getInt("Hotel_Id");
        }
        this.set_HotelId(h_id);


        for(int i = 1 ; i <=3 ; i++ ){
            PreparedStatement stat10 = connection.prepareStatement("Insert into pricing (Hotel_Id , Type_Id , Price_per_night) values (?,?,?) ; ");
            stat10.setInt(1, this.get_HotelId());
            stat10.setInt(2, i);
            if(i==1){
                stat10.setDouble(3,ppn1);
                stat10.executeUpdate();
            }
            else if (i==2){
                stat10.setDouble(3,ppn2);
                stat10.executeUpdate();
            }
            else{
                stat10.setDouble(3,ppn3);
                stat10.executeUpdate();
            }
        }

        PreparedStatement stat11 = connection.prepareStatement("Insert into hotel_cancellation_policy (Hotel_Id , Cancellation_Id) values (?,?) ; ");
        stat11.setInt(1,this.get_HotelId());
        stat11.setInt(2,op);
        stat11.executeUpdate();


        //adding rooms :

        //one-seater room :
        System.out.println("\n");
        System.out.println("Enter Rooms Information : ");
        System.out.println("\tOne Seater Rooms : ");
        String roomnumber1 ;
        //initiating one-seater rooms
        for(int i=0 ; i<(this.num_OneSeater_Rooms) ; i++ ){
            oneSeaterRooms[i] = new One_SeaterRoom();
            System.out.println("\t\tRoom "+(i+1)+" : ");
            System.out.println("Room Number : ");
            input.nextLine();
            roomnumber1 = input.nextLine();
            oneSeaterRooms[i].set_RoomNumber(roomnumber1);
            oneSeaterRooms[i].set_HotelId(this.Hotel_Id);
            oneSeaterRooms[i].set_PricePerNight(ppn1);
            System.out.println("\n");

            PreparedStatement stat12 = connection.prepareStatement("Insert into room (Room_Number) values (?) ;");
            stat12.setString(1,roomnumber1);
            stat12.executeUpdate();

            PreparedStatement stat13 = connection.prepareStatement("SELECT Room_Id FROM hotelmanagementsystem.room Where Room_Number = ? ;");
            stat13.setString(1,oneSeaterRooms[i].get_RoomNumber());
            ResultSet rs6 = stat13.executeQuery();
            int room_id = 0 ;
            while(rs6.next()){
                room_id = rs6.getInt("Room_Id");
            }
            oneSeaterRooms[i].set_RoomId(room_id);

            PreparedStatement stat14 = connection.prepareStatement("Insert into hotel_rooms (Hotel_Id , Room_Id , Type_Id) values (? , ? , ? ) ; ");
            stat14.setInt(1,this.get_HotelId());
            stat14.setInt(2,room_id);
            stat14.setInt(3,1);
            stat14.executeUpdate();

        }


        //two-seater rooms :
        System.out.println("\tTwo Seater Rooms : ");
        String roomnumber2 ;
        //initiating one-seater rooms
        for(int i=0 ; i<(this.num_TwoSeater_Rooms) ; i++ ){
            twoSeaterRooms[i] = new Two_SeaterRoom();
            System.out.println("\t\tRoom "+(i+1)+" : ");
            System.out.println("Room Number : ");
            input.nextLine();
            roomnumber2 = input.nextLine();
            twoSeaterRooms[i].set_RoomNumber(roomnumber2);
            twoSeaterRooms[i].set_HotelId(this.Hotel_Id);
            twoSeaterRooms[i].set_PricePerNight(ppn2);
            System.out.println("\n");

            PreparedStatement stat12 = connection.prepareStatement("Insert into room (Room_Number) values (?) ;");
            stat12.setString(1,roomnumber2);
            stat12.executeUpdate();

            PreparedStatement stat13 = connection.prepareStatement("SELECT Room_Id FROM hotelmanagementsystem.room Where Room_Number = ? ;");
            stat13.setString(1,twoSeaterRooms[i].get_RoomNumber());
            ResultSet rs6 = stat13.executeQuery();
            int room_id = 0 ;
            while(rs6.next()){
                room_id = rs6.getInt("Room_Id");
            }
            twoSeaterRooms[i].set_RoomId(room_id);

            PreparedStatement stat14 = connection.prepareStatement("Insert into hotel_rooms (Hotel_Id , Room_Id , Type_Id) values (? , ? , ? ) ; ");
            stat14.setInt(1,this.get_HotelId());
            stat14.setInt(2,twoSeaterRooms[i].get_RoomId());
            stat14.setInt(3,2);
            stat14.executeUpdate();
        }


        //three-seater room :
        System.out.println("\tThree Seater Rooms : ");
        String roomnumber3 ;
        //initiating one-seater rooms
        for(int i=0 ; i<(this.num_ThreeSeater_Rooms) ; i++ ){
            threeSeaterRooms[i] = new Three_SeaterRoom();
            System.out.println("\t\tRoom "+(i+1)+" : ");
            System.out.println("Room Number : ");
            input.nextLine();
            roomnumber3 = input.nextLine();
            threeSeaterRooms[i].set_RoomNumber(roomnumber3);
            threeSeaterRooms[i].set_HotelId(this.Hotel_Id);
            threeSeaterRooms[i].set_PricePerNight(ppn3);
            System.out.println("\n");

            PreparedStatement stat12 = connection.prepareStatement("Insert into room (Room_Number) values (?) ;");
            stat12.setString(1,roomnumber3);
            stat12.executeUpdate();

            PreparedStatement stat13 = connection.prepareStatement("SELECT Room_Id FROM hotelmanagementsystem.room Where Room_Number = ? ;");
            stat13.setString(1,threeSeaterRooms[i].get_RoomNumber());
            ResultSet rs6 = stat13.executeQuery();
            int room_id = 0 ;
            while(rs6.next()){
                room_id = rs6.getInt("Room_Id");
            }
            threeSeaterRooms[i].set_RoomId(room_id);

            PreparedStatement stat14 = connection.prepareStatement("Insert into hotel_rooms (Hotel_Id , Room_Id , Type_Id) values (? , ? , ? ) ; ");
            stat14.setInt(1,this.get_HotelId());
            stat14.setInt(2,threeSeaterRooms[i].get_RoomId());
            stat14.setInt(3,3);
            stat14.executeUpdate();
        }

        Num_of_Hotels ++ ;
    }




                          // Getters and Setters :
    //Hotel_id :
    public int get_HotelId() {
        return this.Hotel_Id;
    }
    public void set_HotelId(int hotelId) {
        this.Hotel_Id = hotelId;
    }

    //Hotel_name :
    public String get_HotelName() {
        return this.Hotel_Name;
    }
    public void set_HotelName(String hotelName) {
        this.Hotel_Name = hotelName;
    }


    //Hotel Address :
    public String get_HotelAddress() {
        return Hotel_Address;
    }
    public void set_HotelAddress(String hotelAddress) {
        this.Hotel_Address = hotelAddress;
    }


    //Hotel City :
    public String get_HotelCity() {
        return this.Hotel_City;
    }
    public void set_HotelCity(String hotelCity) {
        this.Hotel_City = hotelCity;
    }


    //Hotel Country :
    public String get_HotelCountry() {
        return this.Hotel_Country;
    }
    public void set_HotelCountry(String hotel_Country) {
        this.Hotel_Country = hotel_Country;
    }


    //Cancellation Policy :
    public String get_Cancellationpolicy() {
        return this.Cancellation_policy;
    }
    public void set_Cancellationpolicy(String cancellation_policy) {
        this.Cancellation_policy = cancellation_policy;
    }

    //Num_OneSeater_rooms :
    public int get_OneSeaterRooms(){
        return this.num_OneSeater_Rooms;
    }
    public void set_OneSeaterRooms(int oneSeaterRooms) {
        this.num_OneSeater_Rooms = oneSeaterRooms;
    }

    //Num_TwoSeater_rooms :
    public int get_TwoSeaterRooms() {
        return num_TwoSeater_Rooms;
    }
    public void set_TwoSeaterRooms(int twoSeaterRooms) {
        this.num_TwoSeater_Rooms = twoSeaterRooms;
    }


    //Num_ThreeSeater_rooms :
    public int get_ThreeSeaterRooms() {
        return num_ThreeSeater_Rooms;
    }
    public void set_ThreeSeaterRooms(int threeSeaterRooms) {
        this.num_ThreeSeater_Rooms = threeSeaterRooms;
    }


    //Total_rooms :
    public int get_TotalRooms() {
        return this.Total_Rooms;
    }
    public void set_TotalRooms(int totalRooms) {
        this.Total_Rooms = totalRooms;
    }


    public ArrayList<String> getHotelFacilities() {
        return hotelFacilities;
    }
    public void setHotelFacilities(ArrayList<String> hotelFacilities) {
        this.hotelFacilities = hotelFacilities;
    }




                              // Static methods to display hotels
    public static void display_all_Hotels(){

    }


}
