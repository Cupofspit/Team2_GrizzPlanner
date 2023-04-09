package Tests;
import grizzplanner.Traffic;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;

public class TrafficTest {

    @Test
    public void testFindDriveTime() throws IOException {
        Traffic traffic = new Traffic(500, "Woodward Ave", "Detroit", "MI");
        int expectedTime = 32; // expected drive time in minutes
        int actualTime = traffic.findDriveTime();
        assertEquals(expectedTime, actualTime, 5);
    }

    @Test
    public void testLocateXCoords() throws IOException {
        Traffic traffic = new Traffic(500, "Woodward Ave", "Detroit", "MI");
        String expectedX = "42.33026"; // expected x coordinate
        String actualX = traffic.locateXCoords(traffic.getStart());
        assertEquals(expectedX, actualX);
    }

    @Test
    public void testLocateYCoords() throws IOException {
        Traffic traffic = new Traffic(500, "Woodward Ave", "Detroit", "MI");
        String expectedY = "-83.04445"; // expected y coordinate
        String actualY = traffic.locateYCoords(traffic.getStart());
        assertEquals(expectedY, actualY);
    }
}