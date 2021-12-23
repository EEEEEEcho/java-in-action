package com.echo.ch16.bestshop;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PriceFinderWithCode {
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

    /**
     * 这个操作是最耗时的
     * @param product 要生产的商品
     * @return
     */
    public List<String> findPrices(String product){
        return shops.stream()
                .map(shop -> shop.getPriceByQuote(product)) //这里有延时操作
                .map(Quote::parse)  //将转换后的字符串变成Quote对象
                .map(Discount::applyDiscount)   //为每个Quote应用折扣服务。有延迟操作
                .collect(Collectors.toList());
    }

    public List<String> findPricesByExecutor(String product){
        List<CompletableFuture<String>> collect = shops.stream()
                //增加异步任务，获取商品的价格
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPriceByQuote(product), executor))
                //将future中的商品价格转换成Quote对象
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(
                        //对future中的quote对象增加打折计算的异步任务
                        quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)
                ))
                .collect(Collectors.toList());
        return collect.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }
    public List<Double> testCombine(String product){
        List<CompletableFuture<Double>> collect = shops.stream()
                //提交一个异步任务计算价格，线程1
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product),executor))
                .map(future -> future.thenCombine(
                        //提交一个不相关的任务计算汇率,线程2
                        CompletableFuture.supplyAsync(() -> {
                            return 0.38;
                        }, executor),
                        //在两个任务都完成之后，执行价格 * 汇率，回线程1整合
                        (price, rate) -> price * rate
                ).orTimeout(3, TimeUnit.SECONDS))//如果任务没有在3秒内完成。就会抛出TimeOutException异常
                .collect(Collectors.toList())
                ;
        return collect.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

    public Future<Double> timeOutDemo(Shop shop,String product){
        return CompletableFuture.supplyAsync(() -> shop.getPrice(product))
                .thenCombine(
                        CompletableFuture.supplyAsync(
                                () -> {
                                    return 0.3;
                                }
                        ).completeOnTimeout(0.5, 1, TimeUnit.SECONDS),//如果1秒还未返回结果，使用默认值0.5继续计算
                        (price, rate) -> price * rate
                ).orTimeout(3, TimeUnit.SECONDS);
    }

    public Stream<CompletableFuture<String>> findPricesStream(String product){
        return shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPriceByQuote(product),executor))
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(
                        quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote),executor)
                ));
    }
}
