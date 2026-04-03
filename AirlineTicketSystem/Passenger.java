package airline.passengers;

import java.util.Vector;

public class Passenger {
    protected String passengerID;
    protected String name;
    protected String flightNo;
    protected String seatNo;
    protected boolean isCancelled;
    private static Vector<String> cancelledBookings = new Vector<>();

    public Passenger(String id, String name, String flightNo, String seatNo) {
        this.passengerID = id;
        this.name = name;
        this.flightNo = flightNo;
        this.seatNo = seatNo;
        this.isCancelled = false;
    }

    public String getPassengerID() {
        return passengerID;
    }

    public String getName() {
        return name;
    }

    public String getFlightNo() {
        return flightNo;
    }

    public String getSeatNo() {
        return seatNo;
    }

    public String toUpperCaseName() {
        return name.toUpperCase();
    }

    public String toLowerCaseName() {
        return name.toLowerCase();
    }

    public String getFirstLastChar() {
        return "First: " + name.charAt(0) + ", Last: " + name.charAt(name.length() - 1);
    }

    public String reverseName() {
        return new StringBuffer(name).reverse().toString();
    }

    public void cancelBooking() {
        isCancelled = true;
        cancelledBookings.add(this.passengerID);
    }

    public boolean isCancelled() {
        return isCancelled;
    }

    public String getBookingStatus() {
        return isCancelled ? "rescheduled" : "active";
    }

    // New method to check if name contains a substring
    public boolean nameContains(String substring) {
        return name.toLowerCase().contains(substring.toLowerCase());
    }

    @Override
    public String toString() {
        return "ID: " + passengerID + ", Name: " + name + ", Flight: " + flightNo + ", Seat: " + seatNo + ", Status: " + getBookingStatus();
    }
}
