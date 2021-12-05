package com.echo.ch07;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CountWordDemo {
    final String SENTENCE = "Nel  mezzo del cammin di nostra vita " +
                            "mi ritrovai in una  selva oscura " +
                            " che la  dritta via era smarrita";
    public void demoOne(){
        Stream<Character> stream = IntStream.range(0, SENTENCE.length()).mapToObj(SENTENCE::charAt);
        WordCounter wordCounter = stream.reduce(new WordCounter(0, true),
                WordCounter::accumulate,
                WordCounter::combine);
        System.out.println(wordCounter.getCounter());
    }

    public static void main(String[] args) {
        CountWordDemo demo = new CountWordDemo();
        demo.demoOne();
    }
}
