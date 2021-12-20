package com.echo.ch13.demo4;

public interface B {
    default void sayHello(){
        System.out.println("Hello from B");
    }
}
