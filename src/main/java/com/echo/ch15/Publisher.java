package com.echo.ch15;

public interface Publisher <T>{
    void subscribe(Subscriber<? super T> subscriber);
}
