package com.echo.ch07;

import java.util.stream.Stream;

public class ParallelStreamDemo {
    public static void main(String[] args) {

    }
    public static long parallelSum(long n){
        return Stream.iterate(1L,i -> i + 1)    //生成自然数无限流
                .limit(n)
                .parallel() //将流转换为并行流
                .reduce(0L,Long::sum);
    }
}
