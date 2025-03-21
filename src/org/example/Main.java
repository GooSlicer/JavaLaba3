package org.example;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Course> javaBasicsCourses = new ArrayList<>();
        javaBasicsCourses.add(new Course("Java Fundamentals", 14));
        javaBasicsCourses.add(new Course("OOP", 20));
        javaBasicsCourses.add(new Course("Data Handling", 10));
        javaBasicsCourses.add(new Course("Exceptions", 16));
        javaBasicsCourses.add(new Course("Collections", 20));

        List<Course> javaAdvancedCourses = new ArrayList<>();
        javaAdvancedCourses.add(new Course("Java IO", 10));
        javaAdvancedCourses.add(new Course("JDBC", 15));
        javaAdvancedCourses.add(new Course("JAXP", 15));


        Program javaBasicsProgram = new Program("Java Basics", javaBasicsCourses);
        Program javaAdvancedProgram = new Program("Java Advanced", javaAdvancedCourses);

        LocalDateTime startDate = LocalDateTime.of(2023, 10, 1, 10, 0);
        Student ivanov = new Student("Иванов Иван", javaBasicsProgram, startDate);
        Student petrov = new Student("Петров Петр", javaAdvancedProgram, startDate);

        TrainingCenter trainingCenter = new TrainingCenter();
        trainingCenter.addStudent(ivanov);
        trainingCenter.addStudent(petrov);

        LocalDateTime currentDate = LocalDateTime.of(2023, 10, 15, 18, 0);

        trainingCenter.generateReport(currentDate);
    }
}