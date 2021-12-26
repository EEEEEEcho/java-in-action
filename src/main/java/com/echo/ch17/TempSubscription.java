package com.echo.ch17;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Flow.*;

public class TempSubscription implements Subscription {
    private final Subscriber<? super TempInfo> subscriber;
    private final String town;
    private static final ExecutorService executor = Executors.newSingleThreadExecutor();

    public TempSubscription(Subscriber<? super TempInfo> subscriber,String town){
        this.subscriber = subscriber;
        this.town = town;
    }

    @Override
    public void request(long n) {
        //背压，每次仅收取n个数据
//        for (long i = 0L; i < n; i++) {
//            try {
//                subscriber.onNext(TempInfo.fetch(town));
//            }
//            catch (Exception e){
//                subscriber.onError(e);  //如果出错，将出错信息返回给Subscirber
//                break;
//            }
//        }
        executor.submit(() -> {         //另一个线程想subscriber发送下一个元素
            for (long i = 0L; i < n; i++) {
                try {
                    subscriber.onNext(TempInfo.fetch(town));
                }
                catch (Exception e){
                    subscriber.onError(e);  //如果出错，将出错信息返回给Subscirber
                    break;
                }
            }
        });
    }

    @Override
    public void cancel() {
        //如果Subscription被取消了，向Subscriber发送一个信号
        subscriber.onComplete();
    }
}
