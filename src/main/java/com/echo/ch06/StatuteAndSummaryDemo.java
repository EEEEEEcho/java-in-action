package com.echo.ch06;

import com.echo.ch04.Dish;
import com.echo.ch04.MenuFactory;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StatuteAndSummaryDemo {
    public static void main(String[] args) {
        List<Dish> menu = MenuFactory.getMenu();
        //1.规约
        //统计
        Long count = menu.stream().collect(Collectors.counting());
        System.out.println(count);
        //查找流中的最大值和最小值
        Comparator<Dish> dishComparator = Comparator.comparingInt(Dish::getCalories);
        Optional<Dish> maxDish = menu.stream().collect(Collectors.maxBy(dishComparator));
        maxDish.ifPresent(System.out::println);
        //2.汇总
        Integer totalCalories = menu.stream().collect(Collectors.summingInt(Dish::getCalories));
        System.out.println(totalCalories);

        Double avgCalories = menu.stream().collect(Collectors.averagingDouble(Dish::getCalories));
        System.out.println(avgCalories);

        //包含了对菜单中数据的统计信息的对象
        IntSummaryStatistics menuStatistics = menu.stream().collect(Collectors.summarizingInt(Dish::getCalories));
        System.out.println(menuStatistics);

        //连接字符串
//        String shortMenu = menu.stream().map(Dish::getName).collect(Collectors.joining());
//        System.out.println(shortMenu);
        //分隔符
        String shortMenu = menu.stream().map(Dish::getName).collect(Collectors.joining(", "));
        System.out.println(shortMenu);
    }
}
