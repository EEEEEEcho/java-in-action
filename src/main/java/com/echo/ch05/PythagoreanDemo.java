package com.echo.ch05;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PythagoreanDemo {
    public static void main(String[] args) {
        //生成勾股数流
        Stream<int[]> pythagoreanTriples =
                IntStream.rangeClosed(1, 100)
                .boxed()
                .flatMap(a ->
                        IntStream.rangeClosed(a, 100)
                                .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                                .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)})

                );
        pythagoreanTriples.limit(5)
                .forEach(t -> System.out.println(t[0] + "," + t[1] + "," + t[2]));

        //上述要求两次平方根，因此可以先求平方，在做过滤
        Stream<double[]> pythagoreanTriples2 = IntStream.rangeClosed(1, 100)
                .boxed()
                .flatMap(
                        a -> IntStream.rangeClosed(a, 100)
                                .mapToObj(b -> new double[]{a, b, Math.sqrt(a * a + b * b)})
                                .filter(t -> t[2] % 1 == 0)

                );
    }
}
