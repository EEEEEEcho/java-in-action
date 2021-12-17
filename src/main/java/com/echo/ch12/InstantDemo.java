package com.echo.ch12;

import java.time.*;

public class InstantDemo {
    public static void main(String[] args) {
        Instant instant = Instant.ofEpochSecond(3);
        Instant instant1 = Instant.ofEpochSecond(3, 0);
        //两秒之后再加上10亿纳秒（1秒）
        Instant instant2 = Instant.ofEpochSecond(2, 1_000_000_000);
        System.out.println(instant + "\t" + instant1 + "\t" + instant2);

        //计算间隔
//        LocalDate date = LocalDate.of(2020, 10, 1);
//        LocalDate date1 = LocalDate.of(2020, 10, 3);
//        Duration twoDays = Duration.between(date, date1);
//        System.out.println(twoDays);
//
//
//        LocalDateTime dateTime = LocalDateTime.of(2020, 11, 1, 18, 32);
//        LocalDateTime dateTime1 = LocalDateTime.of(2020, 12, 20, 17, 54);
//        Duration duration = Duration.between(dateTime, dateTime1);
//        System.out.println(duration);

        Instant instant3 = Instant.ofEpochSecond(10);
        Instant instant4 = Instant.ofEpochSecond(20);
        Duration between = Duration.between(instant3, instant4);
        System.out.println(between);

        Period tendays = Period.between(LocalDate.of(2017, 9, 11),
                LocalDate.of(2017, 9, 21));
        System.out.println(tendays);
    }
}
