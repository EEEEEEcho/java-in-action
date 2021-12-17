package com.echo.ch12;

import java.time.*;
import java.time.temporal.ChronoField;

public class LocalDateAndTimeDemo {
    public static void main(String[] args) {
        LocalDate date = LocalDate.of(2017, 9, 21);
        System.out.println(date);
        int year = date.getYear();
        System.out.println(year);
        Month month = date.getMonth();
        System.out.println(month);
        int day = date.getDayOfMonth();
        System.out.println(day);
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        System.out.println(dayOfWeek);
        int len = date.lengthOfMonth();
        System.out.println(len);
        boolean leapYear = date.isLeapYear();
        System.out.println(leapYear);

        LocalDate today = LocalDate.now();

        int i = date.get(ChronoField.YEAR);
        int i1 = date.get(ChronoField.MONTH_OF_YEAR);
        int i2 = date.get(ChronoField.DAY_OF_MONTH);
        System.out.println("" + i + i1 + i2);


        LocalTime time = LocalTime.of(13, 45, 20);
        int hour = time.getHour();
        int minute = time.getMinute();
        int second = time.getSecond();
        System.out.println(hour + ":" + minute + ":" + second);

        LocalDateTime dt1 = LocalDateTime.of(2014, Month.SEPTEMBER, 21, 13, 45, 20);
        System.out.println(dt1);
        LocalDate date1 = dt1.toLocalDate();
        LocalTime time1 = dt1.toLocalTime();
        System.out.println(date1);
        System.out.println(time1);


    }
}
