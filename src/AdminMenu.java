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

    public static void start() {
        String line = "";
        Scanner scanner = new Scanner(System.in);
        printMenu();
        do {
            line = scanner.nextLine();
            if (line.length() == 1) {
                switch (line.charAt(0)) {
                    case '1':
                        showCustomers();
                        break;
                    case '2':
                        showRooms();
                        break;
                    case '3':
                        showRervations();
                        break;
                    case '4':
                        addARoom();
                        break;
                    case '5':
                        MainMenu.printMainMenu();
                        break;
                }
            } else {
                System.out.println("Error: Invalid action\n");
            }
        } while (line.charAt(0) != '5' || line.length() != 1);
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


    public static void BacktoMainMenu(){
        MainMenu.start();
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
