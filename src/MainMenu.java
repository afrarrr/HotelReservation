import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashSet;
import java.util.Scanner;

import API.AdminResource;
import API.HotelResource;
import Model.Customer;
import Model.IRoom;
import Model.Reservation;
import Service.CustomerService;
import Service.ReservationService;

public class MainMenu {
    
    public static HotelResource hotelResource;
    public static AdminResource adminResource;
    private static String Dateformat = "MM/dd/yyyy";
    public static CustomerService customerService = CustomerService.getInstance();
    public static ReservationService reservationService = ReservationService.getInstance();
    public static HotelResource hotelresource = HotelResource.getInstance();

    public void start() { 
        boolean keepRunning = true;
        Scanner scanner = new Scanner(System.in);
        while (keepRunning) {
            try {
                printMainMenu();
                int selection = Integer.parseInt(scanner.next());
                if (selection == 1) {
                    findAndReserveARoom();
                } else if (selection == 2) {
                    seeMyReservations();
                } else if (selection == 3) {
                    createAnAccount();
                } else if (selection == 4) {
                    System.out.println("ADMIN HAS BEEN CHOSEN!");
                    AdminMenu adminMenu = new AdminMenu();
                    adminMenu.start();
                } else if (selection == 5) {
                    System.out.println("YOU ARE GOING TO LEAVE THIS VERY COOL AND AWESOME APPLICATION NOW. GOODBYE");
                    keepRunning = false;
                }
            } catch (Exception ex) {
                System.out.println("THERE IS AN ERROR... \n CALCULATING............. \n MISTAKE FOUND, YOU MUST HAVE DONE SOMETHING,  FIX IT FAST BEFORE  YOUR PC EXPLODES THEN IMPLODES");
            }
        }

    }   

    public static void findAndReserveARoom() {
        Collection<IRoom> roomsOpenForReserve = new HashSet<>();
        Scanner scanner = new Scanner(System.in);
        String option = "";
        String account ="";
        System.out.println("Enter Check-In Date mm/dd/yyyy example 02/01/2020");
        Date checkIn = getInputDate(scanner);

        System.out.println("Enter Check-Out Date mm/dd/yyyy example 02/21/2020");
        Date checkOut = getInputDate(scanner);

        if (checkIn != null && checkOut != null) {
            roomsOpenForReserve = hotelresource.findARoom(checkIn, checkOut);  
            if (roomsOpenForReserve.size()!=0){
                    System.out.println("Would you like to book a room? y/n");
                    option = scanner.next();
                    if (option.equals("y")) {
                        System.out.println("Do you have an account with us? y/n");
                        account = scanner.next();
                        if (account.equals("y")) {
                            System.out.println("Enter Email format: name@domain.com");
                            String email = scanner.next();
                            Customer customer = hotelresource.getCustomer(email);
                            if (customer == null ){
                                System.out.println("Customer not found.\nYou may need to create a new account.");
                            }else{
                                System.out.println("What room number would you like to reserve?");
                                roomsOpenForReserve.forEach(System.out::println);
                                String roomNumber = scanner.next();
                                IRoom room = hotelresource.getRoom(roomNumber);
                                Reservation reservation = hotelresource.bookARoom(customer.getEmail(), room, checkIn, checkOut);
                                System.out.println("Reservation created successfully!");
                                System.out.println(reservation);                                    
                            }
                        } else if (account.equals("n")) {
                            System.out.println("Please create an account");
                            createAnAccount();
                        }
                        
                    }else if (option.equals("n")) {
                        return;
                    }
            } else {
                System.out.println("There is no room available");
 
            } 
        }
    }

    public static void createAnAccount(){
        Scanner input = new Scanner (System.in);
        System.out.println ("-------------------------------------------");
        System.out.println ("Enter email format: name@domain.com");
        String email = input.nextLine();
        System.out.println ("Enter your first name: ");
        String firstname = input.nextLine();
        System.out.println ("Enter your last name: ");
        String lastname = input.nextLine();   
        try{    
            hotelresource.createACustomer (email, firstname, lastname);
            System.out.println("Account created successfully!");
        }catch (IllegalArgumentException e){
            System.out.print("Please follow the right email format: name@domain.com");    
            createAnAccount();       
        }
    }

    private static Date getInputDate(Scanner scanner) {
        try {
            return new SimpleDateFormat(Dateformat).parse(scanner.nextLine());
        } catch (ParseException ex) {
            System.out.println("Error: Invalid date.");
        }
        return null;
    }

    private static void seeMyReservations() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter your email:");
        String email = input.nextLine();
        Collection<Reservation> reservations = hotelresource.getCustomerReservations(email);
        for (Reservation reservation : reservations) {
            System.out.println(reservation);
        }

    }

    public static void printMainMenu(){
        System.out.print("\nWelcome to the Hotel Reservation Application\n" +
                "--------------------------------------------\n" +
                "1. Find and reserve a room\n" +
                "2. See my reservations\n" +
                "3. Create an Account\n" +
                "4. Admin\n" +
                "5. Exit\n" +
                "--------------------------------------------\n" +
                "Please select a number for the menu option:\n");
    }
}
   

