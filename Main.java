import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.Scanner;

public class Main {

    public static void main(String args[]) throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("\t\tWelcome to Hotel Reservation System !\n");
        Login_System system = new Login_System();
        while(!(system.get_Logged_Status())){
            System.out.println("Invalid Login Credentials !\n");
            system.Test_Credentials();
        }
        if(system.get_LoggedinType()=="Admin"){
            System.out.println("Admin Successfully Logged In !");
            }
        else{
                System.out.println("Customer Successfully Logged In");
            }

    }



}

