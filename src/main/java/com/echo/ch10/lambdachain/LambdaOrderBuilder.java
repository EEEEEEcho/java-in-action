package com.echo.ch10.lambdachain;

import com.echo.ch10.Order;
import com.echo.ch10.Trade;

import java.util.function.Consumer;

public class LambdaOrderBuilder {
    private Order order = new Order();

    public static Order order(Consumer<LambdaOrderBuilder> consumer){
        LambdaOrderBuilder builder = new LambdaOrderBuilder();
        consumer.accept(builder);
        return builder.order;
    }

    public void forConsumer(String consumer){
        order.setConsumer(consumer);
    }

    public void buy(Consumer<TradeBuilder> consumer){
        trade(consumer, Trade.Type.BUY);
    }

    public void sell(Consumer<TradeBuilder> consumer){
        trade(consumer, Trade.Type.SELL);
    }

    private void trade(Consumer<TradeBuilder> tradeBuilderConsumer, Trade.Type type){
        TradeBuilder tradeBuilder = new TradeBuilder();
        tradeBuilderConsumer.accept(tradeBuilder);
        order.addTrade(tradeBuilder.getTrade());
    }

    public static void main(String[] args) {
        order(o -> {
            o.forConsumer("BigBank");
            o.buy(t -> {
                t.quantity(80);
                t.price(125.00);
                t.stock(s ->{
                    s.symbol("IBM");
                    s.market("NYSE");
                });
            });
            o.sell(t -> {
                t.quantity(50);
                t.price(375.00);
                t.stock(s -> {
                    s.symbol("GOOGLE");
                    s.market("NASDAQ");
                });
            });
        });
    }
}
