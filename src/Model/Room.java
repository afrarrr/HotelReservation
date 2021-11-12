package Model;

import java.util.Objects;

public class Room implements IRoom {
    private String roomNumber;
    private Double price;
    private RoomType enumeration;

    public Room(String roomNumber, Double price, RoomType enumeration){
        this.roomNumber = roomNumber;
        this.price = price;
        this.enumeration = enumeration;   
    }

    public String getRoomNumber(){
        return this.roomNumber;
    }

    public Double getRoomPrice(){
        return this.price;
    }

    public RoomType getRoomType(){
        return this.enumeration;
    }

    public boolean isFree(){
        return this.price == 0;
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj){
            return true;
        }
        if (obj==null || obj.getClass() != this.getClass()){
            return false;
        }
        Room room = (Room) obj;
        return (room.roomNumber == this.roomNumber 
                && room.price == this.price 
                && room.enumeration == this.enumeration);
    }
    
    // why we need this method?
    @Override
    public String toString(){
        return "Room Number: " + this.roomNumber + " Price: $" + this.price + " Room Type: " + this.enumeration;
    }

    // why we need this one?
    @Override 
    public int hashCode() {
        return Objects.hash(roomNumber); 
    }
}
