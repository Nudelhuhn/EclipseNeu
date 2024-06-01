package oop.misc;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class DateTimeExamples
{
    private static void exampleLocalDate()
    {
        LocalDate now = LocalDate.now();
        System.out.println(now);
        System.out.println(now.getDayOfMonth() + ". " + now.getMonth() + " " + now.getYear());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d. MMMM y");
        System.out.println(now.format(formatter));
        
        LocalDate nationalHoliday = LocalDate.of(1990, Month.OCTOBER, 3);
        System.out.println(nationalHoliday.format(formatter));
        LocalDate nationalHolidayDaysLater = nationalHoliday.plusDays(30);
        System.out.println(nationalHolidayDaysLater.format(formatter));
        LocalDate nationalHolidayMonthsLater = nationalHoliday.plusMonths(30);
        System.out.println(nationalHolidayMonthsLater.format(formatter));
        LocalDate nationalHolidayYearsLater = nationalHoliday.plusYears(30);
        System.out.println(nationalHolidayYearsLater.format(formatter));

        LocalDate oldNationalHoliday = LocalDate.parse("17. Juni 1953", formatter);
        System.out.println(oldNationalHoliday.getDayOfMonth() + ". " + 
                           oldNationalHoliday.getMonth() + " " + 
                           oldNationalHoliday.getYear());
    }
    
    private static void exampleLocalTime()
    {
        LocalTime now = LocalTime.now();
        System.out.println(now);
        System.out.println(now.getHour() + ":" + now.getMinute() + ":" + 
                           now.getSecond() + "," + now.getNano());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss:nn");
        System.out.println(now.format(formatter));
        
        formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime startLecture = LocalTime.of(9, 45);
        System.out.println(startLecture.format(formatter));
        LocalTime endLecture = startLecture.plusMinutes(90);
        System.out.println(endLecture.format(formatter));
    }
    
    public static void main(String[] args)
    {
        exampleLocalDate();
        System.out.println("=".repeat(30));
        exampleLocalTime();
    }

}
