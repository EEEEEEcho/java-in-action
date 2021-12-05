package com.echo.ch09;

import java.util.List;
import java.util.stream.Collectors;

public class PeekDemo {
    public static void main(String[] args) {
        List<Integer> list = List.of(3, 4, 5, 6);
        List<Integer> integers = list.stream()
                .peek(x -> System.out.println("from stream " + x))
                .map(x -> x + 17)
                .peek(x -> System.out.println("after map " + x))
                .filter(x -> x % 2 == 0)
                .peek(x -> System.out.println("after filter " + x))
                .limit(3)
                .peek(x -> System.out.println("after limit " + x))
                .collect(Collectors.toList());
        System.out.println(integers);
    }
}
