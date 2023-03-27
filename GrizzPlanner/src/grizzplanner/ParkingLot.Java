package grizzplanner;

public class ParkingLot {
    private String name;
    private int capacity;
    private int availability;

    public ParkingLot(String name, int capacity, int availability) {
        this.name = name;
        this.capacity = capacity;
        this.availability = availability;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }
}
