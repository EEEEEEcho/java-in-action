package com.echo.ch15;

import java.util.ArrayList;
import java.util.List;

public class SimpleCell implements Publisher<Integer>,Subscriber<Integer>{
    private int value = 0;
    private String name;
    private List<Subscriber> subscribers = new ArrayList<>();

    public SimpleCell(String name) {
        this.name = name;
    }

    @Override
    public void subscribe(Subscriber<? super Integer> subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void onNext(Integer newValue) {
        //更新自己的值
        this.value = newValue;
        System.out.println(this.name + ": " + this.value);
        //通知所有的订阅者
        notifyAllSubscribers();
    }

    //通知所有订阅者新值的产生
    private void notifyAllSubscribers(){
        subscribers.forEach(subscriber -> subscriber.onNext(this.value));
    }

    public static void main(String[] args) {
        SimpleCell c3 = new SimpleCell("C3");
        SimpleCell c2 = new SimpleCell("C2");
        SimpleCell c1 = new SimpleCell("C1");

        c1.subscribe(c3);

        c1.onNext(20);
        c2.onNext(20);

    }
}
