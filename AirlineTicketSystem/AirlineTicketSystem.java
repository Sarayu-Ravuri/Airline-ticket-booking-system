package main;

import airline.passengers.*;
import airline.flights.*;
import airline.utils.*;
import java.util.ArrayList;
import java.util.Scanner;

public class AirlineTicketSystem {
    private static ArrayList<Passenger> passengers = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n1. Add Passenger\n2. Search Passenger by ID\n3. Search Passenger by Name\n4. Display Passengers\n5. Sort Passengers\n6. Cancel Booking\n7. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: addPassenger(); break;
                case 2: searchPassengerById(); break;
                case 3: searchPassengerByName(); break;
                case 4: displayPassengers(); break; // This will now display all details
                case 5: sortPassengers(); break;
                case 6: cancelBooking(); break;
                case 7: System.out.println("Exiting..."); return;
                default: System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void addPassenger() {
        System.out.print("Enter Passenger ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Flight No: ");
        String flightNo = scanner.nextLine();
        System.out.print("Enter Seat No: ");
        String seatNo = scanner.nextLine();
        System.out.print("Enter Class (1 for Economy, 2 for Business): ");
        int classType = scanner.nextInt();
        scanner.nextLine();

        Passenger passenger = (classType == 2) ? new BusinessClassPassenger(id, name, flightNo, seatNo) : new EconomyPassenger(id, name, flightNo, seatNo);
        passengers.add(passenger);
        System.out.println("Passenger added successfully!");
    }

    private static void searchPassengerById() {
        System.out.print("Enter Passenger ID: ");
        String id = scanner.nextLine();
        Passenger found = PassengerSorter.binarySearchById(passengers, id);
        System.out.println(found != null ? found : "Passenger not found.");
    }

    private static void searchPassengerByName() {
        System.out.print("Enter Name (partial match): ");
        String partialName = scanner.nextLine();
        boolean found = false;
        for (Passenger p : passengers) {
            if (p.nameContains(partialName)) {
                System.out.println(p);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No passengers found with that name.");
        }
    }

    private static void displayPassengers() {
        System.out.println("\nPassenger List:");
        for (Passenger p : passengers) {
            String reversedName = p.reverseName();
            System.out.println("ID: " + p.getPassengerID() + 
                               ", Name: " + p.getName() + 
                               " | Reversed Name: " + reversedName + 
                               ", Flight: " + p.getFlightNo() + 
                               ", Seat: " + p.getSeatNo() + 
                               ", Status: " + p.getBookingStatus());
        }
    }

    private static void sortPassengers() {
        PassengerSorter.sortPassengers(passengers);
        System.out.println("Passengers sorted by name.");
    }

    private static void cancelBooking() {
        System.out.print("Enter Passenger ID: ");
        String id = scanner.nextLine();
        for (Passenger p : passengers) {
            if (p.getPassengerID().equals(id)) {
                p.cancelBooking();
                System.out.println("Booking rescheduled for " + p.getName());
                return;
            }
        }
        System.out.println("Passenger not found.");
    }
}
