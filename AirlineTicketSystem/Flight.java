package airline.flights;

import java.util.Vector;

public class Flight {
    private String flightNo;
    private Vector<String> canceledBookings;

    public Flight(String flightNo) {
        this.flightNo = flightNo;
        this.canceledBookings = new Vector<>();
    }

    public void cancelBooking(String passengerID) {
        canceledBookings.add(passengerID);
    }
}

// Custom Exception
class FlightNotFoundException extends Exception {
    public FlightNotFoundException(String message) {
        super(message);
    }
}
