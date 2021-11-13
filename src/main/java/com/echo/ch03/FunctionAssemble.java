package com.echo.ch03;

import java.util.function.Function;

public class FunctionAssemble {
    public static void main(String[] args) {
        Function<Integer,Integer> f = x -> x + 1;
        Function<Integer,Integer> g = x -> x * 2;
        Function<Integer, Integer> h = f.andThen(g);
        Function<Integer, Integer> t = f.compose(g);
        Integer r1 = h.apply(1);    //先f函数后g函数
        Integer r2 = t.apply(1);    //先g函数后f函数
        System.out.println(r1);
        System.out.println(r2);

        //组合流水线
        Function<String, String> addHeader = Letter::addHeader;
        Function<String, String> transformation =
                addHeader.andThen(Letter::checkSpelling).andThen(Letter::addFooter);
        String text = transformation.apply("Hello labda,Good Evening.");
        System.out.println(text);
    }
}
class Letter{
    public static String addHeader(String text){
        return "From Raoul,Mario and Alan: " + text;
    }
    public static String addFooter(String text){
        return text + " Kind regards";
    }
    public static String checkSpelling(String text){
        return text.replaceAll("labda","lambda");
    }
}