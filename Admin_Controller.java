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
            System.out.println("----------------------------------");
            System.out.println("\n\tAdmin Functionalities : ");
            System.out.println("----------------------------------");
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
            System.out.println("----------------------------------");
            System.out.print("Enter the Operation you want to perform : ");
            op = input.nextInt();
            System.out.println("----------------------------------");
            while(!(op>0 && op<=11)){
                System.out.print("\tInvalid Operation Number !\n\tPlease enter valid Operation Number (1-10) : ");
                op = input.nextInt();
            }

            if(op==11){
                System.out.println("----------------------------------");
                System.out.println("\tExiting.......!\n\tGoodBYE...!");
                System.out.println("----------------------------------");
                break ;
            }

            switch (op) {
                case 1:
                    System.out.println("----------------------------------");
                    this.Register_Hotel();
                    System.out.println("----------------------------------");
                    break;

                case 2:
                    System.out.println("----------------------------------");
                    System.out.print("Enter the Hotel ID you want to DELETE : ");
                    h_id = input.nextInt();
                    while (h_id <= 0) {
                        System.out.print("\tInvalid Hotel ID !\n\tPlease Enter a valid Hotel ID : ");
                        h_id = input.nextInt();
                    }
                    System.out.println("----------------------------------");
                    this.Delete_Hotel(h_id);
                    break;

                case 3:
                    System.out.println("\n----------------------------------");
                    System.out.print("Enter the Hotel ID you want to SEE DETAILS of : ");
                    h_id = input.nextInt();
                    while (h_id <= 0) {
                        System.out.print("\tInvalid Hotel ID !\n\tPlease Enter a valid Hotel ID : ");
                        h_id = input.nextInt();
                    }
                    System.out.println("----------------------------------");
                    this.Display_Hotel_Details(h_id);
                    break;

                case 4:
                    System.out.println("\n----------------------------------");
                    System.out.print("Enter the Admin ID you want to DELETE : ");
                    ad_id = input.nextInt();
                    while (ad_id <= 0) {
                        System.out.print("\tInvalid Admin ID !\n\tPlease Enter a valid Admin ID : ");
                        ad_id = input.nextInt();
                    }
                    System.out.println("----------------------------------");
                    this.Delete_Admin(ad_id);
                    break;

                case 5:
                    System.out.println("\n----------------------------------");
                    this.Add_Admin();
                    System.out.println("\n----------------------------------");
                    break;

                case 6:
                    System.out.println("\n----------------------------------");
                    this.Display_Personal_Info(admin.get_AdminId());
                    System.out.println("\n----------------------------------");
                    break;

                case 7:
                    System.out.println("\n----------------------------------");
                    this.Display_All_Hotels();
                    System.out.println("\n----------------------------------");
                    break;

                case 8:
                    System.out.println("\n----------------------------------");
                    System.out.print("Enter the place you want to SEARCH HOTELS of : ");
                    input.nextLine();
                    place = input.nextLine();
                    System.out.println("----------------------------------");
                    this.Display_All_Hotels_By_Place(place);
                    break;

                case 9:
                    System.out.println("\n----------------------------------");
                    System.out.print("Enter the Name of Hotel you want to SEARCH HOTELS of : ");
                    input.nextLine();
                    name = input.nextLine();
                    System.out.println("----------------------------------");
                    this.Search_Hotel_By_Name(name);
                    break;

                case 10:
                    System.out.println("\n----------------------------------");
                    System.out.print("Enter the city you want to SEARCH HOTELS of : ");
                    input.nextLine();
                    City = input.nextLine();
                    System.out.println("\n----------------------------------");
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
