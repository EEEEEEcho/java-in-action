package com.echo.ch06;

import com.echo.ch04.CaloricLevel;
import com.echo.ch04.Dish;
import com.echo.ch04.MenuFactory;

import java.util.*;
import java.util.stream.Collectors;

public class GroupDemo2 {
    public static void main(String[] args) {
        List<Dish> menu = MenuFactory.getMenu();
        //查找每个子组中热量最高的Dish
        Map<Dish.Type, Dish> mostCaloricByType = menu.stream().collect(Collectors.groupingBy(Dish::getType,
                Collectors.collectingAndThen(
                        Collectors.maxBy(
                                Comparator.comparingInt(Dish::getCalories)
                        ),
                        Optional::get
                )));
        System.out.println(mostCaloricByType);
        //计算每组菜肴的热量总和
        Map<Dish.Type, Integer> sum = menu.stream().collect(
                Collectors.groupingBy(
                        Dish::getType,
                        Collectors.summingInt(Dish::getCalories)
                )
        );
        System.out.println(sum);

        Map<Dish.Type, HashSet<CaloricLevel>> typeHashSetMap = menu.stream().collect(Collectors.groupingBy(
                Dish::getType,
                Collectors.mapping(dish -> {
                    if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                    else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                    else return CaloricLevel.FAT;
                }, Collectors.toCollection(HashSet::new))
        ));
        System.out.println(typeHashSetMap);
    }
}
