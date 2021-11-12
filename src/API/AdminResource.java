package API;

import Model.Customer;
import Model.IRoom;
import java.util.Collection;
import java.util.List;

import Service.CustomerService;
import Service.ReservationService;

public class AdminResource {
    private static AdminResource adminresource;

    private CustomerService customerService = CustomerService.getInstance();
    private ReservationService reservationService = ReservationService.getInstance();

    private AdminResource (){}

    public static AdminResource getInstance(){
        if (adminresource == null){
            adminresource = new AdminResource();
        }
        return adminresource;
    }   
    
    public Customer getCustomer(String email){
        return customerService.getCustomer(email);
    }
    // iterate over the rooms passed and use the reservationService variable to invoke its own addRoom method to add each room, 
    public void addRoom (List<IRoom> rooms){
        for (IRoom room: rooms){
            reservationService.addRoom(room);
        }
    }

    public Collection<IRoom> getAllRooms(){
        return reservationService.getRooms();
    }

    public Collection <Customer> getAllCustomer(){
        return customerService.getAllCustomers();
    }

    public void displayAllReservations(){
        reservationService.printAllReseverations();
    }
}
