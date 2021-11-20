package com.echo.ch05;

import com.echo.ch04.Dish;
import com.echo.ch04.MenuFactory;

import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class DataStreamDemo {
    public static void main(String[] args) {
        List<Dish> menu = MenuFactory.getMenu();
        int sum = menu.stream()
                .mapToInt(Dish::getCalories).sum();
        System.out.println(sum);
        //将数值流转换为Stream
        IntStream intStream = menu.stream().mapToInt(Dish::getCalories);
        Stream<Integer> boxed = intStream.boxed();
        //默认值OptionalInt
        OptionalInt max = menu.stream().mapToInt(Dish::getCalories).max();
        int result = max.orElse(1); //流不为空，存在最大值，则取最大值，否则取1

        //数值范围:生成一个从1到100的偶数流
        IntStream eventNumber = IntStream.rangeClosed(1, 100).filter(n -> n % 2 == 0);
        //打印该流中的数字的数量
        System.out.println(eventNumber.count());
    }
}
