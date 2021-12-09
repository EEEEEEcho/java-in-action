package com.echo.ch10.methodchaining;

import com.echo.ch10.Stock;
import com.echo.ch10.Trade;

public class StockBuilder {
    private final MethodChainingOrderBuilder builder;
    private final Trade trade;
    private final Stock stock = new Stock();

    public StockBuilder(MethodChainingOrderBuilder builder,
                         Trade trade,String symbol){
        this.builder = builder;
        this.trade = trade;
        stock.setSymbol(symbol);
    }

    public TradeBuilderWithStock on(String market){
        stock.setMarket(market);
        trade.setStock(stock);
        return new TradeBuilderWithStock(builder,trade);
    }

}
