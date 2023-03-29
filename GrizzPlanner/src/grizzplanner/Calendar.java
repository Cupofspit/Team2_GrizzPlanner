/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grizzplanner;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 *
 * @author 15866
 */
public class Calendar {
    private List<Event> events;
    
    public Calendar() {
        events = new ArrayList<>();
    }
    
    public String viewEvent(int eventId) {
        for (Event event : events) {
            if (event.getId() == eventId) {
                System.out.println("Event ID: " + event.getId());
                System.out.println("Event Name: " + event.getName());
                System.out.println("Event Date: " + event.getDate());
                System.out.println("Event Description: " + event.getDescription());
                String output = "Event ID: "+ event.getId()+"\nEvent Name: "+event.getName()+"\nEvent Date: "+event.getDate()+"\nEvent Description: "+event.getDescription()+"\n";
                return output;
            }
        }
        System.out.println("Event not found!");
        return "Event not found!";
    }
    
    public void deleteEvent(int eventId) {
        for (int i = 0; i < events.size(); i++) {
            Event event = events.get(i);
            if (event.getId() == eventId) {
                events.remove(i);
                System.out.println("Event deleted!");
                return;
            }
        }
        System.out.println("Event not found!");
    }
    
    public void updateEvent(int eventId, String name, String date, String description) {
        for (int i = 0; i < events.size(); i++) {
            Event event = events.get(i);
            if (event.getId() == eventId) {
                event.setName(name);
                event.setDate(date);
                event.setDescription(description);
                System.out.println("Event updated!");
                return;
            }
        }
        System.out.println("Event not found!");
    }
    
    public void addEvent() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Event Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Event Date (yyyy-mm-dd): ");
        String date = scanner.nextLine();
        System.out.print("Enter Event Description: ");
        String description = scanner.nextLine();
        int id = events.size() + 1;
        Event event = new Event(id, name, date, description);
        events.add(event);
        System.out.println("Event added!");
    }
    
    public List<Event> getEvents() {
        return events;
    }
    
    /*public static void main(String[] args) {
        Calendar calendar = new Calendar();
        calendar.addEvent();
        calendar.addEvent();
        calendar.viewEvent(1);
        calendar.updateEvent(1, "Updated Event Name", "2023-03-28", "Updated Event Description");
        calendar.viewEvent(1);
        calendar.deleteEvent(2);
    }*/
}
