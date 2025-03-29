package org.example;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.MonthDay;
import java.util.HashSet;
import java.util.Set;

public class TrainingCalculator {
    private static final int WORKING_HOURS_START = 10;
    private static final int WORKING_HOURS_END = 18;
    private static final DayOfWeek[] WORKING_DAYS = {
            DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY
    };

    // Множество праздничных дней
    private static final Set<MonthDay> HOLIDAYS = new HashSet<>();

    static {
        HOLIDAYS.add(MonthDay.of(Month.MARCH, 8)); //Международный женский день
        HOLIDAYS.add(MonthDay.of(Month.FEBRUARY, 28)); //День защитника Отечества
        HOLIDAYS.add(MonthDay.of(Month.MAY, 9)); //День Победы
        HOLIDAYS.add(MonthDay.of(Month.NOVEMBER, 4)); //День народного единства
        HOLIDAYS.add(MonthDay.of(Month.JUNE, 12)); //День России
        HOLIDAYS.add(MonthDay.of(Month.MAY, 1)); //Праздник Весны и Труда
        HOLIDAYS.add(MonthDay.of(Month.JANUARY, 1)); //Новый год
        HOLIDAYS.add(MonthDay.of(Month.JANUARY, 2)); //Новый год
        HOLIDAYS.add(MonthDay.of(Month.JANUARY, 3)); //Новый год
        HOLIDAYS.add(MonthDay.of(Month.JANUARY, 4)); //Новый год
        HOLIDAYS.add(MonthDay.of(Month.JANUARY, 5)); //Новый год
        HOLIDAYS.add(MonthDay.of(Month.JANUARY, 6)); //Новый год
        HOLIDAYS.add(MonthDay.of(Month.JANUARY, 7)); //Рождество Христово
        HOLIDAYS.add(MonthDay.of(Month.JANUARY, 8)); //Новый год
    }

    private final Student student;

    public TrainingCalculator(Student student) {
        this.student = student;
    }

    // Метод для расчета даты окончания обучения
    public LocalDateTime calculateEndDate() {
        LocalDateTime currentDate = student.getStartDate();
        int hoursRemaining = student.getProgram().getTotalDuration();

        // Цикл для расчета даты окончания
        while (hoursRemaining > 0) {
            if (isWorkingDay(currentDate)) {

                int workdayHoursAvailable = WORKING_HOURS_END - currentDate.getHour();  // Вычисляем доступные рабочие часы

                if (workdayHoursAvailable > 0) {  // Если рабочие часы еще не закончились
                    int workdayHours = Math.min(workdayHoursAvailable, hoursRemaining);  // Вычисляем часы для текущего дня
                    hoursRemaining -= workdayHours;  // Уменьшаем оставшиеся часы
                    currentDate = currentDate.plusHours(workdayHours);  // Добавляем часы к текущей дате
                }
                else {  // Если рабочие часы закончились
                    currentDate = currentDate.plusDays(1).withHour(WORKING_HOURS_START).withMinute(0).withSecond(0);
                }
            }
            else {  // Если это выходной или праздничный день
                currentDate = currentDate.plusDays(1).withHour(WORKING_HOURS_START).withMinute(0).withSecond(0);
            }
        }
        return currentDate;
    }

    // Метод для проверки, является ли день рабочим
    private boolean isWorkingDay(LocalDateTime date) {
        if (isHoliday(date)) {
            return false;
        }

        for (DayOfWeek day : WORKING_DAYS) {
            if (date.getDayOfWeek() == day) {
                return true;
            }
        }
        return false;
    }

    // Метод для проверки, является ли день праздничным
    private boolean isHoliday(LocalDateTime date) {
        MonthDay currentMonthDay = MonthDay.of(date.getMonth(), date.getDayOfMonth());
        return HOLIDAYS.contains(currentMonthDay);
    }

    // Метод расчета времени
    public String getTime(LocalDateTime currentDate, boolean isPassed){
        LocalDateTime endDate = calculateEndDate();
        if (isPassed){
            if (currentDate.isAfter(endDate)) {
                Duration duration = Duration.between(endDate, currentDate);
                long days = duration.toDays();
                long hours = duration.toHours() % 24;
                if (duration.toMinutes() % 60 > 0) {
                    hours++;
                }
                return days + " д " + hours + " ч";
            }
        }
        else{
            if (currentDate.isBefore(endDate)) {
                Duration duration = Duration.between(currentDate, endDate);
                long days = duration.toDays();
                long hours = duration.toHours() % 24;
                if (duration.toMinutes() % 60 > 0) {
                    hours++;
                }
                return days + " д " + hours + " ч";
            }
        }
        return null;
    }
}