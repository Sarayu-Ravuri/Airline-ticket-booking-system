package airline.passengers;

public class BusinessClassPassenger extends Passenger {
    public BusinessClassPassenger(String id, String name, String flightNo, String seatNo) {
        super(id, "Priority Passenger: " + name, flightNo, seatNo);
    }
}
