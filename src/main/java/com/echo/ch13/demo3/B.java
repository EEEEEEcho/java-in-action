package com.echo.ch13.demo3;

public interface B extends A{
    default void hello(){
        System.out.println("Hello from B");
    }
}
