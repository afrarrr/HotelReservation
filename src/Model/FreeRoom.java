package Model;

public class FreeRoom extends Room{
    // set the price for freeroom to be zero
    public FreeRoom (String RoomNumber, RoomType enumeration) {
        super(RoomNumber,0.0,enumeration);
    }

    @Override

    public String toString(){
        return "Free! Room number: "+ this.getRoomNumber() + " Room type: "+this. getRoomType();
    }
}
