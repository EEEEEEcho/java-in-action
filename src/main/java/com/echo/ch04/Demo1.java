package com.echo.ch04;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class Demo1 {
    public static void main(String[] args) {
        List<Dish> menu = MenuFactory.getMenu();
        List<String> threeHighCaloricDishName =
                menu.stream()
                .filter(dish -> dish.getCalories() > 300)
                .map(Dish::getName)//获取名称
                .limit(3)
                .collect(toList()); //将结果保存在另一个list中
        System.out.println(threeHighCaloricDishName);
    }
}
