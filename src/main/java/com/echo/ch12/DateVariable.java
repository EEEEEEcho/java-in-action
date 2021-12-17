package com.echo.ch12;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

public class DateVariable {
    public static void main(String[] args) {
        LocalDate date1 = LocalDate.of(2017, 9, 21);
        //所有的方法都返回了一个修改了属性的对象，都是一个在原有对象基础上生成的新对象
        //并不会修改原来的对象
        LocalDate date2 = date1.withYear(2011); //一个新对象
        LocalDate date3 = date2.withDayOfMonth(25);
        LocalDate date4 = date3.with(ChronoField.MONTH_OF_YEAR, 2);
        System.out.println(date4);

        LocalDate date5 = date1.plusWeeks(1);
        LocalDate date6 = date5.minusYears(6);
        System.out.println(date6);

        LocalDate date7 = date1.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
        LocalDate date8 = date1.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println(date7);
        System.out.println(date8);
    }
}
