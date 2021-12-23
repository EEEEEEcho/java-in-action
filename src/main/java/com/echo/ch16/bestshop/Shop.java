package com.echo.ch16.bestshop;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class Shop {
    private String shopName;
    public Shop(String shopName){
        this.shopName = shopName;
    }
    public Future<Double> getPriceAsync(String product){
//        //创建CompletableFuture对象，她会包含计算的结果
//        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
//        //开启一个线程进行计算
//        new Thread(() -> {
//            try {
//                double price = calculatePrice(product);
//                //需要长时间计算的任务结束并返回结果时，设置Future的返回值
//                //如果正常操作就完成Future操作并设置商品价格
//                //throw new Exception("product is unavailable");
//                futurePrice.complete(price);
//            }
//            catch (Exception ex){
//                //否则就抛出导致失败的异常，完成这次Future操作
//                futurePrice.completeExceptionally(ex);
//            }
//        }).start();
//        //无需等待还没结束的计算，直接返回Future对象
//        return futurePrice;
        //一行简写,提交的Supplier任务会由ForkJoinPool池子中的某个执行线程执行
        return CompletableFuture.supplyAsync(() -> calculatePrice(product));
    }

    public double getPrice(String product){
        return calculatePrice(product);
    }

    public String getPriceByQuote(String product){
        double price = calculatePrice(product);
        Discount.Code code = Discount.Code.values()[new Random().nextInt(Discount.Code.values().length)];
        return String.format("%s:%.2f:%s",shopName,price,code);
    }

    private double calculatePrice(String product){
        //延时1秒
        //.delay();
        //随机延迟
        DelayUtil.randomDelay();
        //返回计算结果
        return new Random().nextDouble() * product.charAt(0) + product.charAt(1);
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
}
