package com.echo.ch05;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BuildStreamDemo {
    public static void main(String[] args) throws IOException {
        //1.由值创建
        Stream<String> stream1 = Stream.of("Modern", "Java", "In", "Action");
        stream1.map(String::toUpperCase).forEach(System.out::println);
        //2.得到空流
        Stream<Object> emptyStream = Stream.empty();
        //3.由可空对象创建流
        String homeValue = System.getProperty("home");
        Stream<String> homeValueStream1 = homeValue == null ? Stream.empty() : Stream.of(homeValue);
        //简化
        Stream<String> homeValueStream2 = Stream.ofNullable(System.getProperty("home"));
        //搭配flatMap
        Stream<String> values = Stream.of("config", "home", "user").flatMap(
                key -> Stream.ofNullable(System.getProperty(key))
        );
        //4.由数组创建
        int[] numbers = {1,2,3,4};
        int sum = Arrays.stream(numbers).sum();
        //5.由文件生成,统计一个文件中有多少个不同的单词
        long uniqueWords = 0;
        try(Stream<String> lines = Files.lines(Paths.get("data.txt"), Charset.defaultCharset())){
            //将文件中的每一行读出来，生成一个流，然后将这流中的每一个元素（每一行）拿出来，
            //对每一行，将其中包含的所有单词拆出来，然后生成一个新的流，然后扁平化为一个单词流
            //再去重，统计
            uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" ")))
                    .distinct()
                    .count();
            System.out.println(uniqueWords);
        }
        //6.由函数生成无限流
        //6.1 iterate
        Stream.iterate(0,n -> n + 2).limit(10).forEach(System.out::println);
        //斐波那契数列
        Stream.iterate(new int[]{0,1},t -> {
            int tmp = t[1];
            t[1] = t[0] + t[1];
            t[0] = tmp;
            return t;
        })
                .limit(20).forEach(a -> {
            System.out.println(Arrays.toString(a));
        });
        //java9的谓词增强,第二个参数表示只取小于100的数字
        IntStream.iterate(0,n -> n < 100,n -> n + 4).forEach(System.out::println);
        //6.2 generate
        Stream.generate(Math::random).limit(5).forEach(System.out::println);

        //匿名类和lambda的区别就在于，匿名类可以通过字段定义状态，而状态可以通过
        //getAsInt来修改
        IntSupplier intSupplier = new IntSupplier(){
            private int previous = 0;
            private int current = 1;
            public int getAsInt(){
                int oldPrevious = this.previous;
                int nextValue = this.previous + this.current;
                this.previous = this.current;
                this.current = nextValue;
                return oldPrevious;
            }
        };
        IntStream.generate(intSupplier).limit(10).forEach(System.out::println);
    }
}
