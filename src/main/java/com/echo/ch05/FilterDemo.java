package com.echo.ch05;

import com.echo.ch04.Dish;
import com.echo.ch04.MenuFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FilterDemo {
    public static void main(String[] args) {
        //筛选所有素菜
        List<Dish> menu = MenuFactory.getMenu();
        List<Dish> vegetarian = menu.stream().filter(Dish::isVegetarian).collect(Collectors.toList());
        System.out.println(vegetarian);
        //筛选去重
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream().filter(i -> i % 2 == 0).distinct().forEach(System.out::println);
        //筛选前两个荤菜
        menu.stream().filter(dish -> dish.getType() == Dish.Type.MEAT).limit(2).forEach(System.out::println);

    }
}
