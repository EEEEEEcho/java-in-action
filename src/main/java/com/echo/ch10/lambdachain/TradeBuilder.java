package com.echo.ch10.lambdachain;

import com.echo.ch10.Stock;
import com.echo.ch10.Trade;

import java.util.function.Consumer;

public class TradeBuilder {
    private Trade trade = new Trade();

    public void quantity(int quantity){
        trade.setQuantity(quantity);
    }

    public void price(double price){
        trade.setPrice(price);
    }

    public void stock(Consumer<StockBuilder> consumer){
        StockBuilder stockBuilder = new StockBuilder();
        //执行传递过来的lambda
        consumer.accept(stockBuilder);
        trade.setStock(stockBuilder.getStock());
    }

    public Trade getTrade() {
        return trade;
    }
}
