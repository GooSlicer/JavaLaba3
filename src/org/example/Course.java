package org.example;

public class Course {
    private final int duration; // Длительность в часах

    public Course(String name, int duration) {
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }
}