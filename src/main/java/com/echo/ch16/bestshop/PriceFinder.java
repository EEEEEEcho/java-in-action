package com.echo.ch16.bestshop;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class PriceFinder {
    private final List<Shop> shops = List.of(new Shop("BestPrice"),
                new Shop("LetsSaveBig"),
                new Shop("MyFavoriteShop"),
                new Shop("BuyItAll"),

                new Shop("WoErMa"),
                new Shop("HuaLian"),
                new Shop("WuMei"),
                new Shop("YongHui"),

                new Shop("TianMao"),
                new Shop("SuNing"),
                new Shop("JingDong"),
                new Shop("TaoBao"),

                new Shop("Abandon"),
                new Shop("Bee"),
                new Shop("Client"),
                new Shop("Dream")
    );
    //自定义线程池
    private final Executor executor = Executors.newFixedThreadPool(
            Math.min(shops.size(), 100),    //线程池中的线程数量为商店数目和100之间的最小值
            (Runnable r) -> {               //定义线程池中的线程行为，使用守护线程，这种方式不会阻止程序的关停
                Thread t = new Thread(r);
                t.setDaemon(true);
                return t;
            });

    public PriceFinder(){
    }


    public List<String> findPriceByStream(String product){
        //这样的查询方式是顺序进行的，一个查询操作会阻塞另一个。
        return shops.stream()
                .map(shop -> String.format("%s price is %.2f",shop.getShopName(),shop.getPrice(product)))
                .collect(Collectors.toList());
    }

    public List<String> findPriceByParallelStream(String product){
        return shops.parallelStream()
                .map(shop -> String.format("%s price is %.2f",shop.getShopName(),shop.getPrice(product)))
                .collect(Collectors.toList());
    }

    public List<String> findPriceByComFuture(String product){
        List<CompletableFuture<String>> futures = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getShopName() + " price is " + shop.getPrice(product)))
                .collect(Collectors.toList());
        return futures.stream()
                .map(CompletableFuture::join)   //使用join方法等待所有的异步操作结束
                .collect(Collectors.toList());
    }

    public List<String> findPriceByExecutor(String product){
        List<CompletableFuture<String>> futures = shops.stream()
                .map(shop ->
                        CompletableFuture.supplyAsync(() -> shop.getShopName() + " price is " + shop.getPrice(product)
                        ,executor))         //指定线程池
                .collect(Collectors.toList());
        return futures.stream()
                .map(CompletableFuture::join)   //使用join方法等待所有的异步操作结束
                .collect(Collectors.toList());
    }
}
