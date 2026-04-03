package airline;

import airline.passengers.*;
import airline.flights.*;
import airline.utils.*;


import java.util.*;

interface FrequentFlyerProgram {
    void earnPoints();
}

class Passenger {
    protected int passengerID;
    protected String name;
    protected String flightNo;
    protected String seatNo;

    public Passenger(int passengerID, String name, String flightNo, String seatNo) {
        this.passengerID = passengerID;
        this.name = name.toUpperCase();
        this.flightNo = flightNo;
        this.seatNo = seatNo;
    }

    public String getName() { return name; }
    public int getPassengerID() { return passengerID; }

    public void displayInfo() {
        System.out.println("Passenger ID: " + passengerID);
        System.out.println("Name: " + name);
        System.out.println("Flight No: " + flightNo);
        System.out.println("Seat No: " + seatNo);
    }
}

class EconomyPassenger extends Passenger {
    public EconomyPassenger(int passengerID, String name, String flightNo, String seatNo) {
        super(passengerID, name, flightNo, seatNo);
    }
}

class BusinessClassPassenger extends Passenger implements FrequentFlyerProgram {
    public BusinessClassPassenger(int passengerID, String name, String flightNo, String seatNo) {
        super(passengerID, "Priority Passenger: " + name, flightNo, seatNo);
    }

    public void earnPoints() {
        System.out.println(name + " has earned frequent flyer points.");
    }
}

class FlightNotFoundException extends Exception {
    public FlightNotFoundException(String message) {
        super(message);
    }
}

class Airline {
    private static List<Passenger> passengers = new ArrayList<>();
    private static Vector<Passenger> canceledBookings = new Vector<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n1. Add Passenger\n2. Search Passenger\n3. Display Passengers\n4. Cancel Booking\n5. Sort Passengers\n6. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                addPassenger(scanner);
            } else if (choice == 2) {
                searchPassenger(scanner);
            } else if (choice == 3) {
                displayPassengers();
            } else if (choice == 4) {
                cancelBooking(scanner);
            } else if (choice == 5) {
                sortPassengers();
            } else if (choice == 6) {
                System.out.println("Exiting...");
                break;
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }
        scanner.close();
    }

    private static void addPassenger(Scanner scanner) {
        System.out.print("Enter Passenger ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Flight No: ");
        String flightNo = scanner.nextLine();

        System.out.print("Enter Seat No: ");
        String seatNo = scanner.nextLine();

        System.out.print("Enter Class (1 for Economy, 2 for Business): ");
        int classType = scanner.nextInt();

        try {
            if (seatNo.isEmpty()) {
                throw new FlightNotFoundException("Invalid seat selection!");
            }
            Passenger passenger;
            if (classType == 1) {
                passenger = new EconomyPassenger(id, name, flightNo, seatNo);
            } else {
                passenger = new BusinessClassPassenger(id, name, flightNo, seatNo);
            }
            passenger.name = new StringBuilder(passenger.name).toString();
            passengers.add(passenger);
            System.out.println("Passenger added successfully!");
        } catch (FlightNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void searchPassenger(Scanner scanner) {
        System.out.print("Enter Passenger ID to search: ");
        int id = scanner.nextInt();

        for (Passenger p : passengers) {
            if (p.getPassengerID() == id) {
                p.displayInfo();
                return;
            }
        }
        System.out.println("Passenger not found.");
    }

    private static void displayPassengers() {
        for (Passenger p : passengers) {
            p.displayInfo();
            System.out.println("-------------------");
        }
    }

    private static void cancelBooking(Scanner scanner) {
        System.out.print("Enter Passenger ID to cancel: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < passengers.size(); i++) {
            if (passengers.get(i).getPassengerID() == id) {
                Passenger p = passengers.remove(i);
                canceledBookings.add(p);
                System.out.println("Booking rescheduled successfully!");
                return;
            }
        }
        System.out.println("Passenger not found.");
    }

    private static void sortPassengers() {
        Collections.sort(passengers, new Comparator<Passenger>() {
            public int compare(Passenger p1, Passenger p2) {
                return p1.getName().compareTo(p2.getName());
            }
        }
        System.out.println("Passengers sorted successfully!");
    }
}