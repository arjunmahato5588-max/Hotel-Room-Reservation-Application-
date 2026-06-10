import java.util.ArrayList;
import java.util.Scanner;

class Customer {
    int customerId;
    String customerName;

    Customer(int customerId, String customerName) {
        this.customerId = customerId;
        this.customerName = customerName;
    }
}

class Room {
    int roomNumber;
    String roomType;
    int price;
    boolean booked;

    Room(int roomNumber, String roomType, int price) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.price = price;
        this.booked = false;
    }
}

public class Main {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Room> rooms = new ArrayList<>();
    static ArrayList<String> bookings = new ArrayList<>();

    public static void main(String[] args) {

        addRooms();

        while (true) {

            System.out.println("\n=======================================");
            System.out.println("     HOTEL RESERVATION SYSTEM");
            System.out.println("=======================================");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Book Room");
            System.out.println("3. View Bookings");
            System.out.println("4. Cancel Booking");
            System.out.println("5. Exit");
            System.out.print("Enter Choice: ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    displayRooms();
                    break;

                case 2:
                    bookRoom();
                    break;

                case 3:
                    viewBookings();
                    break;

                case 4:
                    cancelBooking();
                    break;

                case 5:
                    System.out.println("\nThank You For Using Our Hotel System.");
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }

    static void addRooms() {

        rooms.add(new Room(101, "Standard", 1000));
        rooms.add(new Room(102, "Standard", 1000));
        rooms.add(new Room(201, "Deluxe", 2000));
        rooms.add(new Room(202, "Deluxe", 2000));
        rooms.add(new Room(301, "Suite", 3500));
    }

    static void displayRooms() {

        System.out.println("\nAvailable Rooms");
        System.out.println("----------------------------------------------");
        System.out.println("Room\tType\t\tPrice\tStatus");

        for (Room room : rooms) {

            String status = room.booked ? "Booked" : "Available";

            System.out.println(room.roomNumber + "\t"
                    + room.roomType + "\t\t₹"
                    + room.price + "\t"
                    + status);
        }
    }

    static void bookRoom() {

        System.out.print("\nEnter Customer ID: ");
        int id = sc.nextInt();

        sc.nextLine();

        System.out.print("Enter Customer Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Room Number: ");
        int roomNo = sc.nextInt();

        for (Room room : rooms) {

            if (room.roomNumber == roomNo) {

                if (!room.booked) {

                    room.booked = true;

                    Customer customer = new Customer(id, name);

                    String booking = "Customer ID : " + customer.customerId +
                            "\nCustomer Name : " + customer.customerName +
                            "\nRoom Number : " + room.roomNumber +
                            "\nRoom Type : " + room.roomType +
                            "\nPrice : ₹" + room.price +
                            "\n----------------------------";

                    bookings.add(booking);

                    System.out.println("\nRoom Booked Successfully!");
                    return;

                } else {

                    System.out.println("\nRoom Already Booked!");
                    return;
                }
            }
        }

        System.out.println("\nInvalid Room Number!");
    }

    static void viewBookings() {

        if (bookings.isEmpty()) {

            System.out.println("\nNo Bookings Found.");
            return;
        }

        System.out.println("\n========= BOOKING DETAILS =========");

        for (String booking : bookings) {

            System.out.println(booking);
        }
    }

    static void cancelBooking() {

        System.out.print("\nEnter Room Number: ");
        int roomNo = sc.nextInt();

        for (Room room : rooms) {

            if (room.roomNumber == roomNo && room.booked) {

                room.booked = false;

                for (int i = 0; i < bookings.size(); i++) {

                    if (bookings.get(i).contains("Room Number : " + roomNo)) {

                        bookings.remove(i);
                        break;
                    }
                }

                System.out.println("\nBooking Cancelled Successfully!");
                return;
            }
        }

        System.out.println("\nBooking Not Found!");
    }
}