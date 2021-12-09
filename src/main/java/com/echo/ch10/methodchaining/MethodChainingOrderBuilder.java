package com.echo.ch10.methodchaining;

import com.echo.ch10.Order;
import com.echo.ch10.Trade;

public class MethodChainingOrderBuilder {
    public final Order order = new Order(); //由构建器封装的订单对象

    public MethodChainingOrderBuilder(String customer){
        order.setConsumer(customer);    //客户名称
    }

    //这其实相当于又创建了一个订单对象
    public static MethodChainingOrderBuilder forCustomer(String customer){
        return new MethodChainingOrderBuilder(customer);
    }

    public TradeBuilder buy(int quantity){
        return new TradeBuilder(this, Trade.Type.BUY,quantity);
    }

    public TradeBuilder sell(int quantity){
        return new TradeBuilder(this, Trade.Type.SELL,quantity);
    }

    public MethodChainingOrderBuilder addTrade(Trade trade){
        order.addTrade(trade);
        return this;
    }

    public Order end(){
        return order;
    }
}
