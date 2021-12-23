package com.echo.ch16;

import com.echo.ch16.bestshop.PriceFinder;
import com.echo.ch16.bestshop.PriceFinderWithCode;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
//        Shop shop = new Shop("BestShop");
//        long start = System.nanoTime();
//        //查询商店，试图取得商品的价格，这个Future对象是立刻返回的
//        Future<Double> futurePrice
//                = shop.getPriceAsync("my favorite product");
//        long invocationTime = ((System.nanoTime() - start) / 1_000_000);
//        System.out.println("Invocation returned after " + invocationTime + "msecs");
//        //执行更多任务，比如查询其他商店
//        DelayUtil.doSomethingElse();
//        //在计算商品价格的同事
//        try {
//            //如果价格未知时，会发生阻塞
//            Double price = futurePrice.get();
//            System.out.printf("Price is %.2f%n",price);
//        }
//        catch (Exception e){
//            throw new RuntimeException(e);
//        }
//        long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
//        System.out.println("Price returned after " + retrievalTime + " msecs");
//        PriceFinder priceFinder = new PriceFinder();
//        long start = System.nanoTime();
//        //顺序流执行
//        //List<String> phones = priceFinder.findPriceByStream("myPhone27s");
//        //并行流执行
//        //List<String> phones = priceFinder.findPriceByParallelStream("myPhone27s");
//        //使用CompletableFuture流执行
//        //List<String> phones = priceFinder.findPriceByComFuture("myPhone27s");
//        //使用指定线程池的CompletableFuture流执行  最佳效率
//        List<String> phones = priceFinder.findPriceByExecutor("myPhone27s");
//        System.out.println(phones);
//        long duration = ((System.nanoTime() - start) / 1_000_000);
//        System.out.println(duration);

        PriceFinderWithCode pfw = new PriceFinderWithCode();
        long start = System.nanoTime();
        //List<String> phones = pfw.findPrices("myPhone27s");
        //List<String> phones = pfw.findPricesByExecutor("myPhone27s");
        //System.out.println(phones);
        Stream<CompletableFuture<String>> pricesStream = pfw.findPricesStream("myPhone27s");
        //在每个CompletableFuture上注册一个操作，该操作会在CompletableFuture完成执行后使用它的返回值
        CompletableFuture[] completableFutures = pricesStream.map(future -> future.thenAccept(System.out::println))
                .toArray(size -> new CompletableFuture[size]);
        CompletableFuture.allOf(completableFutures).join();
        long duration = ((System.nanoTime() - start) / 1_000_000);
        //System.out.println(duration);
    }
}
