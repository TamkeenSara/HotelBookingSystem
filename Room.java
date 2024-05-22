public class Room {
    private int Room_Id;
    private String Room_Number ;
    private int Hotel_Id ;

                                          //Constructor :
    Room(int room_id , String RoomNumber , int hotel_id ){
        this.Room_Id = room_id ;
        this.Room_Number = RoomNumber ;
        this.Hotel_Id = hotel_id ;
    }
    Room(){

    }
                                      // Getters and Setters
    // Room_Id :
    public int get_RoomId() {
        return this.Room_Id;
    }
    public void set_RoomId(int roomId) {
        this.Room_Id = roomId;
    }

    //Room_Number :
    public String get_RoomNumber() {
        return this.Room_Number;
    }
    public void set_RoomNumber(String roomnumber) {
        this.Room_Number = roomnumber;
    }

    // Hotel_Id :
    public int get_HotelId() {
        return Hotel_Id;
    }
    public void set_HotelId(int hotelId) {
        this.Hotel_Id = hotelId;
    }


}
