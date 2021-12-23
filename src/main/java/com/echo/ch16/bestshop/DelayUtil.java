package com.echo.ch16.bestshop;

import java.util.Random;

public class DelayUtil {
    public static void delay(){
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public static void doSomethingElse(){
        System.out.println("正在淦别的事情。。。。");
        DelayUtil.delay();
    }
}
