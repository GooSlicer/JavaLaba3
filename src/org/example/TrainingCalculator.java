package org.example;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;

public class TrainingCalculator {
    private static final int WORKING_HOURS_START = 10;
    private static final int WORKING_HOURS_END = 18;
    private static final DayOfWeek[] WORKING_DAYS = {
            DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY
    };

    private final Student student;

    public TrainingCalculator(Student student) {
        this.student = student;
    }

    public LocalDateTime calculateEndDate() {
        LocalDateTime currentDate = student.getStartDate();
        int hoursRemaining = student.getProgram().getTotalDuration();

        while (hoursRemaining > 0) {
            if (isWorkingDay(currentDate)) {
                int workdayHoursAvailable = WORKING_HOURS_END - currentDate.getHour();
                if (workdayHoursAvailable <= 0) {
                    currentDate = currentDate.plusDays(1).withHour(WORKING_HOURS_START).withMinute(0).withSecond(0);
                    continue;
                }

                int workdayHours = Math.min(workdayHoursAvailable, hoursRemaining);
                hoursRemaining -= workdayHours;
                currentDate = currentDate.plusHours(workdayHours);
            } else {
                currentDate = currentDate.plusDays(1).withHour(WORKING_HOURS_START).withMinute(0).withSecond(0);
            }
        }

        return currentDate;
    }

    private boolean isWorkingDay(LocalDateTime date) {
        for (DayOfWeek day : WORKING_DAYS) {
            if (date.getDayOfWeek() == day) {
                return true;
            }
        }
        return false;
    }

    public String getTimeRemaining(LocalDateTime currentDate) {
        LocalDateTime endDate = calculateEndDate();
        if (currentDate.isBefore(endDate)) {
            Duration duration = Duration.between(currentDate, endDate);
            long days = duration.toDays();
            long hours = duration.toHours() % 24;
            if (duration.toMinutes() % 60 > 0) {
                hours++;
            }
            return days + " д " + hours + " ч";
        }
        return null;
    }

    public String getTimePassed(LocalDateTime currentDate) {
        LocalDateTime endDate = calculateEndDate();
        if (currentDate.isAfter(endDate)) {
            Duration duration = Duration.between(endDate, currentDate);
            long days = duration.toDays();
            long hours = duration.toHours() % 24;
            if (duration.toMinutes() % 60 > 0) {
                hours++;
            }
            return days + " д " + hours + " ч";
        }
        return null;
    }
}