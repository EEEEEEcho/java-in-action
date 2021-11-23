package com.echo.ch06add;

import com.echo.ch04.Dish;
import com.echo.ch04.MenuFactory;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class PartitionDemo {
    public static void main(String[] args) {
        List<Dish> menu = MenuFactory.getMenu();
        //将菜单中的菜分为素食和肉食两类
        Map<Boolean, List<Dish>> collect = menu.stream().collect(Collectors.partitioningBy(Dish::isVegetarian));

        System.out.println(collect);
        //第二个收集器
        Map<Boolean, Map<Dish.Type, List<Dish>>> vegetablesByType = menu.stream().collect(Collectors.partitioningBy(Dish::isVegetarian,
                Collectors.groupingBy(Dish::getType)));
        System.out.println(vegetablesByType);
        //分区，然后找到素食和非素食中热量最高的菜
        Map<Boolean, Dish> mostCaloriesPartition = menu.stream().collect(
                Collectors.partitioningBy(Dish::isVegetarian,
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)),
                                Optional::get
                        )
                )
        );
        System.out.println(mostCaloriesPartition);
    }
}
