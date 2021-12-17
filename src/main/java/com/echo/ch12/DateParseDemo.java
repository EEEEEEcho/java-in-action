package com.echo.ch12;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateParseDemo {
    public static void main(String[] args) {
        LocalDate date = LocalDate.of(2014, 3, 18);
        String s1 = date.format(DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println(s1);
        String s2 = date.format(DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println(s2);

        LocalDate date3 = LocalDate.parse("20140318",
                DateTimeFormatter.BASIC_ISO_DATE);
        LocalDate date4 = LocalDate.parse("2014-03-18",
                DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println(date3);
        System.out.println(date4);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate of = LocalDate.of(2014, 3, 18);
        String format = of.format(formatter);//使用指定的模式生成字符串
        System.out.println(format);
        LocalDate date2 = LocalDate.parse(format, formatter);//使用指定的模式解析字符串
        System.out.println(date2);
    }
}
