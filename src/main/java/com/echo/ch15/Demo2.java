package com.echo.ch15;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Demo2 {
    public static void main(String[] args) throws InterruptedException {
        //method1();
        method2();
    }
    public static void method1() throws InterruptedException {
        work1();
        Thread.sleep(2000);
        work2();
    }
    public static void method2(){
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(1);
        work1();
        pool.schedule(Demo2::work2,2000, TimeUnit.MILLISECONDS);
        pool.shutdown();
    }
    public static void work1(){
        System.out.println("Hello from work1");
    }
    public static void work2(){
        System.out.println("Hello from work2");
    }
}
