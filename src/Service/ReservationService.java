package Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;


import Model.Customer;
import Model.IRoom;
import Model.Reservation;

public class ReservationService {
    private static ReservationService reservationService;
    public Collection <Reservation> reservations = new ArrayList<>();
    public Collection <IRoom> roomlist = new ArrayList<>();
    private ReservationService() {}

    public static ReservationService getInstance(){
        if (reservationService == null){
            reservationService = new ReservationService();
        }
        return reservationService;
    }   
    
    public void addRoom(IRoom room){
        roomlist.add(room);
    }

    public IRoom getARoom(String roomID) {
        for (IRoom room : roomlist) {
            if (roomID.equals(room.getRoomNumber())) {
                return room;
            }
        }
        return null;
    }

    //find the rooms which are free based on the 2 dates you receive as the argument
    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        Collection <IRoom> freerooms = new HashSet<>();
        System.out.println(roomlist);
        if (reservations.size() == 0){
            return roomlist;
        }else {
            
            for(Reservation res : reservations) {
                for (IRoom rom : roomlist){
                    if ((res.getRoom().getRoomNumber()).equals(rom.getRoomNumber())){
                        if ((res.getCheckInDate().before(checkInDate)) && (res.getCheckOutDate().before(checkOutDate))){
                            freerooms.add(rom);
                        }
                        if ((res.getCheckInDate().after(checkInDate)) && (res.getCheckOutDate().after(checkOutDate))){
                            freerooms.add(rom);
                        }
                    }else {
                        freerooms.add(rom);
                    }
                }
            }
        }
        
/*             for(Reservation res : reservations) {
                for (IRoom rom : roomlist) {
                    if ((rom.getRoomNumber().equals(res.room.getRoomNumber()))
                            && ((checkInDate.before(res.getCheckInDate()) && checkOutDate.before(res.getCheckInDate()))
                                || (checkInDate.after(res.getCheckOutDate()) && checkOutDate.after(res.getCheckOutDate())))
                        || (!res.room.getRoomNumber().contains(rom.getRoomNumber()))) {
                            freerooms.add(rom);

                    } else if (rom.getRoomNumber().equals(res.room.getRoomNumber())) {
                        freerooms.remove(rom);
                    }
                }
            } */
        System.out.println(freerooms.size());
        return freerooms;
    }
  
    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate){
        Reservation reservedRooms = new Reservation(customer,room,checkInDate, checkOutDate);
        // make sure no duplicates
        reservations.add(reservedRooms); 
        System.out.println("This room has been reserved.");
        return reservedRooms;
    }

    //This function will print all the reservations by a particular customer. 
    public Collection<Reservation> getCustomerReservation(Customer customer){
        Collection<Reservation> Allrooms = new HashSet<>();
        for (Reservation reservation : reservations) {
            if((reservation.getCustomer()).equals(customer)){
                Allrooms.add(reservation);
            }
        }
        return Allrooms;
    }

    // get all rooms in the system
    public Collection<IRoom> getRooms(){
        return roomlist;
    }

    //This function will print all the reservations made in the application
    public void printAllReseverations(){
        if (reservations.size()!=0){
            for (Reservation reservation : reservations){
                System.out.println(reservation );
            }
        }else{
            System.out.println("No reservation was found");
        }
    }
}
