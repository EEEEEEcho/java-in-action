package com.echo.ch05;

import com.echo.ch04.Dish;
import com.echo.ch04.MenuFactory;

import java.util.List;
import java.util.stream.Collectors;

public class SliceDemo {
    public static void main(String[] args) {
        List<Dish> specialMenu = MenuFactory.getSpecialMenu();
        //找出所有热量<320的元素
        //takeWhile可以在处理海量数据分片时也有很好的效果，可以帮助我们用谓词进行分片
        //会在遇到第一个不符合要求的元素时停止处理
        //字面意思取所有符合条件的元素
        List<Dish> sliceMenu1 =
                specialMenu.stream()
                        .takeWhile(dish -> dish.getCalories() < 320)
                        .collect(Collectors.toList());
        System.out.println(sliceMenu1);
        //找出所有热量 > 320的元素
        //使用dropWhile，是takeWhile的反操作。
        //字面意思就是删除所有符合条件的元素
        List<Dish> sliceMenu2 = specialMenu.stream()
                .dropWhile(dish -> dish.getCalories() < 320)
                .collect(Collectors.toList());
        System.out.println(sliceMenu2);

        //截短流,只取前n个
        List<Dish> dishes = specialMenu.stream().filter(dish -> dish.getCalories() > 300).limit(3).collect(Collectors.toList());
        System.out.println(dishes);
        //跳过前n个，与limit互补
        List<Dish> skipList = specialMenu.stream()
                .filter(d -> d.getCalories() > 300)
                .skip(2)
                .collect(Collectors.toList());
    }
}
