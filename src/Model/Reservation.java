package Model;

import java.util.Date;

public class Reservation {

    private Customer customer;
    public IRoom room;
    private Date checkInDate;
    private Date checkOutDate;

    public Reservation (Customer customer, IRoom room, Date checkInDate, Date checkOutDate){
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;     
    }

    public IRoom getRoom() {
        return this.room;
    }

    public Date getCheckInDate() {
        return this.checkInDate;
    }
    public Date getCheckOutDate() {
        return this.checkOutDate;
    }

    @Override
    public String toString(){
        return "Reservation Details: " +"\n" + this.customer.toString() + "\nRoom: " + this.room.toString() + "\nCheckIn Date: " + this.checkInDate + "\nCheckOut Date: " + this.checkOutDate;
    }

    public Customer getCustomer() {
        return this.customer;
    }



}
