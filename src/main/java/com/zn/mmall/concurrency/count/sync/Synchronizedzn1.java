package com.zn.mmall.concurrency.count.sync;

import com.zn.mmall.concurrency.util.MyLog;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zn on 2019/5/22.
 */
@Slf4j
public class Synchronizedzn1 {


    public void test1() {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                MyLog.info("test1 -"+i);
            }
        }
    }

    //修饰一个方法 同步方法
    public synchronized void test2() {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                MyLog.info("test1 -"+i);
            }
        }
    }

    public static void main(String[] args){
        Synchronizedzn1 zn1=new Synchronizedzn1();
        ExecutorService executorService= Executors.newCachedThreadPool();

    }

}
