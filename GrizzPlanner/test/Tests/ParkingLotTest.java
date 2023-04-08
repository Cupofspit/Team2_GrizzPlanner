package Tests;
import static org.junit.Assert.*;

import grizzplanner.ParkingLot;
import org.junit.Test;

public class ParkingLotTest {

    @Test
    public void testGetName() {
        ParkingLot parkingLot = new ParkingLot("Lot A", 100, 50);
        assertEquals("Lot A", parkingLot.getName());
    }

    @Test
    public void testGetCapacity() {
        ParkingLot parkingLot = new ParkingLot("Lot A", 100, 50);
        assertEquals(100, parkingLot.getCapacity());
    }

    @Test
    public void testGetAvailability() {
        ParkingLot parkingLot = new ParkingLot("Lot A", 100, 50);
        assertEquals(50, parkingLot.getAvailability());
    }

    @Test
    public void testSetAvailability() {
        ParkingLot parkingLot = new ParkingLot("Lot A", 100, 50);
        parkingLot.setAvailability(40);
        assertEquals(40, parkingLot.getAvailability());
    }
}