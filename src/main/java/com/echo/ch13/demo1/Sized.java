package com.echo.ch13.demo1;

public interface Sized {
    int size(); //是由继承者实现的
    //默认方法，调用继承者实现的size()方法
    default boolean isEmpty(){
        return size() == 0;
    }
}
