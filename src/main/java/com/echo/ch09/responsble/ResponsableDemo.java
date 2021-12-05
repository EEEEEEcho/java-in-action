package com.echo.ch09.responsble;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public class ResponsableDemo {
    public static void main(String[] args) {
        ProcessingObject<String> p1 = new HeaderTextProcessing();
        ProcessingObject<String> p2 = new SpellCheckerProcessing();
        //p1的后继者是p2
        p1.setSuccessor(p2);

        String result = p1.handle("Aren't labdas really sexy?!!");

        System.out.println(result);

        //使用lambda完全不需要这样
        UnaryOperator<String> headerProcessing = (String text) -> "From Raoul,Mario and Alan: " + text;

        UnaryOperator<String> spellCheckingProcessing = (String text) -> text.replace("labdas","lambda");

        Function<String,String> pipeline = headerProcessing.andThen(spellCheckingProcessing);

        String ans = pipeline.apply("Aren't labdas really sexy?!!");
        System.out.println(ans);
    }
}
