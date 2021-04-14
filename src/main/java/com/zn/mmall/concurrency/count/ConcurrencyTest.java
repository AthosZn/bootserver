package com.zn.mmall.concurrency.count;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by zn on 2019/5/21.
 */
public class ConcurrencyTest {

    //请求总数
    public static int clientTotal=1000;

    public static int threadTotal=50;

    public static int count=0;


    private static void add(){

        System.out.println("add"+count++);
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService excutorService= Executors.newCachedThreadPool();
        final Semaphore semaphore=new Semaphore(threadTotal);
//        与CountDownLatch的第一次交互是主线程等待其他线程。主线程必须在启动其他线程后立即调用
//        CountDownLatch.await()方法。这样主线程的操作就会在这个方法上阻塞，直到其他线程完成各自的任务。
//        其他N 个线程必须引用闭锁对象，因为他们需要通知CountDownLatch对象，他们已经完成了各自的任务。
//        这种通知机制是通过 CountDownLatch.countDown()方法来完成的；每调用一次这个方法，在构造函数中
//        初始化的count值就减1。所以当N个线程都调 用了这个方法，count的值等于0，然后主线程就能通过await()
//        方法，恢复执行自己的任务。
        final CountDownLatch countDownLatch=new CountDownLatch(clientTotal);
        for(int i=0;i<clientTotal;i++){
            excutorService.execute(()->{
                try {
                    semaphore.acquire();
                    add();
                   semaphore.release();
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.printf("semaphore.acquire() error"+ e);
                }
                countDownLatch.countDown();

            });
        }
        countDownLatch.await();
        excutorService.shutdown();
        System.out.println("count:"+count);
    }



}
