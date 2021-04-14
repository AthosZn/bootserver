package com.zn.mmall.concurrency.count.atomic;

import com.zn.mmall.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * Created by zn on 2019/5/22.
 */
@Slf4j
@ThreadSafe
public class AtomicIntegerFieldUpdaterExm {
    private static AtomicIntegerFieldUpdater<AtomicIntegerFieldUpdaterExm> updater =
            AtomicIntegerFieldUpdater.newUpdater(AtomicIntegerFieldUpdaterExm.class,"count");


    public volatile int count=100;

    private static AtomicIntegerFieldUpdaterExm zn5=new AtomicIntegerFieldUpdaterExm();


    public static void main(String[] args){
        if(updater.compareAndSet(zn5,100,120)){
            System.out.println("update success 1"+zn5.getClass());
        }
        if(updater.compareAndSet(zn5,100,120)){
            System.out.println("update success 2"+zn5.getClass());
        }else{
            System.out.println("update failed"+zn5.getClass());

        }
    }
}
