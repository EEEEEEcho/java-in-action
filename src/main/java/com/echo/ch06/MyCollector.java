package com.echo.ch06;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class MyCollector implements Collector<Integer,Map<Boolean,List<Integer>>,Map<Boolean,List<Integer>>> {

    public static boolean isPrime(List<Integer> primes,int candidate){
        int candidateRoot = (int)Math.sqrt((double) candidate);
        return primes.stream().takeWhile(i -> i <= candidate).noneMatch(i -> candidate % i == 0);
    }

    @Override
    public Supplier<Map<Boolean, List<Integer>>> supplier() {
        return () -> {
            HashMap<Boolean, List<Integer>> booleanListHashMap = new HashMap<>();
            booleanListHashMap.put(true,new ArrayList<Integer>());
            booleanListHashMap.put(false,new ArrayList<Integer>());
            return booleanListHashMap;
        };
    }

    @Override
    public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
        return (acc,candidate) -> {
            acc.get( isPrime(acc.get(true),candidate) ).add(candidate);
        };
    }

    @Override
    public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
        return (map1,map2) -> {
            map1.get(true).addAll(map2.get(true));
            map1.get(false).addAll(map2.get(false));
            return map1;
        };
    }

    @Override
    public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH));
    }
}
