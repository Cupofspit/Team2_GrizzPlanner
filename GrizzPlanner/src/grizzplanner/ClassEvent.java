package grizzplanner;

import java.time.LocalDate;
import java.time.LocalTime;


public class ClassEvent {

    private String CL_Name;
    private String CL_Location;
    private LocalTime CL_StartTime;
    private LocalTime CL_EndTime;
    private LocalDate CL_Date;
    private int CL_ID;


    public ClassEvent(String CL_Name, LocalTime CL_StartTime, LocalTime CL_EndTime, LocalDate CL_Date, int CL_ID){
        this.CL_Name = CL_Name;
        //this.CL_Location = CL_Location;
        this.CL_StartTime = CL_StartTime;
        this.CL_EndTime = CL_EndTime;
        this.CL_Date = CL_Date;
        this.CL_ID = CL_ID;
    }

    public void setClassEventName(String name){
        CL_Name = name;
    }

    public String getClassEventName(){
        return CL_Name;
    }

 /*   public void setClassEventLocation(String loc){
        CL_Location = loc;
    }*/

/*    public String getClassEventLocation(){
        return CL_Location;
    }*/

    public void setClassEventStartTime(LocalTime time){
        CL_StartTime = time;
    }

    public LocalTime getClassEventStartTime(){
        return CL_StartTime;
    }

    public void setClassEventEndTime(LocalTime time){
        CL_EndTime = time;
    }

    public LocalTime getClassEventEndTime(){
        return CL_EndTime;
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

    public String toString(){
        String str = "Event: "+getClassEventName()+"    From: "+getClassEventStartTime()+" to "+getClassEventEndTime()+" on "+getClassEventDate()+" with ID "+getClassEventID();
        return str;
    }


}
