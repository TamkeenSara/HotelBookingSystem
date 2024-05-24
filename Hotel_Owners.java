public class Hotel_Owners {
    private int Owner_Id ;
    private String Name ;
    private int Age ;
    private String Gender ;
    private String Phone_Number ;
    private String Email ;

    public Hotel_Owners(String name, int age, String gender, String phone_Number, String email) {
        this.Name = name;
        this.Age = age;
        this.Gender = gender;
        this.Phone_Number = phone_Number;
        this.Email = email;
    }

    //Name ;
    public String get_Name() {
        return this.Name;
    }
    public void set_Name(String name) {
        this.Name = name;
    }

    //Age ;
    public int get_Age() {
        return this.Age;
    }
    public void set_Age(int age) {
        this.Age = age;
    }

    //Gender ;
    public String get_Gender() {
        return this.Gender;
    }
    public void set_Gender(String gender) {
        this.Gender = gender;
    }

    //Phone number  ;
    public String get_PhoneNumber() {
        return this.Phone_Number;
    }
    public void set_PhoneNumber(String phone_Number) {
        this.Phone_Number = phone_Number;
    }

    //Email ;
    public String get_Email() {
        return this.Email;
    }
    public void set_Email(String email) {
        this.Email = email;
    }

    //Owner Id :
    public int get_OwnerId() {
        return this.Owner_Id;
    }
    public void set_OwnerId(int owner_Id) {
        this.Owner_Id = owner_Id;
    }


}
