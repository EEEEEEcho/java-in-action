package com.echo.ch09.observer;

import java.util.ArrayList;
import java.util.List;

public class Feed implements Subject{
    private final List<Observer> observers = new ArrayList<>();
    @Override
    public void registerObserver(Observer o) {
        //添加观察者
        this.observers.add(o);
    }

    @Override
    public void notifyObservers(String tweet) {
        observers.forEach(o -> o.notify(tweet));
    }

    public static void main(String[] args) {
        Feed feed = new Feed();
        feed.registerObserver(new NYTimes());
        feed.registerObserver(new Guardian());
        feed.registerObserver(new LeMonde());
        //使用lambda
        feed.registerObserver((String tweet) -> {
            if (tweet != null && tweet.contains("Java")){
                System.out.println("Breaking news in Java Coding ... " + tweet);
            }
        });
        //但实际上，这样并不好，并不会提升可读性
        feed.notifyObservers("The queen said her favourite book is Modern Java In Action");
    }
}
