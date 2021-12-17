package com.echo.ch12;

import java.time.*;
import java.util.Date;
import java.util.TimeZone;

public class TimeZoneDemo {
    public static void main(String[] args) {
        //时区表示{区域/城市}
        ZoneId romeZone = ZoneId.of("Europe/Rome");
        ZoneId zoneId = TimeZone.getDefault().toZoneId();


        LocalDate date = LocalDate.of(2014, Month.MARCH, 18);
        //使用时区将其变成了一个时区对象
        ZonedDateTime zdt1 = date.atStartOfDay(romeZone);
        System.out.println(zdt1);
        LocalDateTime dateTime = LocalDateTime.of(2014, Month.MARCH, 18, 13, 45);
        ZonedDateTime zdt2 = dateTime.atZone(romeZone);
        System.out.println(zdt2);
        Instant instant = Instant.now();
        ZonedDateTime zdt3 = instant.atZone(romeZone);
        System.out.println(zdt3);

        Instant instant1 = new Date().toInstant();
        System.out.println(instant1);

    }
}
