import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import API.AdminResource;
import Model.Customer;
import Model.IRoom;
import Model.Room;
import Model.RoomType;

public class AdminMenu {

    private static AdminResource adminResource = AdminResource.getInstance();

    public void start() {
        boolean keepRunning = true;
        Scanner scanner = new Scanner(System.in);
        while (keepRunning) {
            try {
                printMenu();
                int selection = Integer.parseInt(scanner.next());
                if (selection == 1) {
                    showCustomers();
                } else if (selection == 2) {
                    showRooms();
                } else if (selection == 3) {
                    showRervations();
                } else if (selection == 4) {
                    addARoom();
                } else if (selection == 5) {
                    System.out.println("YOU ARE GOING BACK TO THE MAIN MENU. GOODBYE");
                    MainMenu mainMenu = new MainMenu();
                    mainMenu.start();
                    keepRunning = false;
                }
            } catch (Exception e) {
                System.out.println("THERE IS AN ERROR... \n CALCULATING............. \n MISTAKE FOUND, YOU MUST HAVE DONE SOMETHING,  FIX IT FAST BEFORE  YOUR PC EXPLODES THEN IMPLODES");
            }
        }
    }

    public static void showCustomers(){
        Collection<Customer> customers = adminResource.getAllCustomer();
        if (customers.size()!=0){
            for (Customer customer : customers) {
                System.out.println(customer);
                printMenu();
            }
        } else{
            System.out.println("No cucstomer was found");
            printMenu();
        }
    }

       public static void showRooms(){
        Collection <IRoom> rooms = adminResource.getAllRooms();
        if (rooms.size()!=0){
            for (IRoom room : rooms) {
                System.out.println(room);
            }
            printMenu();
        } else{
            System.out.println("No room was found");
            printMenu();
        }    
    }

    public static void showRervations(){
        adminResource.displayAllReservations();
        printMenu();
    }

    private static void addARoom(){
        Scanner input = new Scanner(System.in);
        AdminResource adminResource = AdminResource.getInstance();
        String addRoom = "y";
        RoomType roomType = null;
        List<IRoom> rooms = new ArrayList<>();
        while (addRoom.equals("y")){ 
            System.out.println("Enter room number:");
            String roomNumber = input.next();

            System.out.println("Enter room price: ");        
            Double roomprice = input.nextDouble();
            System.out.println("Enter room type: 1 - Single bed, 2 - double bed");

            int type = input.nextInt();

            if (type == 1){
                roomType = RoomType.SINGLE;
            }else if (type == 2){
                roomType = RoomType.DOUBLE;
            }else{
                System.out.println("Invalid input. Please enter the room type");
            }
            Room room = new Room (roomNumber, roomprice, roomType);            
            rooms.add(room); 
            addRoom="";           
            System.out.println ("Would you like to add another room? y/n");
            addRoom = input.next();
            if ((addRoom.equals("y")==false) && (addRoom.equals("n")==false)){
                while (addRoom.equals("y")==false && addRoom.equals("n")==false){
                    System.out.println ("Please enter y (yes) or n (no)");
                    addRoom = input.nextLine();
                }
            }else{
                continue;
            }
        } 
        
        if (addRoom.equals("n")){
            adminResource.addRoom(rooms);
            printMenu();
        }
    }
    private static void printMenu() {
        System.out.print("\nAdmin Menu\n" +
                "--------------------------------------------\n" +
                "1. See all Customers\n" +
                "2. See all Rooms\n" +
                "3. See all Reservations\n" +
                "4. Add a Room\n" +
                "5. Back to Main Menu\n" +
                "--------------------------------------------\n" +
                "Please select a number for the menu option:\n");
    }
}
