package grizzplanner;

import java.util.ArrayList;
import java.util.List;

public class Traffic {
    private List<ParkingLot> parkingLots;

    public Traffic() {
        // Create parking lots and add them to the list
        ParkingLot lot1 = new ParkingLot("Lot 1", 100, 80);
        ParkingLot lot2 = new ParkingLot("Lot 2", 150, 120);
        ParkingLot lot3 = new ParkingLot("Lot 3", 200, 180);
        ParkingLot lot4 = new ParkingLot("Lot 4", 250, 200);

        parkingLots = new ArrayList<>();
        parkingLots.add(lot1);
        parkingLots.add(lot2);
        parkingLots.add(lot3);
        parkingLots.add(lot4);
    }

    public List<ParkingLot> getParkingLots() {
        return parkingLots;
    }
}
