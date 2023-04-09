package grizzplanner;

import java.time.LocalDate;
import java.time.LocalTime;


public class ClassEvent {

    private String CL_Name;
    private LocalDate CL_Date;
    //private String CL_Location;
    private LocalTime CL_StartTime;
    private LocalTime CL_EndTime;
    private int CL_ID;


    public ClassEvent(String CL_Name, LocalDate CL_Date, LocalTime CL_StartTime, LocalTime CL_EndTime, int CL_ID){
        this.CL_Name = CL_Name;
        //this.CL_Location = CL_Location;
        this.CL_StartTime = CL_StartTime;
        this.CL_EndTime = CL_EndTime;
        this.CL_Date = CL_Date;
        this.CL_ID = CL_ID;
    }

    public void setCL_Name(String name){
        CL_Name = name;
    }

    public String getCL_Name(){
        return CL_Name;
    }

    public void setCL_StartTime(LocalTime time){
        CL_StartTime = time;
    }

    public LocalTime getCL_StartTime(){
        return CL_StartTime;
    }

    public void setCL_EndTime(LocalTime time){
        CL_EndTime = time;
    }

    public LocalTime getCL_EndTime(){
        return CL_EndTime;
    }

    public LocalDate getCL_Date(){
        return CL_Date;
    }

    public void setCL_Date(LocalDate date){
        CL_Date = date;
    }

    public void setCL_ID(int ID){
        CL_ID = ID;
    }

    public int getCL_ID(){
        return CL_ID;
    }

    @Override
    public String toString() {
        return "CL_Name=" + CL_Name + ", CL_Date=" + CL_Date + ", CL_StartTime=" + CL_StartTime + ", CL_EndTime=" + CL_EndTime + ", CL_ID=" + CL_ID;
    }





}
