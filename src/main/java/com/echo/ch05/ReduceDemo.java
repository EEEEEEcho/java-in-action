package com.echo.ch05;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ReduceDemo {
    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(4, 5, 3, 9);
        //基础版本
        Integer sum = integers.stream().reduce(0, (a, b) -> a + b);
        System.out.println(sum);

        //函数替换版本
        Integer sum2 = integers.stream().reduce(0, Integer::sum);
        System.out.println(sum2);

        //无初始值
        //流中可能没有元素
        Optional<Integer> optional = integers.stream().reduce((m, n) -> (m + n));

        Optional<Integer> max = integers.stream().reduce(Integer::max);
        max.ifPresent(System.out::println);

        Optional<Integer> min = integers.stream().reduce(Integer::min);
        min.ifPresent(System.out::println);

        Optional<Integer> parallelSum = integers.parallelStream().reduce(Integer::sum);
        parallelSum.ifPresent(System.out::println);
    }
}
