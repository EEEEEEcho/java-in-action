package com.echo.ch15;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Demo1 {
    public static void main(String[] args) {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2);
        fixedThreadPool.execute(()->{
            System.out.println("Hello world");
        });
        fixedThreadPool.execute(() -> {
            System.out.println("Life is a f**king movie");
        });
        fixedThreadPool.shutdown();
    }
}
