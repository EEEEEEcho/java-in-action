package com.echo.ch13.demo4;

public interface A {
    default void sayHello(){
        System.out.println("Hello from A");
    }
}
