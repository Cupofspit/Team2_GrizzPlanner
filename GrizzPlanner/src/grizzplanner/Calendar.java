
package grizzplanner;

import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author 15866
 */
public class Calendar {
    private List<Event> events;
    
    public Calendar() throws IOException {
        events = new ArrayList<>();
//        try {
//            pull();
//        } catch (FileNotFoundException ex) {
//            System.out.println("Could not pull event data");;
//        }
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
    
    public void deleteEvent(int eventId) throws IOException {
        for (int i = 0; i < events.size(); i++) {
            Event event = events.get(i);
            if (event.getId() == eventId) {
                events.remove(i);
                System.out.println("Event deleted!");
                save();
                return;
            }
        }
        System.out.println("Event not found!");
    }
    
    public void updateEvent(int eventId, String name, String date, String description) throws IOException {
        for (int i = 0; i < events.size(); i++) {
            Event event = events.get(i);
            if (event.getId() == eventId) {
                event.setName(name);
                event.setDate(date);
                event.setDescription(description);
                System.out.println("Event updated!");
                save();
                return;
            }
        }
        System.out.println("Event not found!");
    }
    
    public void addEvent() throws IOException {
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
        save();
    }
    
    private void save() throws IOException{
        String output = "./save.txt";
        File file = new  File(output);
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        for (Event temp: getEvents()){
            writer.write(temp.toString() + "\n");
        }
        writer.close();
    }
    
    
    public List<Event> getEvents() {
        return events;
    }
    
//    private void pull() throws FileNotFoundException, IOException{
//        String input = "./save.txt";
//        File file = new File(input);
//        InputStream inputStream = new FileInputStream(file);
//        InputStreamReader isReader = new InputStreamReader(inputStream);
//        BufferedReader reader = new BufferedReader(isReader);
//        
//        String str;
//        int id = 0;
//        String name = "";
//        String date = "";
//        String description = "";
//        
//        Event add;
//        
//        while((str = reader.readLine())!= null){
//           String[] split1 = str.split(",");
//            for(String temp: split1){
////                System.out.println(temp);
//                String[] split2 = temp.split("=");   
//                switch(split2[0].trim()){
//                    case "id" :
//                        id = Integer.parseInt(split2[1]);
//                        break;
//                    case "name" :
//                        name = split2[1];
//                        break;
//                    case "date" :
//                        date = split2[1];
//                        break;
//                    case "description" :
//                        description = split2[1];
//                        break;
//                }
//            }
//            add = new Event(id, name, date, description);
//            events.add(add);
////               System.out.println(str);
//        }
//    }
//    public static void main(String[] args) throws IOException {
//        Calendar calendar = new Calendar();
//        calendar.addEvent();
//        calendar.addEvent();
//        calendar.viewEvent(1);
//        calendar.updateEvent(1, "Updated Event Name", "2023-03-28", "Updated Event Description");
//        calendar.viewEvent(1);
//        calendar.deleteEvent(2);
//    }

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
