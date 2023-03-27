package grizzplanner;

import java.time.LocalDate;
import java.time.LocalTime;


public class classEvent {

    private String CL_Name;
    private String CL_Location;
    private LocalTime CL_Time;
    private LocalDate CL_Date;
    private int CL_ID;


    public classEvent(String CL_Name, String CL_Location, LocalTime CL_Time, LocalDate CL_Date, int CL_ID){
        this.CL_Name = CL_Name;
        this.CL_Location = CL_Location;
        this.CL_Time = CL_Time;
        this.CL_Date = CL_Date;
        this.CL_ID = CL_ID;
    }

    public void setClassEventName(String name){
        CL_Name = name;
    }

    public String getClassEventName(){
        return CL_Name;
    }

    public void setClassEventLocation(String loc){
        CL_Location = loc;
    }

    public String getClassEventLocation(){
        return CL_Location;
    }

    public void setClassEventTime(LocalTime time){
        CL_Time = time;
    }

    public LocalTime getClassEventTime(){
        return CL_Time;
    }

    public void setClassEventDate(LocalDate date){
        CL_Date = date;
    }

    public LocalDate getClassEventDate(){
        return CL_Date;
    }

    public void setClassEventID(int ID){
        CL_ID = ID;
    }

    public int getClassEventID(){
        return CL_ID;
    }


}
