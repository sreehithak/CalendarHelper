package com.example.calendarhelper.ui.gallery;
public class Assignment {
    String Title, Due, Course;

    public Assignment(String Name, String Time, String Instructor) {
        this.Title = Name;
        this.Due = Time;
        this.Course = Instructor;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public String getDue() {
        return Due;
    }

    public void setDue(String due) {
        this.Due = due;
    }

    public String getInstructor() {
        return Course;
    }

    public void setInstructor(String course) {
        this.Course = course;
    }
}
