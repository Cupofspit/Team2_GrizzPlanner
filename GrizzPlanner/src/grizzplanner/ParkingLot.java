/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grizzplanner;

/**
 *
 * @author 15866
 */
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

