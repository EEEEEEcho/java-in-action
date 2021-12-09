package com.echo.ch10;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private String consumer;
    private List<Trade> trades = new ArrayList<>();

    public void addTrade(Trade trade){
        trades.add(trade);
    }

    public double getValue(){
        return trades.stream().mapToDouble(Trade::getValue).sum();
    }

    public String getConsumer() {
        return consumer;
    }

    public void setConsumer(String consumer) {
        this.consumer = consumer;
    }
}
