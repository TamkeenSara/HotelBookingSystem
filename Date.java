import java.time.LocalDate ;
public class Date{
    private int month;        // 1-12
    private int day;          // 1-31 based on month
    private int year;         // any year
    private static final int[] daysPerMonth =
            {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    //Constructor :
    Date(Date obj){
        this.day = obj.day ;
        this.month = obj.month ;
        this.year = obj.year ;
    }

    // Constructor : confirm proper value for month and day given the year
    public Date(int month, int day, int year) {
        // check if month in range
        if (month <= 0 || month > 12){
            throw new IllegalArgumentException(
                    "Month (" + month + ") must be in this range : 1-12 !");
        }

        // check if day in range for month
        if (day <= 0 ||
                (day > daysPerMonth[month] && !(month == 2 && day == 29))) {
            throw new IllegalArgumentException("day (" + day +
                    ") out-of-range for the specified month and year");
        }

        // check for leap year if month is 2 and day is 29
        if (month == 2 && day == 29 && !(year % 400 == 0 ||
                (year % 4 == 0 && year % 100 != 0))){
            throw new IllegalArgumentException("Day (" + day +
                    ") out-of-range for the specified Month and Year !");
        }

        this.month = month;
        this.day = day;
        this.year = year;

        System.out.printf("Date Object Constructor for Date %s%n",this);
    }


    //Getters and Setters :
    //Getters :
    public int get_Month(){
        return this.month;
    }
    public int get_Day(){
        return this.day;
    }
    public int get_Year(){
        return this.year;
    }

    //Setters :
    public void set_Month(int month){
        this.month = month ;
    }
    public void set_Day(int day){
        this.day = day ;
    }
    public void set_Year(int year){
        this.year = year;
    }


    //Method to get current Month Number :
    public static int get_CurrentMonth(){
        return (LocalDate.now().getMonthValue()) ;
    }

    //Method to get current Day Number :
    public static int get_CurrentDay(){
        return (LocalDate.now().getDayOfMonth()) ;
    }

    //Method to get current Year Number :
    public static int get_CurrentYear(){
        return (LocalDate.now().getYear()) ;
    }

    // return a String of the form month/day/year
    public String toString() {
        return String.format("%d/%d/%d", month, day, year);
    }

}