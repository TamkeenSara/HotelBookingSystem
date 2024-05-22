import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Admin_Controller implements Admin_Functions {
    Scanner input = new Scanner(System.in);
    Connection connection = DatabaseConnection.getConnection();
    Admin admin ;
    Admin_Controller(String Name , String Gender , String phoneno ,String email , String Address , String Username , String Password , String UserType) throws SQLException {
        admin = new Admin(Name , Gender , phoneno , email ,Address ,Username ,Password ,UserType) ;
    }
    Admin_Controller(Admin obj) throws SQLException {
        int op = 0 , h_id , ad_id ;
        String place , City , name  ;
        this.admin = obj ;
        do {
            System.out.println("\n\tAdmin Functionalities : ");
            System.out.println("\t\t1. Register Hotel ");
            System.out.println("\t\t2. Delete Hotel ");
            System.out.println("\t\t3. Display Hotel Details by Hotel ID ");
            System.out.println("\t\t4. Delete Admin ");
            System.out.println("\t\t5. Add Admin ");
            System.out.println("\t\t6. Display Personal Information ");
            System.out.println("\t\t7. Display All Hotels ");
            System.out.println("\t\t8. Search Hotels by Place ");
            System.out.println("\t\t9. Search Hotels by Name ");
            System.out.println("\t\t10. Search Hotels by City ");
            System.out.println("\t\t11. Exit");
            System.out.println("Enter the Operation you want to perform : ");
            op = input.nextInt();

            while ((op != 1) && (op != 2) && (op != 3) && (op != 4) && (op != 5) && (op != 6) && (op != 7) && (op != 8) && (op != 9) && (op != 10)) {
                System.out.println("\tInvalid Operation Number !\n\tPlease enter valid Operation Number (1-10) : ");
                op = input.nextInt();
            }

            if(op==11){
                System.out.println("\tExciting.......!\n\tGoodBYE...!");
                break ;
            }

            switch (op) {
                case 1:
                    this.Register_Hotel();
                    break;

                case 2:
                    System.out.println("Enter the Hotel ID you want to DELETE : ");
                    h_id = input.nextInt();
                    while (h_id <= 0) {
                        System.out.println("\tInvalid Hotel ID !\n\tPlease Enter a valid Hotel ID : ");
                        h_id = input.nextInt();
                    }
                    this.Delete_Hotel(h_id);
                    break;

                case 3:
                    System.out.println("Enter the Hotel ID you want to SEE DETAILS of : ");
                    h_id = input.nextInt();
                    while (h_id <= 0) {
                        System.out.println("\tInvalid Hotel ID !\n\tPlease Enter a valid Hotel ID : ");
                        h_id = input.nextInt();
                    }
                    this.Display_Hotel_Details(h_id);
                    break;

                case 4:
                    System.out.println("Enter the Admin ID you want to DELETE : ");
                    ad_id = input.nextInt();
                    while (ad_id <= 0) {
                        System.out.println("\tInvalid Admin ID !\n\tPlease Enter a valid Admin ID : ");
                        ad_id = input.nextInt();
                    }
                    this.Delete_Admin(ad_id);
                    break;

                case 5:
                    this.Add_Admin();
                    break;

                case 6:
                    this.Display_Personal_Info(admin.get_AdminId());
                    break;

                case 7:
                    this.Display_All_Hotels();
                    break;

                case 8:
                    System.out.println("Enter the place you want to SEARCH HOTELS of : ");
                    place = input.nextLine();
                    this.Display_All_Hotels_By_Place(place);
                    break;

                case 9:
                    System.out.println("Enter the Name of Hotel you want to SEARCH HOTELS of : ");
                    name = input.nextLine();
                    this.Search_Hotel_By_Name(name);
                    break;

                case 10:
                    System.out.println("Enter the city you want to SEARCH HOTELS of : ");
                    City = input.nextLine();
                    this.Display_All_Hotels_By_City(City);
                    break;
            }
            System.out.println("\n\n");
        }while(op!=11);


    }

                                          //Methods :

    @Override
    public void Register_Hotel(){
        System.out.println("\t\t\tRegistering Hotel : ");
        try {
            admin.register_Hotel();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void Delete_Hotel(int h_id){
        System.out.println("\t\t\tDeleting Hotel : ");
        try {
            admin.delete_Hotel(h_id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void Display_Hotel_Details(int h_Id) {
        System.out.println("\t\t\tDisplaying Hotel Details : ");
        try {
            admin.display_hotel_details(h_Id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void Delete_Admin(int admin_Id) {
        System.out.println("\t\t\tDeleting Admin : ");
        try {
            admin.delete_Admin(admin_Id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void Add_Admin(){
        System.out.println("\t\t\tAdding Admin : ");
        try {
            admin.add_Admin();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void Display_Personal_Info(int admin_Id){
        System.out.println("\t\t\tDisplaying Personal Info : ");
        try {
            admin.display_personal_info();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void Display_All_Hotels(){
        System.out.println("\t\t\tDisplaying All Hotels : ");
        try {
            admin.display_all_Hotel();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void Display_All_Hotels_By_Place(String p){
        System.out.println("\t\t\tDisplaying All Hotels by Location : ");
        try {
            admin.display_all_Hotel_by_place(p);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void Search_Hotel_By_Name(String h_name){
        System.out.println("\t\t\tSearching Hotel by Name : ");
        try {
            admin.search_Hotel(h_name);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void Display_All_Hotels_By_City(String city){
        System.out.println("\t\t\tDisplaying all Hotels of City : ");
        try {
            admin.display_hotels_by_City(city);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
