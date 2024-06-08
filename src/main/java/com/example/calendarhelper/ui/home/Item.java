package com.example.calendarhelper.ui.home;
public class Item {
    String Name, Time, Instructor;

    public Item(String Name, String Time, String Instructor) {
        this.Name = Name;
        this.Time = Time;
        this.Instructor = Instructor;
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

    public String getInstructor() {
        return Instructor;
    }

    public void setInstructor(String instructor) {
        this.Instructor = instructor;
    }
}
