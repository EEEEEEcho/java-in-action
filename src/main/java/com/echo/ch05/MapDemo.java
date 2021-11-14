package com.echo.ch05;

import com.echo.ch04.Dish;
import com.echo.ch04.MenuFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MapDemo {
    public static void main(String[] args) {
        List<Dish> menu = MenuFactory.getMenu();
        //找到每道菜的名字
        List<String> dishName = menu.stream()
                .map(Dish::getName)
                .collect(Collectors.toList());
        //找到每道菜的名字有多长
        List<Integer> lengthList = menu.stream()
                .map(Dish::getName)
                .map(String::length)
                .collect(Collectors.toList());
        System.out.println(lengthList);
        //流扁平化
        String[] arrayOfWords = {"Goodbye","World"};
        Stream<String> stream = Arrays.stream(arrayOfWords);

        List<Stream<String>> errorCollect = stream.map(word -> word.split(""))//变成数组
                .map(Arrays::stream)    //将数组再变成流
                .distinct() //去重
                .collect(Collectors.toList());
        //这样收集出来的是两个流对象
        System.out.println(errorCollect);

        //流不可以重用
        stream = Arrays.stream(arrayOfWords);
        List<String> rightCollect = stream.map(word -> word.split(""))
                .flatMap(Arrays::stream)//扁平化
                .distinct()
                .collect(Collectors.toList());
        //flatMap将流中的每个值都换成另一个流，然后把所有的流连接起来成为一个流
        System.out.println(rightCollect);

        //数字平方
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> quare = integers.stream()
                .map(x -> x * x)
                .collect(Collectors.toList());
        System.out.println(quare);
        //组合数组[1,2,3]和[3,4]
        List<Integer> nums1 = Arrays.asList(1, 2, 3);
        List<Integer> nums2 = Arrays.asList(3, 4);
        List<int[]> pairs = nums1.stream()
                .flatMap(i -> nums2.stream().map(j -> new int[]{i, j}))
                .collect(Collectors.toList());
        pairs.stream().forEach(arr -> System.out.print(Arrays.toString(arr) + " "));
    }
}
