import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Login_System {
    private int Loggedin_Id ;
    private boolean Logged_Status = false ;
    private String Loggedin_Type ;
    private String username ;
    private String password ;

    //Constructor :
    public Login_System() throws SQLException {
        Test_Credentials();

    }

    //Methods to test credentials :
    public void Test_Credentials() throws SQLException {
        Scanner input = new Scanner(System.in);
        Connection connection = DatabaseConnection.getConnection();
        int op, loggedin_id = 0;
        System.out.println("\t Use System as : ");
        System.out.println("\t\t1. Admin");
        System.out.println("\t\t2. Customer");
        System.out.println("----------------------------------");
        System.out.print("\tPlease Enter your choice : ");
        op = input.nextInt();
        System.out.println("----------------------------------");
        String name = null , gender = null ,phoneno = null, email = null , address = null , username , password , usertype = null;
        switch (op) {
            case 1:
                System.out.println("----------------------------------");
                System.out.println("\tAdmin Log-In ! ");
                System.out.println("----------------------------------");
                usertype = "Admin";
                //Admin Processing :
                set_LoggedinType(usertype);
                input.nextLine();
                System.out.print("\n\tUsername : ");
                username = input.nextLine();
                if(username.isEmpty()){
                    System.out.println("\t(Username can't be empty !)\n\tUsername : ");
                    username = input.nextLine();
                }
                System.out.print("\n\tPassword : ");
                password = input.nextLine();
                if(password.isEmpty()){
                    System.out.println("\t(Password can't be empty !)\n\tPassword : ");
                    password = input.nextLine();
                }
                System.out.println("----------------------------------");
                PreparedStatement stat0 = connection.prepareStatement("SELECT hotelmanagementsystem.User_Search(?,?,?) as User_Id ;");
                stat0.setString(1, username);
                stat0.setString(2, password);
                stat0.setString(3, get_LoggedinType());
                ResultSet rs0 = stat0.executeQuery();
                while (rs0.next()) {
                    loggedin_id = rs0.getInt("User_Id");
                }
                if (loggedin_id != 0) {
                    set_Loggedin_Id(loggedin_id);
                    set_Logged_Status(true);
                    PreparedStatement stat5 = connection.prepareStatement("SELECT Name , Gender , Phone_number , Email , Address , Username , Password FROM hotelmanagementsystem.user WHERE User_Id = ? ");
                    stat5.setInt(1, this.get_Loggedin_Id());
                    ResultSet rs5 = stat5.executeQuery();
                    while (rs5.next()) {
                        name = rs5.getString(1);
                        gender = rs5.getString(2);
                        phoneno = rs5.getString(3);
                        email = rs5.getString(4);
                        address = rs5.getString(5);
                        username = rs5.getString(6);
                        password = rs5.getString(7);
                    }
                    Admin Logged_In_Admin = new Admin(name , gender , phoneno , email , address , username , password , usertype , loggedin_id);
                    Admin_Controller adminController = new Admin_Controller(Logged_In_Admin);
                    System.out.println("----------------------------------");
                    System.out.println("\tAdmin Controller Created ! ");
                    System.out.println("----------------------------------");
                } else {
                    set_Logged_Status(false);
                }
                break;


            case 2:
                //Customer Processing :
                usertype = "Customer";
                int op1;
                set_LoggedinType(usertype);
                System.out.println("\t\t1. Login");
                System.out.println("\t\t2. Sign-Up");
                System.out.println("----------------------------------");
                System.out.print("Please Enter your choice : ");
                op1 = input.nextInt();
                System.out.println("----------------------------------");
                while (!(op1 == 2 || op1 == 1)) {
                    System.out.println("\tInvalid Choice !");
                    System.out.println("----------------------------------");
                    System.out.println("\t\t1. Login");
                    System.out.println("\t\t2. Sign-Up");
                    System.out.println("----------------------------------");
                    System.out.print("Please Enter your choice : ");
                    op1 = input.nextInt();
                    System.out.println("----------------------------------");
                }

                //Login Processing :
                if (op1 == 1) {
                    System.out.println("----------------------------------");
                    System.out.println("Logging You In !");
                    System.out.println("----------------------------------");
                    input.nextLine();
                    System.out.println("----------------------------------");
                    System.out.print("\n\tUsername : ");
                    username = input.nextLine();
                    System.out.print("\n\tPassword : ");
                    password = input.nextLine();
                    System.out.println("----------------------------------");
                    PreparedStatement stat1 = connection.prepareStatement("SELECT hotelmanagementsystem.User_Search(?,?,?) as User_Id ;");
                    stat1.setString(1, username);
                    stat1.setString(2, password);
                    stat1.setString(3, get_LoggedinType());
                    ResultSet rs1 = stat1.executeQuery();
                    while (rs1.next()) {
                        loggedin_id = rs1.getInt("User_Id");
                    }
                    if (loggedin_id != 0) {
                        set_Loggedin_Id(loggedin_id);
                        set_Logged_Status(true);
                        PreparedStatement stat2 = connection.prepareStatement("SELECT Name , Gender , Phone_number , Email , Address , Username , Password FROM hotelmanagementsystem.user WHERE User_Id = ? ");
                        stat2.setInt(1, this.get_Loggedin_Id());
                        ResultSet rs2 = stat2.executeQuery();
                        while (rs2.next()) {
                            name = rs2.getString(1);
                            gender = rs2.getString(2);
                            phoneno = rs2.getString(3);
                            email = rs2.getString(4);
                            address = rs2.getString(5);
                            username = rs2.getString(6);
                            password = rs2.getString(7);
                        }
                        System.out.println("-------------------------------------------------");
                        Customer Logged_In_Customer = new Customer(name , gender , phoneno , email , address , username , password , usertype , loggedin_id);
                        Customer_Controller customerController = new Customer_Controller(Logged_In_Customer);
                        System.out.println("\t\t---Customer Controller Created !--- ");
                        System.out.println("-------------------------------------------------");
                    }
                    else {
                        set_Logged_Status(false);
                    }
                    break;
                }

                //Sign-up Processing :
                else{
                    System.out.println("----------------------------------");
                    System.out.println("\t\t-----Signing you up !-----");
                    System.out.println("----------------------------------");
                    System.out.println("\n\tEnter your Information : ");
                    input.nextLine();
                    System.out.print("\t\tName : ");
                    name = input.nextLine() ;
                    while(name.isEmpty()){
                        System.out.print("\t(Name can't be empty !) \n\t\tEnter Name : ");
                        name = input.nextLine() ;
                    }

                    System.out.print("\t\tGender (Male / Female): ");
                    gender = input.nextLine() ;
                    while(gender.isEmpty()){
                        System.out.print("\t(Gender can't be empty !) \n\t\tEnter Gender : ");
                        gender = input.nextLine() ;
                    }
                    if((gender=="M") || (gender == "m") || (gender == "male")){
                        gender = "Male";
                    } else if ((gender=="F") || (gender == "f") || (gender == "female")){
                        gender = "Female";
                    }
                    while( (gender=="Male") && (gender=="Female") ){
                        System.out.print("\t(Invalid Gender !)\n\t\tEnter Gender : ");
                        gender = input.nextLine();
                        if( (gender=="M") || (gender == "m") || (gender == "male") ){
                            gender = "Male";
                        }
                        else if ((gender=="F") || (gender == "f") || (gender == "female")){
                            gender = "Female";
                        }

                    }
                    System.out.print("\t\tPhone Number : ");
                    phoneno = input.nextLine() ;
                    while(phoneno.isEmpty()){
                        System.out.print("\t(Phone Number can't be empty !) \n\t\tEnter Phone Number : ");
                        phoneno = input.nextLine() ;
                    }
                    while(phoneno.length()<10){
                        System.out.print("\t(Phone Number can't be too short !) \n\t\tEnter Phone Number : ");
                        phoneno = input.nextLine() ;
                    }
                    System.out.print("\t\tEmail : ");
                    email = input.nextLine() ;
                    while(email.isEmpty()){
                        System.out.print("\t(Email can't be null !) \n\t\tEnter Email : ");
                        phoneno = input.nextLine() ;
                    }
                    System.out.print("\t\tAddress : ");
                    address = input.nextLine() ;
                    while(address.isEmpty()){
                        System.out.print("\t( Address can't be empty ! ) \n\t\tEnter Address : ");
                        address = input.nextLine() ;
                    }
                    System.out.print("\t\tUsername : ");
                    username = input.nextLine() ;
                    while(username.isEmpty()){
                        System.out.print("( Username can't be empty !(At least 5 characters)) \n\t\tEnter Username : ");
                        username = input.nextLine() ;
                    }
                    while(username.length()<5){
                        System.out.print("\t(Username is too short !(At least 5 characters))\n\t\tEnter Username : ");
                        username = input.nextLine() ;
                    }
                    System.out.print("\t\tPassword : ");
                    password = input.nextLine() ;
                    while(password.isEmpty()){
                        System.out.print("\t(Password can't be empty !(At least 5 characters)) \n\t\tEnter Password : ");
                        password = input.nextLine() ;
                    }
                    while(password.length()<5){
                        System.out.print("\t(Password is too short !(At least 5 characters))\n\t\tEnter Password : ");
                        password = input.nextLine() ;
                    }
                    System.out.println("-------------------------------------------------");
                    Customer Logged_In_Customer = new Customer(name , gender , phoneno , email , address , username , password , usertype);
                    Customer_Controller customerController = new Customer_Controller(Logged_In_Customer);
                    System.out.println("\t\t---Customer Controller Created !--- ");
                    System.out.println("-------------------------------------------------");
                    Logged_In_Customer.add_Customer_in_SQL(Logged_In_Customer);
                    PreparedStatement stat4 = connection.prepareStatement("SELECT User_Id FROM hotelmanagementsystem.user WHERE username = ? ");
                    stat4.setString(1, Logged_In_Customer.get_Username());
                    ResultSet rs4 = stat4.executeQuery();
                    while (rs4.next()) {
                        Logged_In_Customer.set_CustomerId(rs4.getInt("User_Id"));
                    }
                    set_Logged_Status(true);
                }


        }

    }


                                  //Getters and Setters  :
    //LoggedIn_Id :
    public int get_Loggedin_Id() {
        return this.Loggedin_Id;
    }
    public void set_Loggedin_Id(int loggedin_Id) {
        this.Loggedin_Id = loggedin_Id;
    }

    //LoggedIn status :
    public boolean get_Logged_Status() {
        return this.Logged_Status;
    }
    public void set_Logged_Status(boolean logged_Status) {
        this.Logged_Status = logged_Status;
    }

    //LoggedIn_Type :
    public String get_LoggedinType() {
        return this.Loggedin_Type;
    }
    public void set_LoggedinType(String loggedin_Type) {
        this.Loggedin_Type = loggedin_Type;
    }

    //Username :
    public String get_Username() {
        return this.username;
    }
    public void set_Username(String username) {
        this.username = username;
    }

    //Password :
    public String get_Password() {
        return this.password;
    }
    public void set_Password(String password) {
        this.password = password;
    }

}
