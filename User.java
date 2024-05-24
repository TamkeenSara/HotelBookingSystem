import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public abstract class User {
                                       //Attributes :
    private String Name;
    private String Gender;
    private String Phone_Number;
    private String Email;
    private String Address;
    private String Username;
    private String Password;
    private String UserType; // "admin" or "customer"
    public static int Num_Users = 0;
    Scanner input = new Scanner(System.in);
    Connection connection = DatabaseConnection.getConnection();

                                            //Constructor :
    User(String Name, String Gender, String phoneno, String email, String Address, String Username, String Password, String UserType) throws SQLException {
        this.Name = Name;
        this.Gender = Gender;
        this.Email = email;
        this.Phone_Number = phoneno;
        this.Address = Address;
        this.Username = Username;
        this.Password = Password;
        this.UserType = UserType;
        Num_Users++;
    }

                                         // Getters and Setters :
    //Name :
    public String get_Name() {
        return this.Name;
    }

    public void set_Name(String name) {
        this.Name = name;
    }

    //Gender :
    public String get_Gender() {
        return this.Gender;
    }

    public void set_Gender(String gender) {
        this.Gender = gender;
    }

    //Address :
    public String get_Address() {
        return this.Address;
    }

    public void set_Address(String address) {
        this.Address = address;
    }

    //Username :
    public String get_Username() {
        return this.Username;
    }

    public void set_Username(String username) {
        this.Username = username;
    }

    //Password :
    public String get_Password() {
        return this.Password;
    }

    public void set_Password(String password) {
        this.Password = password;
    }

    //User_type :
    public String get_UserType() {
        return UserType;
    }

    public void set_UserType(String userType) {
        this.UserType = userType;
    }

    //Phone_Number :
    public String get_PhoneNumber() {
        return this.Phone_Number;
    }

    public void set_PhoneNumber(String phone_Number) {
        this.Phone_Number = phone_Number;
    }

    //Email :
    public String get_Email() {
        return this.Email;
    }

    public void set_Email(String email) {
        this.Email = email;
    }



                                           //Methods :

    //Method to display personal information :
    public void display_personal_info() throws SQLException {
        int user_id = 0;
        PreparedStatement stat = connection.prepareStatement("Select User_Id from hotelmanagementsystem.user where Username = ? ;");
        stat.setString(1, this.get_Username());
        ResultSet rs = stat.executeQuery();
        while (rs.next()) {
            user_id = rs.getInt("User_Id");
        }
        System.out.println("\n\t\tYour personal details are : ");
        System.out.println("\t\t\tUsername : " + this.get_Username());
        System.out.println("\t\t\tPassword : " + this.get_Password());
        System.out.println("\t\t\tUser-Type : " + this.get_UserType());
        System.out.println("\t\t\tUser-ID : " + user_id);
        System.out.println("\t\t\tName : " + this.Name);
        System.out.println("\t\t\tGender : " + this.Gender);
        System.out.println("\t\t\tEmail : " + this.Email);
        System.out.println("\t\t\tPhone Number : " + this.Phone_Number);
        System.out.println("\t\t\tAddress : " + this.Address);

    }


    //Method to display all hotels :
    public void display_all_Hotel() throws SQLException {
        int h_id, h_totalrooms;
        double minimum_ppn;
        String h_name, h_fulladdress, h_cpolicy, owner_contact;
        PreparedStatement stat = connection.prepareStatement("SELECT * FROM hotelmanagementsystem.hotel_display;");
        ResultSet rs = stat.executeQuery();
        System.out.println("\t\t\tDisplaying all Hotels");
        if(rs.next()){
            do{
                h_id = rs.getInt(1);
                h_name = rs.getString(2);
                h_fulladdress = rs.getString(3);
                h_totalrooms = rs.getInt(4);
                h_cpolicy = rs.getString(5);
                owner_contact = rs.getString(6);
                minimum_ppn = rs.getDouble(7);

                System.out.println("\n");
                System.out.println("---------------------------------------------------------------------------");
                System.out.println("\t\t\t" + h_id + ". " + h_name);
                System.out.println("\tHotel_ID : " + h_id);
                System.out.println("\tHotel_Address : " + h_fulladdress);
                System.out.println("\tTotal Rooms : " + h_totalrooms);
                System.out.println("\tHotel Cancellation Policy : " + h_cpolicy);
                System.out.println("\tOwner Contact Number: " + owner_contact);
                System.out.println("\t\t\t\tPrice : " + minimum_ppn);
                System.out.println("  ");
                System.out.println("---------------------------------------------------------------------------");
            } while (rs.next());
        }
        else{
            System.out.println("\tNo Hotels Listed !");
        }

    }



    //Method to display all hotels by place :
    public void display_all_Hotel_by_place(String place) throws SQLException {
        int h_id, h_totalrooms;
        double minimum_ppn;
        String h_name, h_fulladdress, h_cpolicy, owner_contact;
        PreparedStatement stat = connection.prepareStatement("Call hotelmanagementsystem.Get_Hotels_By_Place(?);");
        stat.setString(1, place);
        ResultSet rs1 = stat.executeQuery();
        if(rs1.next()){
            do {
                h_id = rs1.getInt(1);
                h_name = rs1.getString(2);
                h_fulladdress = rs1.getString(3);
                h_totalrooms = rs1.getInt(4);
                h_cpolicy = rs1.getString(5);
                owner_contact = rs1.getString(6);
                minimum_ppn = rs1.getDouble(7);

                System.out.println("\n");
                System.out.println("----------------------------------------------------------------------");
                System.out.println("\t\t\t" + h_id + ". " + h_name);
                System.out.println("\tHotel_ID : " + h_id);
                System.out.println("\tHotel_Address : " + h_fulladdress);
                System.out.println("\tTotal Rooms : " + h_totalrooms);
                System.out.println("\tHotel Cancellation Policy : " + h_cpolicy);
                System.out.println("\tOwner Contact Number: " + owner_contact);
                System.out.println("\t\t\t\tPrice : " + minimum_ppn);
                System.out.println("  ");
                System.out.println("----------------------------------------------------------------------");
            }while (rs1.next()) ;
        }
        else{
            System.out.println("No Hotels Found !");
        }

    }



    //Method to Search Hotel by Name :
    public void search_Hotel(String h_name) throws SQLException {
        int h_id, h_totalrooms;
        double minimum_ppn;
        String h_fulladdress, h_cpolicy, owner_contact;
        PreparedStatement stat = connection.prepareStatement("Call hotelmanagementsystem.Get_Hotels_By_Name(?);");
        stat.setString(1, h_name);
        ResultSet rs1 = stat.executeQuery();
        System.out.println("\t\t\tDisplaying Hotel");
        if(rs1.next()){
           do {
            h_id = rs1.getInt(1);
            h_name = rs1.getString(2);
            h_fulladdress = rs1.getString(3);
            h_totalrooms = rs1.getInt(4);
            h_cpolicy = rs1.getString(5);
            owner_contact = rs1.getString(6);
            minimum_ppn = rs1.getDouble(7);

            System.out.println("\n");
            System.out.println("----------------------------------------------------------------------");
            System.out.println("\t\t\t" + h_id + ". " + h_name);
            System.out.println("\tHotel_ID : " + h_id);
            System.out.println("\tHotel_Address : " + h_fulladdress);
            System.out.println("\tTotal Rooms : " + h_totalrooms);
            System.out.println("\tHotel Cancellation Policy : " + h_cpolicy);
            System.out.println("\tOwner Contact Number: " + owner_contact);
            System.out.println("\t\t\t\tPrice : " + minimum_ppn);
            System.out.println("  ");
            System.out.println("----------------------------------------------------------------------");

            } while (rs1.next());
    }
    else{
        System.out.println("\tNo Hotels Found ! ");
    }
}




    // Method to display all hotels in a specific city :
    protected void display_hotels_by_City(String city) throws SQLException {
        int h_id, h_totalrooms;
        double minimum_ppn;
        String h_name , h_fulladdress, h_cpolicy, owner_contact;
        PreparedStatement stat = connection.prepareStatement("Call hotelmanagementsystem.Get_Hotels_By_City(?);");
        stat.setString(1,city);
        ResultSet rs = stat.executeQuery();
        if(rs.next()){
            do{
                h_id = rs.getInt(1);
                h_name = rs.getString(2);
                h_fulladdress = rs.getString(3)+rs.getInt(4)+rs.getString(5);
                h_totalrooms = rs.getInt(6);
                h_cpolicy = rs.getString(7);
                owner_contact = rs.getString(8);
                minimum_ppn = rs.getDouble(9);

                System.out.println("\n");
                System.out.println("----------------------------------------------------------------------");
                System.out.println("\t\t\t" + h_id + ". " + h_name);
                System.out.println("\tHotel_ID : " + h_id);
                System.out.println("\tHotel_Address : " + h_fulladdress);
                System.out.println("\tTotal Rooms : " + h_totalrooms);
                System.out.println("\tHotel Cancellation Policy : " + h_cpolicy);
                System.out.println("\tOwner Contact Number: " + owner_contact);
                System.out.println("\t\t\t\tPrice : " + minimum_ppn);
                System.out.println("  ");
                System.out.println("----------------------------------------------------------------------");

            }while(rs.next()) ;
        }
        else{
            System.out.println("\tNo Hotel Found ! ");
        }

    }





    // Method to display hotel details :
    protected void display_hotel_details (int h_id) throws SQLException {
        int h_totalrooms , num1 = 0, num2 = 0  , num3 = 0 ;
        double ppn1 =0 , ppn2 = 0, ppn3 = 0  ;
        String h_name , h_address , h_city , h_country , h_cpolicy,O_name, O_phone , O_email , h_cancel ;

        //Getting number of rooms :

        PreparedStatement stat1 = connection.prepareStatement("Select hotelmanagementsystem.One_Seater_Room(?) as Result ;");
        stat1.setInt(1,h_id);
        ResultSet rs1 = stat1.executeQuery();
        while(rs1.next()){
            num1 = rs1.getInt("Result") ;
        }

        PreparedStatement stat2 = connection.prepareStatement("Select hotelmanagementsystem.Two_Seater_Room(?) as Result ;");
        stat2.setInt(1,h_id);
        ResultSet rs2 = stat2.executeQuery();
        while(rs2.next()){
            num2 = rs2.getInt("Result") ;
        }

        PreparedStatement stat3 = connection.prepareStatement("Select hotelmanagementsystem.Three_Seater_Room(?) as Result ;");
        stat3.setInt(1,h_id);
        ResultSet rs3 = stat3.executeQuery();
        while(rs3.next()){
            num3 = rs3.getInt("Result") ;
        }

        //Getting pricing of rooms :

        PreparedStatement stat4 = connection.prepareStatement("SELECT Price_per_night as Result FROM hotelmanagementsystem.pricing\n" +
                "where Hotel_Id = ? and Type_Id = ? ;");
        stat4.setInt(1,h_id);
        stat4.setInt(2,1);
        ResultSet rs4 = stat4.executeQuery();
        while(rs4.next()){
            ppn1 = rs4.getInt("Result") ;
        }

        PreparedStatement stat5 = connection.prepareStatement("SELECT Price_per_night as Result FROM hotelmanagementsystem.pricing where Hotel_Id = ? and Type_Id = ? ;");
        stat5.setInt(1,h_id);
        stat5.setInt(2,2);
        ResultSet rs5 = stat5.executeQuery();
        while(rs5.next()){
            ppn2 = rs5.getInt("Result") ;
        }

        PreparedStatement stat6 = connection.prepareStatement("SELECT Price_per_night as Result FROM hotelmanagementsystem.pricing where Hotel_Id = ? and Type_Id = ? ;");
        stat6.setInt(1,h_id);
        stat6.setInt(2,3);
        ResultSet rs6 = stat6.executeQuery();
        while(rs6.next()){
            ppn3 = rs6.getInt("Result") ;
        }

        //Getting other details of Hotel :

        PreparedStatement stat = connection.prepareStatement("SELECT h.Hotel_Id , h.Name as Hotel_Name , h.Total_Rooms , l.Address , c.Name as City_Name, co.Country_Name as Country , ho.Name as Owner_Name, ho.Phone_Number as Owner_Phone_Number , ho.Email as Owner_Eamil , cp.Description as Cancellation_Policy   \n" +
                "FROM hotel h \n" +
                "join location l On h.Location_Id = l.Location_Id \n" +
                "join city c on c.City_Id = l.City_Id \n" +
                "join country co on co.Country_Id = l.Country_Id \n" +
                "join hotel_owner ho on ho.Owner_Id = h.Owner_Id\n" +
                "join hotel_cancellation_policy hcp on hcp.Hotel_Id = h.Hotel_Id\n" +
                "join cancellation_policy cp on cp.Cancellation_Id = hcp.Cancellation_Id \n");
        ResultSet rs = stat.executeQuery();
        if(rs.next()){
            do{
                h_id = rs.getInt(1);
                h_name = rs.getString(2);
                h_totalrooms = rs.getInt(3);
                h_address = rs.getString(4);
                h_city = rs.getString(5);
                h_country= rs.getString(6);
                O_name =rs.getString(7);
                O_phone = rs.getString(8);
                O_email = rs.getString(9);
                h_cancel = rs.getString(10);

                System.out.println("\n");
                System.out.println("----------------------------------------------------------------------");
                System.out.println("\t\t\t" + h_id + ". " + h_name);
                System.out.println("\tHotel_ID : " + h_id);
                System.out.println("\tHotel_Address : " + h_address);
                System.out.println("\tRegion : " + h_city+" , "+h_country);
                System.out.println("\tTotal Rooms : " + h_totalrooms);
                System.out.println("\tOne Seater Rooms : " + num1+" ( Price : "+ppn1+" rs)");
                System.out.println("\tTwo Seater Rooms : " + num2+" ( Price : "+ppn2+" rs)");
                System.out.println("\tThree Seater Rooms : " + num3+" ( Price : "+ppn3+" rs)");
                System.out.println("\tHotel Cancellation Policy : " + h_cancel);
                System.out.println("\tOwner Name : " + O_name);
                System.out.println("\tOwner Contact Number : " + O_phone);
                System.out.println("\tOwner Email : " + O_email);
                System.out.println("  ");
                System.out.println("----------------------------------------------------------------------");
            }while(rs.next()) ;
        }
        else{
            System.out.println("\tNo Hotel Found ! ");
        }

    }





}
