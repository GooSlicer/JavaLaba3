package org.example;

import java.util.List;

public class Program {
    private final String name;
    private final List<Course> courses;

    public Program(String name, List<Course> courses) {
        this.name = name;
        this.courses = courses;
    }

    public int getTotalDuration() {
        return courses.stream().mapToInt(Course::getDuration).sum();
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + " (" + getTotalDuration() + " часов)";
    }
}