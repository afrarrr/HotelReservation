package API;

import Service.CustomerService;
import Service.ReservationService;

import java.util.Date;
import java.util.Collection;

import Model.Customer;
import Model.IRoom;
import Model.Reservation;

public class HotelResource {

    private static HotelResource hotelresource;

    private CustomerService customerService = CustomerService.getInstance();
    private ReservationService reservationService = ReservationService.getInstance();

    private HotelResource(){}

    public static HotelResource getInstance(){
        if (hotelresource == null){
            hotelresource = new HotelResource();
        }
        return hotelresource;
    }   
    
    public Customer getCustomer (String email){
        return customerService.getCustomer(email);
    }

    public void createACustomer (String email, String firstName, String lastName){
        customerService.addCustomer(email, firstName, lastName);
    }

    public IRoom getRoom (String roomNumber){
        return reservationService.getARoom(roomNumber);
    }

    public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate){
        return reservationService.reserveARoom(getCustomer(customerEmail), room, checkInDate, checkOutDate);
    }

    public Collection <Reservation> getCustomerReservations (String customerEmail){
        Customer customer = getCustomer(customerEmail);
        if (customer != null){
            return reservationService.getCustomerReservation(customer);
        }
        return null;
    }

    public Collection <IRoom> findARoom (Date checkIn, Date checkOut){
        return reservationService.findRooms(checkIn, checkOut);
    }
     
}
