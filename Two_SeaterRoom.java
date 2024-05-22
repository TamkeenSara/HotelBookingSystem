public class Two_SeaterRoom extends Room {
    private double Price_Per_Night ;
    private final String Room_Type = "Two-Seater Room" ;

                             //Constuctor  :
    Two_SeaterRoom(int room_id, String RoomNumber, int hotel_id , double p_p_night ){
        super(room_id, RoomNumber, hotel_id);
        this.Price_Per_Night = p_p_night ;
    }

    Two_SeaterRoom(){
        super();
    }


                           //Getter and Setters :

    //Price per night :
    public double get_PricePerNight() {
        return this.Price_Per_Night;
    }
    public void set_PricePerNight(double price_Per_Night) {
        this.Price_Per_Night = price_Per_Night;
    }


}
