import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Customer extends User {
    private int CustomerId;
    private String DestinationCity;
    private ArrayList<Booking> My_Bookings;
    public static int Customer_Num = 0;

    Customer(String Name, String Gender, String phone_no, String email, String Address, String Username, String Password, String UserType) throws SQLException {
        super(Name, Gender, phone_no, email, Address, Username, Password, UserType);
        Customer_Num++;
    }

    Customer(String Name, String Gender, String phone_no, String email, String Address, String Username, String Password, String UserType, int customer_id) throws SQLException {
        super(Name, Gender, phone_no, email, Address, Username, Password, UserType);
        this.CustomerId = customer_id;
        Customer_Num++;

    }

    Customer(String Name, String Gender, String phone_no, String email, String Address, String Username, String Password, String UserType, int customer_id, String Destination_city) throws SQLException {
        super(Name, Gender, phone_no, email, Address, Username, Password, UserType);
        this.CustomerId = customer_id;
        this.DestinationCity = Destination_city;
        Customer_Num++;

    }


    // Getters and Setters
    //CustomerId :
    public int get_CustomerId() {
        return this.CustomerId;
    }

    public void set_CustomerId(int customerId) {
        this.CustomerId = customerId;
    }

    //Destination City :
    public String get_DestinationCity() {
        return DestinationCity;
    }

    public void set_DestinationCity(String destinationCity) {
        this.DestinationCity = destinationCity;
    }



                                               //Methods :


    //Method to add customer in MySQL :
    public void add_Customer_in_SQL(Customer obj) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement stat3 = connection.prepareStatement("Insert into hotelmanagementsystem.user (Name , Gender , Phone_number , Email , Address , Username , Password , User_Type) Values (?,?,?,?,?,?,?,?)");
            stat3.setString(1, obj.get_Name());
            stat3.setString(2, obj.get_Gender());
            stat3.setString(3, obj.get_PhoneNumber());
            stat3.setString(4, obj.get_Email());
            stat3.setString(5, obj.get_Address());
            stat3.setString(6, obj.get_Username());
            stat3.setString(7, obj.get_Password());
            stat3.setString(8, obj.get_UserType());
            stat3.executeUpdate();
            System.out.println("Customer Added in MySQL !");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }




    // Method to Book a Room :
    public boolean Book_A_Room(int h_Id) throws SQLException {
        int h_id = h_Id, hotel_found = 0;
        PreparedStatement stat4 = connection.prepareStatement("select hotelmanagementsystem.Hotel_Search(?);");
        stat4.setInt(1, h_id);
        ResultSet rs4 = stat4.executeQuery();
        while (rs4.next()) {
            hotel_found = rs4.getInt("One_Seater_Rooms");
        }
        //-----------------------------------------------------------------//
        if (hotel_found != 0) {

            //Declaring variables :

            int R_type, room_id = 0, num1 = 0, num2 = 0, num3 = 0, total_nums, day = 0, month = 0,
                    day_out = 0, month_out = 0, p , bookingId = 0 , num_of_days ;
            String Room = null, payment , b_status ;
            double ppn1 = 0, ppn2 = 0, ppn3 = 0 , price = 0  ;
            LocalDate check_in, check_out;
            ArrayList<Integer> room_id_s = null;
            final int[] daysPerMonth =
                    {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

            //-----------------------------------------------------------------//

            //Getting prices of room for this hotel from MySQL :
            for (int i = 1; i <= 3; i++) {

                if (i == 1) {
                    PreparedStatement stat = connection.prepareStatement("select hotelmanagementsystem.One_Seater_Price(?) as One_Seater_Price;");
                    stat.setInt(1, h_id);
                    ResultSet rs = stat.executeQuery();
                    while (rs.next()) {
                        ppn1 = rs.getInt("One_Seater_Price");
                    }
                } else if (i == 2) {
                    PreparedStatement stat = connection.prepareStatement("select hotelmanagementsystem.Two_Seater_Price(?) as Two_Seater_Price;");
                    stat.setInt(1, h_id);
                    ResultSet rs = stat.executeQuery();
                    while (rs.next()) {
                        ppn2 = rs.getInt("Two_Seater_Price");
                    }
                } else {
                    PreparedStatement stat = connection.prepareStatement("select hotelmanagementsystem.Three_Seater_Price(?) as Three_Seater_Price;");
                    stat.setInt(1, h_id);
                    ResultSet rs = stat.executeQuery();
                    while (rs.next()) {
                        ppn3 = rs.getInt("Three_Seater_Price");
                    }
                }
            }

            //-----------------------------------------------------------------//

            //Getting number of one-seater , two-seater , three seater and total rooms of this hotel from MySQL :

            PreparedStatement stat0 = connection.prepareStatement("select hotelmanagementsystem.One_Seater_Room(?) as One_Seater_Rooms;");
            stat0.setInt(1, h_id);
            ResultSet rs0 = stat0.executeQuery();
            while (rs0.next()) {
                num1 = rs0.getInt("One_Seater_Rooms");
            }

            PreparedStatement stat1 = connection.prepareStatement("select hotelmanagementsystem.Two_Seater_Room(?) as Two_Seater_Rooms;");
            stat1.setInt(1, h_id);
            ResultSet rs1 = stat1.executeQuery();
            while (rs1.next()) {
                num2 = rs1.getInt("Two_Seater_Rooms");
            }

            PreparedStatement stat2 = connection.prepareStatement("select hotelmanagementsystem.Three_Seater_Room(?) as Three_Seater_Rooms;");
            stat2.setInt(1, h_id);
            ResultSet rs2 = stat2.executeQuery();
            while (rs2.next()) {
                num3 = rs2.getInt("Three_Seater_Rooms");
            }

            PreparedStatement stat3 = connection.prepareStatement("select Total_Rooms from hotelmanagementsystem.hotel where Hotel_Id = ? ;");
            stat3.setInt(1, h_id);
            ResultSet rs3 = stat3.executeQuery();
            while (rs3.next()) {
                total_nums = rs3.getInt("Total_Rooms");
            }

            //-----------------------------------------------------------------//

            //Selecting type of the room by the user :

            //All three types of rooms available :
            if (num1 != 0 && num2 != 0 && num3 != 0) {
                System.out.println("\t\tSelect the type of the Room you want to Book : ");
                System.out.println("\t1. One Seater Room " + "(" + ppn1 + "rs per night)");
                System.out.println("\t2. Two Seater Room " + "(" + ppn2 + "rs per night)");
                System.out.println("\t3. Three Seater Room " + "(" + ppn3 + "rs per night)");
                System.out.println("\t4. Exit Booking");
                System.out.print("Enter Room Selection (1-4): ");
                R_type = input.nextInt();
                while (!(R_type >= 1 && R_type <= 4)) {
                    System.out.print("\tInvalid Selection !\n\tPlease enter a valid room selection : ");
                    R_type = input.nextInt();
                }
                if (R_type == 1) {
                    Room = "One Seater Room";
                    System.out.println("\n\t" + Room + " Selected ! ");
                } else if (R_type == 2) {
                    Room = "Two Seater Room";
                    System.out.println("\n\t" + Room + " Selected ! ");
                } else if (R_type == 3) {
                    Room = "Three Seater Room";
                    System.out.println("\n\t" + Room + " Selected ! ");
                } else {
                    return false;
                }
            }

            //One-seater types of rooms not available :
            if (num1 == 0 && num2 != 0 && num3 != 0) {
                System.out.println("\t\tSelect the type of the Room you want to Book : ");
                System.out.println("\t1. Two Seater Room " + "(" + ppn2 + "rs per night)");
                System.out.println("\t2. Three Seater Room " + "(" + ppn3 + "rs per night)");
                System.out.println("\t3. Exit Booking");
                System.out.print("Enter Room Selection (1-3): ");
                R_type = input.nextInt();
                while (!(R_type >= 1 && R_type <= 3)) {
                    System.out.print("\tInvalid Selection !\n\tPlease enter a valid room selection : ");
                    R_type = input.nextInt();
                }
                if (R_type == 1) {
                    Room = "Two Seater Room";
                    System.out.println("\n\t" + Room + " Selected ! ");
                } else if (R_type == 2) {
                    Room = "Three Seater Room";
                    System.out.println("\n\t" + Room + " Selected ! ");
                } else {
                    return false;
                }
            }

            //Two-seater types of rooms not available :
            if (num1 != 0 && num2 == 0 && num3 != 0) {
                System.out.println("\t\tSelect the type of the Room you want to Book : ");
                System.out.println("\t1. One Seater Room " + "(" + ppn1 + "rs per night)");
                System.out.println("\t2. Three Seater Room " + "(" + ppn3 + "rs per night)");
                System.out.println("\t3. Exit Booking");
                System.out.print("Enter Room Selection (1-3): ");
                R_type = input.nextInt();
                while (!(R_type >= 1 && R_type <= 3)) {
                    System.out.print("\tInvalid Selection !\n\tPlease enter a valid room selection : ");
                    R_type = input.nextInt();
                }
                if (R_type == 1) {
                    Room = "One Seater Room";
                    System.out.println("\n\t" + Room + " Selected ! ");
                } else if (R_type == 2) {
                    Room = "Three Seater Room";
                    System.out.println("\n\t" + Room + " Selected ! ");
                } else {
                    return false;
                }
            }

            //Three-seater types of rooms not available :
            if (num1 != 0 && num2 != 0 && num3 == 0) {
                System.out.println("\t\tSelect the type of the Room you want to Book : ");
                System.out.println("\t1. One Seater Room " + "(" + ppn1 + "rs per night)");
                System.out.println("\t2. Two Seater Room " + "(" + ppn2 + "rs per night)");
                System.out.println("\t3. Exit Booking");
                System.out.print("Enter Room Selection (1-3): ");
                R_type = input.nextInt();
                while (!(R_type >= 1 && R_type <= 3)) {
                    System.out.print("\tInvalid Selection !\n\tPlease enter a valid room selection : ");
                    R_type = input.nextInt();
                }
                if (R_type == 1) {
                    Room = "One Seater Room";
                    System.out.println("\n\t" + Room + " Selected ! ");
                } else if (R_type == 2) {
                    Room = "Two Seater Room";
                    System.out.println("\n\t" + Room + " Selected ! ");
                } else {
                    return false;
                }
            }

            //One and Two-seater types of rooms not available :
            if (num1 == 0 && num2 == 0 && num3 != 0) {
                System.out.println("\t\tSelect the type of the Room you want to Book : ");
                System.out.println("\t1. Three Seater Room " + "(" + ppn3 + "rs per night)");
                System.out.println("\t2. Exit Booking");
                System.out.print("Only Three Seater Rooms are available in this Hotel.\n\t\tEnter Room Selection : ");
                R_type = input.nextInt();
                while (!(R_type >= 1 && R_type <= 2)) {
                    System.out.print("\tInvalid Selection !\n\tPlease enter a valid room selection : ");
                    R_type = input.nextInt();
                }
                if (R_type == 1) {
                    Room = "Three Seater Room";
                    System.out.println("\n\t" + Room + " Selected ! ");
                } else {
                    return false;
                }
            }

            //One and Three-seater types of rooms not available :
            if (num1 == 0 && num2 != 0 && num3 == 0) {
                System.out.println("\t\tSelect the type of the Room you want to Book : ");
                System.out.println("\t1. Two Seater Room " + "(" + ppn2 + "rs per night)");
                System.out.println("\t2. Exit Booking");
                System.out.print("Only Two Seater Rooms are available in this Hotel.\n\t\tEnter Room Selection : ");
                R_type = input.nextInt();
                while (!(R_type >= 1 && R_type <= 2)) {
                    System.out.print("\tInvalid Selection !\n\tPlease enter a valid room selection : ");
                    R_type = input.nextInt();
                }
                if (R_type == 1) {
                    Room = "Two Seater Room";
                    System.out.println("\n\t" + Room + " Selected ! ");
                } else {
                    return false;
                }
            }

            //Two and Three-seater types of rooms not available :
            if (num1 != 0 && num2 == 0 && num3 == 0) {
                System.out.println("\t\tSelect the type of the Room you want to Book : ");
                System.out.println("\t1. One Seater Room " + "(" + ppn1 + "rs per night)");
                System.out.println("\t2. Exit Booking");
                System.out.print("Only One Seater Rooms are available in this Hotel.\n\t\tEnter Room Selection : ");
                R_type = input.nextInt();
                while (!(R_type >= 1 && R_type <= 2)) {
                    System.out.print("\tInvalid Selection !\n\tPlease enter a valid room selection : ");
                    R_type = input.nextInt();
                }
                if (R_type == 1) {
                    Room = "One Seater Room";
                    System.out.println("\n\t" + Room + " Selected ! ");
                } else {
                    return false;
                }
            }

            //-----------------------------------------------------------------//

            //Check-in and Check-out date :

            //Check-in date :
            System.out.println("\tEnter Check-In Date : ");

            //Month :
            System.out.print("\t\tCheck-In Month : ");
            month = input.nextInt();
            while (!(month >= LocalDate.now().getMonthValue())) {
                System.out.print("\t(Invalid Month Number !)\n\t\tEnter Month Number : ");
                month = input.nextInt();
            }

            //day :
            if (month == LocalDate.now().getMonthValue()) {
                System.out.print("\t\tCheck-In Day : ");
                day = input.nextInt();
                while (!((day > LocalDate.now().getDayOfMonth()) && (day <= (daysPerMonth[LocalDate.now().getMonthValue()])))) {
                    System.out.print("\t(Invalid Day Number !)\n\t\tEnter valid day number : ");
                    day = input.nextInt();
                }
            } else if (month > LocalDate.now().getMonthValue()) {
                System.out.print("\t\tCheck-In Day : ");
                day = input.nextInt();
                while ((day <= 0) || day > daysPerMonth[month]) {
                    System.out.print("\t(Invalid Day Number !)\n\t\tEnter valid day number : ");
                    day = input.nextInt();
                }
            }

            //setting check-in date
            check_in = LocalDate.of(LocalDate.now().getYear(), month, day);

            //-----------------------------------------------------------------//

            //Check-out Date :

            System.out.println("\tEnter Check-Out Date : ");
            //month :
            System.out.print("\t\tCheck-Out Month : ");
            month_out = input.nextInt();
            while (!(month_out >= check_in.getMonthValue())) {
                System.out.print("\tInvalid Month Number !\n\t\tEnter valid month number : ");
                month_out = input.nextInt();
            }

            //day ;
            if (month_out == check_in.getMonthValue()) {
                System.out.print("\t\tCheck-Out Day : ");
                day_out = input.nextInt();
                while (!(day_out > check_in.getDayOfMonth())) {
                    System.out.print("\tInvalid Day Number !\n\tEnter valid day number : ");
                    day_out = input.nextInt();
                }
            } else if (month_out > check_in.getMonthValue()) {
                System.out.print("\t\tCheck-Out Day : ");
                day_out = input.nextInt();
                while (!((day_out >= 0) && (day_out <= daysPerMonth[month_out]))) {
                    System.out.print("\tInvalid Day Number !\n\tEnter valid day number : ");
                    day_out = input.nextInt();
                }
            }

            //setting up check-out date :
            check_out = LocalDate.of(LocalDate.now().getYear(), month_out, day_out);

            //-----------------------------------------------------------------//
            //Payment Method

            System.out.println("\tSelect Payment Method : ");
            System.out.println("\t\t1. Cash");
            System.out.println("\t\t2. Credit Card");
            System.out.println("\t\t3. Debit Card");
            System.out.println("\t\t4. Exit Booking");
            System.out.print("Enter payment selection : ");
            p = input.nextInt();
            while (!(p >= 1 && p <= 3)) {
                System.out.print("\tInvalid Selection !\n\tPlease enter a valid payment selection : ");
                p = input.nextInt();
            }
            if (p == 1) {
                payment = "Cash";
            } else if (p == 2) {
                payment = "Credit Card";
            } else if (p == 3) {
                payment = "Debit Card";
            } else {
                return false;
            }

            //-----------------------------------------------------------------//

            // Booking days  :

            num_of_days = (int) ChronoUnit.DAYS.between(check_in, check_out);

            //-----------------------------------------------------------------//

            b_status = "Active" ;

            //-----------------------------------------------------------------//
            //Room_Id :
            p = 0 ;
            if(Room =="One Seater Room"){
                p = 1 ;
            } else if (Room =="Two Seater Room") {
                p = 1 ;
            } else if (Room =="Three Seater Room") {
                p = 3 ;
            }

            //-----------------------------------------------------------------//

            //price :
            if(p==1){
                price = num_of_days *ppn1 ;
            } else if (p == 2) {
                price = num_of_days *ppn2 ;
            } else if (p==3){
                price = num_of_days *ppn3 ;
            }

            //-----------------------------------------------------------------//

            PreparedStatement roomid = connection.prepareStatement("call hotelmanagementsystem.Get_Room_Ids_Of_Hotel(?,?);");
            roomid.setInt(1, h_id);
            roomid.setInt(2,p);
            ResultSet ids = roomid.executeQuery();
            while (ids.next()) {
                room_id_s.add(ids.getInt("Room_Id"));
            }

            p = 0 ;
            for(int ID_room : room_id_s ){

                PreparedStatement bookingcheck = connection.prepareStatement("select hotelmanagementsystem.Booking_Check(?,?,?) as Result ;");
                bookingcheck.setInt(1, h_id);
                bookingcheck.setInt(2, ID_room);
                ResultSet answer = bookingcheck.executeQuery();
                while (answer.next()) {
                    p = answer.getInt("Result") ;
                }
                if(p==1){
                    continue;
                }
                else if(p==0){
                    room_id = ID_room ;
                    break ;
                }
            }

            if(room_id == 0){
                System.out.println("We are Sorry , No "+Room+" available at this Check-in in this Hotel !");
                return false ;
            }
            else if (room_id!=0){

                PreparedStatement insert1 = connection.prepareStatement("Insert into booking_details (Check_in , Check_in , Booking_Status , Payment_Method ) values (?,?,?,?);");
                insert1.setDate(1, Date.valueOf(check_in));
                insert1.setDate(2, Date.valueOf(check_out));
                insert1.setString(3, b_status);
                insert1.setString(4, payment);
                insert1.executeUpdate();

                //-----------------------------------------------------------------//
                 //bookingId ;

                // Retrieve the last inserted ID :
                ResultSet generatedKeys = insert1.getGeneratedKeys();
                if (generatedKeys.next()) {
                     bookingId = generatedKeys.getInt(1);
                }

                PreparedStatement insert2  = connection.prepareStatement("Insert into booking_day (Booking_Id , No_of_Days) values (?,?)");
                insert2.setInt(1,bookingId);
                insert2.setInt(2,num_of_days);
                insert2.executeUpdate();

                PreparedStatement insert3 = connection.prepareStatement("Insert into booking (Booking_Id , Hotel_Id , Room_Id , User_Id ) values (?,?,?,?) ; ");
                insert3.setInt(1,bookingId);
                insert3.setInt(2,h_id);
                insert3.setInt(3,room_id);
                insert3.setInt(4,this.get_CustomerId());
                insert3.executeUpdate();

                My_Bookings.add(new Booking(bookingId,check_in,check_out,b_status,h_id,room_id,num_of_days,price,this.get_CustomerId()));
            }
        }
        else {
            System.out.print("\t\t(Invalid Hotel ID !)\n\t\tHotel Not Found !\n\t\tEnter Hotel ID : ");
            h_id = input.nextInt();
            Book_A_Room(h_id);
        }
        return true;
    }



        // Method to display all bookings :
        public void display_all_bookings() throws SQLException {

        System.out.println("\t----------Viewing All Booking----------");
            for(Booking mybookings : My_Bookings){
                String name = null, address = null;

                PreparedStatement hoteldata = connection.prepareStatement("SELECT Hotel_Name , Address FROM hotelmanagementsystem.hotel_display where Hotel_Id = ?;");
                hoteldata.setInt(1,mybookings.get_HotelId());
                ResultSet hoteldetails = hoteldata.executeQuery();
                while (hoteldetails.next()) {
                    name = hoteldetails.getString("Hotel_Name") ;
                    address = hoteldetails.getString("Address") ;
                }

                System.out.println("----------------------------------------------------------");
                System.out.println("\t\tBooking ID : "+mybookings.get_BookingId());
                System.out.println("Hotel Name : "+name);
                System.out.println("Hotel Address : "+address);
                System.out.println("Room ID : "+mybookings.get_RoomId());
                System.out.println("Check-In Date: "+mybookings.get_CheckIn().toString());
                System.out.println("Check-Out Date : "+mybookings.get_CheckOut().toString());
                System.out.println("Price : "+mybookings.get_Booking_price());
                System.out.println("-----------------------------------------------------------");
            }

        }



       public void Print_Slip(int booking_Id) throws SQLException {

        for(Booking mybookings : My_Bookings) {
            if (mybookings.get_BookingId()==booking_Id){
                String name = null, c_policy = null, address = null, o_name = null, o_gender = null, o_phone = null, o_email = null;
                int o_age, o_id;
                PreparedStatement ownerdata = connection.prepareStatement("Call hotelmanagementsystem.Hotel_Owner_Data();");
                ownerdata.setInt(1, mybookings.get_HotelId());
                ResultSet ownerdetails = ownerdata.executeQuery();
                while (ownerdetails.next()) {
                    o_name = ownerdetails.getString("Name");
                    o_gender = ownerdetails.getString("Gender");
                    o_phone = ownerdetails.getString("Phone_Number");
                    o_email = ownerdetails.getString("Email");
                    o_id = ownerdetails.getInt("Owner_Id");
                }

                PreparedStatement hoteldata = connection.prepareStatement("SELECT Hotel_Name , Address ,Cancellation_Policy FROM hotelmanagementsystem.hotel_display where Hotel_Id = ?;");
                hoteldata.setInt(1, mybookings.get_HotelId());
                ResultSet hoteldetails = hoteldata.executeQuery();
                while (hoteldetails.next()) {
                    name = hoteldetails.getString("Hotel_Name");
                    address = hoteldetails.getString("Address");
                    c_policy = hoteldetails.getString("Cancellation_Policy");
                }

                //printing data :

                System.out.println("----------------------------------------------------------");
                System.out.println("  ");
                System.out.println("\t\t\tBooking ID : " + mybookings.get_BookingId());
                System.out.println("\t\tHotel Information");
                System.out.println("\tHotel Name : " + name);
                System.out.println("\tHotel Address : " + address);
                System.out.println("\tCancellation Policy : " + c_policy);
                System.out.println("  ");
                System.out.println("----------------------------------");
                System.out.println("  ");
                System.out.println("\t\tBooking Details : ");
                System.out.println("\tRoom ID : " + mybookings.get_RoomId());
                System.out.println("\tCheck-In Date: " + mybookings.get_CheckIn().toString());
                System.out.println("\tCheck-Out Date : " + mybookings.get_CheckOut().toString());
                System.out.println("\tDays of Stay : " + mybookings.get_Booking_days());
                System.out.println("  ");
                System.out.println("----------------------------------");
                System.out.println("  ");
                System.out.println("\t\tHotel Owner Details : ");
                System.out.println("\tName : " + o_name);
                System.out.println("\tGender : " + o_gender);
                System.out.println("\tPhone : " + o_phone);
                System.out.println("\tEmail : " + o_email);
                System.out.println("  ");
                System.out.println("-----------------------------------------------------------");

            }

        }

       }

}

