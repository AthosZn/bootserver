package com.zn.mmall.concurrency.count.atomic;

import com.zn.mmall.concurrency.annoations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by zn on 2019/5/22.
 */
@ThreadSafe
public class AtomicReferenceExm {

    private static AtomicReference<Integer> count=new AtomicReference<>();

    public static void main(String[] args){
        count.compareAndSet(0,2);
        count.compareAndSet(0,1);
        count.compareAndSet(1,3);
        count.compareAndSet(2,4);
        count.compareAndSet(3,5);
        System.out.println("count:"+count.get());
    }

}
