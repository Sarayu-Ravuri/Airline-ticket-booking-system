package airline.utils;

import airline.passengers.Passenger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class PassengerSorter {
    public static void sortPassengers(ArrayList<Passenger> passengers) {
        Collections.sort(passengers, Comparator.comparing(Passenger::getName));
    }

    public static Passenger binarySearchById(ArrayList<Passenger> passengers, String id) {
        int left = 0, right = passengers.size() - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            int comparison = passengers.get(mid).getPassengerID().compareTo(id);
            if (comparison == 0) return passengers.get(mid);
            if (comparison < 0) left = mid + 1;
            else right = mid - 1;
        }
        return null;
    }
}
