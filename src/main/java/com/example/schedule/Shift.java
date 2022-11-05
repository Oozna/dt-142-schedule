package com.example.schedule;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Shift {
    private int uid;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime stopTime;
    private String workType;

    Shift(int uid, LocalDate date, LocalTime starttime, LocalTime stoptime, String worktype){
        this.uid = uid;
        this.date = date;
        this.startTime = starttime;
        this.stopTime = stoptime;
        this.workType = worktype;
    }

    public int getUid(){
        return this.uid;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String getDate(){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d MMM");
        return date.format(format);
    }

    public String getStartTime(){
        return this.startTime.toString();
    }

    public String getStopTime(){
        return this.stopTime.toString();
    }

    public String getWorkType(){
        return this.workType;
    }
}
