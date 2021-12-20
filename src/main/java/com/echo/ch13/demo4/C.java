package com.echo.ch13.demo4;

import sun.misc.Unsafe;

public class C implements B,A{

    @Override
    public void sayHello() {
        B.super.sayHello();
    }
}
