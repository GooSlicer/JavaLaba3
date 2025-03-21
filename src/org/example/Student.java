package org.example;

import java.time.LocalDateTime;

public class Student {
    private final String name;
    private final Program program;
    private final LocalDateTime startDate;

    public Student(String name, Program program, LocalDateTime startDate) {
        this.name = name;
        this.program = program;
        this.startDate = startDate;
    }

    public String getName() {
        return name;
    }

    public Program getProgram() {
        return program;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }
}