package com.echo.ch07;

import java.util.stream.LongStream;

public class SideEffectSumDemo {

    public long sideEffectSum(long n){
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1,n).forEach(accumulator::add);
        return accumulator.total;
    }
    public long sideEffectParallel(long n){
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1,n).parallel().forEach(accumulator::add);
        return accumulator.total;
    }

    public static void main(String[] args) {
        SideEffectSumDemo sideEffectSumDemo = new SideEffectSumDemo();
        long l = sideEffectSumDemo.sideEffectSum(10_000_000);
        System.out.println(l);

        long l1 = sideEffectSumDemo.sideEffectParallel(10_000_000);
        System.out.println(l1);
    }
}

