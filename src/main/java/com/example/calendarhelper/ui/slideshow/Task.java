package com.example.calendarhelper.ui.slideshow;
public class Task {
    String Task, Due, Description;

    public Task(String Name, String Description) {
        this.Task = Name;
        this.Due = Due;
        this.Description = Description;
    }

    public String getTask() {
        return Task;
    }

    public void setTask(String task) {
        this.Task = task;
    }

    public String getDue() {
        return Due;
    }

    public void setDue(String due) {
        this.Due = due;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        this.Description = description;
    }
}
