package com.echo.ch13.demo3;

public interface A {
    default void hello(){
        System.out.println("Hello from A");
    }
}
