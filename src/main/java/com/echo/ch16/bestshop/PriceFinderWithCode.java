package com.echo.ch16.bestshop;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

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
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPriceByQuote(product), executor))
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(
                        quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)
                ))
                .collect(Collectors.toList());
        return null;
    }
}
