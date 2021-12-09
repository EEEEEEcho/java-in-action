package com.echo.ch10.methodchaining;

import com.echo.ch10.Trade;

public class TradeBuilderWithStock {
    private final MethodChainingOrderBuilder builder;
    private final Trade trade;

    public TradeBuilderWithStock(MethodChainingOrderBuilder builder, Trade trade) {
        this.builder = builder;
        this.trade = trade;
    }

    public MethodChainingOrderBuilder at(double price){
        trade.setPrice(price);
        return builder.addTrade(trade);
    }
}
