package grizzplanner;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Event {
    
    private int id;
    private String name;
    private String date;
    private String description;
    
    public Event(int id, String name, String date, String description) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.description = description;
    }
    
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDate() {
        return date;
    }
    
    public void setDate(String date) {
        this.date = date;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
}