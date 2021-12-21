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
        //创建CompletableFuture对象，她会包含计算的结果
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        //开启一个线程进行计算
        new Thread(() -> {
            double price = calculatePrice(product);
            //需要长时间计算的任务结束并返回结果时，设置Future的返回值
            futurePrice.complete(price);
        }).start();
        //无需等待还没结束的计算，直接返回Future对象
        return futurePrice;
    }
    public double getPrice(String product){
        return calculatePrice(product);
    }
    private double calculatePrice(String product){
        //延时1~3秒
        DelayUtil.delay();
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
