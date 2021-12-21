package com.echo.ch16;

import com.echo.ch16.bestshop.DelayUtil;
import com.echo.ch16.bestshop.Shop;

import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) {
        Shop shop = new Shop("BestShop");
        long start = System.nanoTime();
        //查询商店，试图取得商品的价格，这个Future对象是立刻返回的
        Future<Double> futurePrice
                = shop.getPriceAsync("my favorite product");
        long invocationTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Invocation returned after " + invocationTime + "msecs");
        //执行更多任务，比如查询其他商店
        DelayUtil.doSomethingElse();
        //在计算商品价格的同事
        try {
            //如果价格未知时，会发生阻塞
            Double price = futurePrice.get();
            System.out.printf("Price is %.2f%n",price);
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
        long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Price returned after " + retrievalTime + " msecs");
    }
}
