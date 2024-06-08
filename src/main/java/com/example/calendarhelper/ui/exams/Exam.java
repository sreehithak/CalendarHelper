package com.example.calendarhelper.ui.exams;
public class Exam {
    String Name, Time, Location;

    public Exam(String Name, String Time, String Location) {
        this.Name = Name;
        this.Time = Time;
        this.Location = Location;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        this.Time = time;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }
}
