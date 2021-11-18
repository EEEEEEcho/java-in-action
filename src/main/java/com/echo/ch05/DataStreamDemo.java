package com.echo.ch05;

import com.echo.ch04.Dish;
import com.echo.ch04.MenuFactory;

import java.util.List;

public class DataStreamDemo {
    public static void main(String[] args) {
        List<Dish> menu = MenuFactory.getMenu();
        int sum = menu.stream()
                .mapToInt(Dish::getCalories).sum();
        System.out.println(sum);
    }
}
