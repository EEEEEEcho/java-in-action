package com.echo.ch06;

import com.echo.ch04.Dish;
import com.echo.ch04.MenuFactory;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

        Map<Boolean, List<Integer>> booleanListMap = partitionPrimes(100);
        System.out.println(booleanListMap);
    }

    public static boolean isPrime(int candidate){
        return IntStream.range(2,candidate).noneMatch(i -> candidate % i == 0);
    }

    public static boolean isPrime2(int candidate){
        int candidateRoot = (int) Math.sqrt((double) candidate);
        return IntStream.rangeClosed(2,candidateRoot).noneMatch(i -> candidateRoot % i == 0);
    }

    /**
     * 将从2~n的数字分为质数和非质数
     * @param n
     * @return
     */
    public static Map<Boolean,List<Integer>> partitionPrimes(int n){
        return IntStream.rangeClosed(2,n).boxed().collect(Collectors.partitioningBy(candidate -> isPrime2(candidate)));
    }


}
