package com.echo.ch17.rxjavademo;

import com.echo.ch17.TempInfo;
import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class Demo1 {
    public static void main(String[] args) {
        Observable<String> strings = Observable.just("first", "second");
        Observable<Long> onePerSec = Observable.interval(1, TimeUnit.SECONDS);
        //由守护线程来执行 一秒过后的发布者发布的事件。所以主线程关闭之后，守护线程也就关闭了
        //onePerSec.subscribe(i -> System.out.println(TempInfo.fetch("New York")));
        //一般生产环境这样使用
        onePerSec.blockingSubscribe(
                i -> System.out.println(TempInfo.fetch("New Work!"))
        );
    }
}
