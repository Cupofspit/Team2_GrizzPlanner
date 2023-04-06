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
import java.util.Scanner;

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
    
    public void reportAccuracy() {
        try (Scanner input = new Scanner(System.in)) {
            System.out.println("Is the parking lot availability status accurate? (Type 'confirm' or 'deny')");
            String response = input.next().toLowerCase();
            
            if (response.equals("confirm")) {
                // Do nothing, availability status is accurate
            } else if (response.equals("deny")) {
                System.out.println("Please enter the new availability status:");
                int newAvailability = input.nextInt();
                setAvailability(newAvailability);
                System.out.println("Thank you for your feedback!");
            } else {
                System.out.println("Invalid response. Please type 'confirm' or 'deny'.");
                reportAccuracy(); // Recursive call to try again if user enters invalid response
            }
        }
    }
}


