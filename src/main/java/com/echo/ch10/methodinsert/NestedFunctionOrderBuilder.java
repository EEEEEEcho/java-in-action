package com.echo.ch10.methodinsert;

import com.echo.ch10.Order;
import com.echo.ch10.Stock;
import com.echo.ch10.Trade;

import java.util.stream.Stream;

public class NestedFunctionOrderBuilder {
    public static Order order(String customer, Trade... trades){
        Order order = new Order();
        order.setConsumer(customer);
        Stream.of(trades).forEach(order::addTrade);
        return order;
    }

    public static Trade buy(int quantity, Stock stock,double price){
        return buildTrade(quantity,stock,price, Trade.Type.BUY);
    }

    public static Trade sell(int quantity,Stock stock,double price){
        return buildTrade(quantity,stock,price, Trade.Type.SELL);
    }

    public static Trade buildTrade(int quantity,Stock stock,double price,Trade.Type buy){
        Trade trade = new Trade();
        trade.setQuantity(quantity);
        trade.setType(buy);
        trade.setPrice(price);
        return trade;
    }
    //桩方法
    public static double at(double price){
        return price;
    }

    public static Stock stock(String symbol,String market){
        Stock stock = new Stock();
        stock.setSymbol(symbol);
        stock.setMarket(market);
        return stock;
    }
    //桩方法
    public static String on(String market){
        return market;
    }

    public static void main(String[] args) {
        Order order = order("BigBank",
                buy(80, stock("IBM", "NYSE"), at(125.00)),
                sell(50, stock("GOOGLE", "NASDAQ"), at(375.00))
        );
    }
}
