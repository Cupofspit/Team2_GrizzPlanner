package grizzplanner;

public class ParkingLot {


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