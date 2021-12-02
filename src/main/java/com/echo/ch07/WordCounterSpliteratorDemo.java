package com.echo.ch07;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class WordCounterSpliteratorDemo {
    public static void main(String[] args) {
        String sentence = "There are some people who think love is sex and marriage and six o’clock-kisses and children," +
                " and perhaps it is, Miss Lester. But do you know what I think? " +
                "I think love is a touch and yet not a touch";
        WordCounterSpliterator spliterator = new WordCounterSpliterator(sentence);
        Stream<Character> stream = StreamSupport.stream(spliterator, true);

    }
}
