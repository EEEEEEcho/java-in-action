package com.echo.ch16.bestshop;

public class Discount {
    public enum Code{
        NONE(0),SLIVER(5),GOLD(10),PLATINUM(15),DIAMOND(20);
        private final int percentage;
        Code(int percentage){
            this.percentage = percentage;
        }
    }

    public static String applyDiscount(Quote quote){
        //应用折扣的时候会触发延迟
        return quote.getShopName() + " price is "  + Discount.apply(quote.getPrice(), quote.getDiscountCode());
    }

    private static double apply(double price,Code code){
        DelayUtil.delay();
        return price * ((100 - price) / 100);
    }
}
