package com.echo.ch09.observer;

public interface Subject {
    public void registerObserver(Observer o);
    public void notifyObservers(String tweet);
}
