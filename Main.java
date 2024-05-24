import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class Main {

    public static void main(String args[]) throws SQLException {

        System.out.println("\n\t\t----------Welcome to Hotel Reservation System-----------");
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

