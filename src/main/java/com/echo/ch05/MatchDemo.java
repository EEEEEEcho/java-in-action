package com.echo.ch05;

import com.echo.ch04.Dish;
import com.echo.ch04.MenuFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class MatchDemo {
    public static void main(String[] args) {
        List<Dish> menu = MenuFactory.getMenu();
        if (menu.stream().anyMatch(Dish::isVegetarian)){
            System.out.println("The menu is vegetarian !!");
        }
        boolean isHealthy = menu.stream().allMatch(dish -> dish.getCalories() < 1000);
        boolean healthy = menu.stream().noneMatch(dish -> dish.getCalories() >= 1000);

        //找到任意一个素食
        Optional<Dish> dish = menu.stream().filter(Dish::isVegetarian).findAny();
        dish.ifPresent(dish1 -> System.out.println(dish1.getName()));

        //找到满足条件的第一个
        List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);
        Optional<Integer> first = someNumbers.stream().map(n -> n * n).filter(n -> n % 3 == 0).findFirst();
        first.ifPresent(System.out::println);
    }
}
