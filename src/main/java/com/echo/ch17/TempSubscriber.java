package com.echo.ch17;
import java.util.concurrent.Flow.*;

public class TempSubscriber implements Subscriber<TempInfo>{
    private Subscription subscription;

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;   //保存subscribtion
        subscription.request(1);    //发送第一次请求
    }

    @Override
    public void onNext(TempInfo item) {
        System.out.println(item);   //打印接收到的温度数据，并发送下一个数据请求
        subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println(throwable.getMessage());
    }

    @Override
    public void onComplete() {
        System.out.println("Done!");
    }
}
