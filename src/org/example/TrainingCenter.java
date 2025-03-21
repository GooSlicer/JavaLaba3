package org.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TrainingCenter {
    private final List<Student> students;

    public TrainingCenter() {
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void generateReport(LocalDateTime currentDate) {
        for (Student student : students) {
            TrainingCalculator calculator = new TrainingCalculator(student);
            LocalDateTime endDate = calculator.calculateEndDate();

            String timeRemaining = calculator.getTimeRemaining(currentDate);
            String timePassed = calculator.getTimePassed(currentDate);

            if (timeRemaining != null) {
                System.out.println(student.getName() + " (" + student.getProgram() + ") - Обучение не закончено. " +
                        "До окончания осталось " + timeRemaining + ". Дата окончания обучения – " +
                        endDate.format(DateTimeFormatter.ofPattern("dd MMMM yyyy года, HH:mm")) + ".");
            } else if (timePassed != null) {
                System.out.println(student.getName() + " (" + student.getProgram() + ") - Обучение закончено. " +
                        "После окончания прошло " + timePassed + ". Дата окончания обучения – " +
                        endDate.format(DateTimeFormatter.ofPattern("dd MMMM yyyy года, HH:mm")) + ".");
            } else {
                System.out.println(student.getName() + " (" + student.getProgram() + ") - Обучение завершено сегодня.");
            }
        }
    }
}