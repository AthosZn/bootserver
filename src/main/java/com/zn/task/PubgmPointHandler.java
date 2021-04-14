package com.zn.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by zn on 2019/3/11.
 * 用于处理pubg 积分更新数据
 */
@Service
public class PubgmPointHandler {

    private static final Logger logger = LoggerFactory.getLogger("EVENT_PUBGM_LOG");


    private static BlockingQueue<String> queue = new LinkedBlockingQueue<String>();


    private ExecutorService executorService = Executors.newFixedThreadPool(60);


    public static BlockingQueue<String> getQueue() {
        return queue;
    }



    @PostConstruct
    private void initAndStart() {
        logger.info("====== init start ======");

        //然后开始执行更新
        Thread thread=new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    while (true){
                        String pubgmSendGiftInfo = queue.take();
                        executorService.execute(new Runnable() {
                            @Override
                            public void run() {
                                logger.info("消息内容："+pubgmSendGiftInfo);

                            }
                        });
                    }
                } catch (InterruptedException e) {
                    logger.error("处理pubg积分更新error",e);
                }
            }
        });
        //暂时这么设置 方便关闭
        //thread.setDaemon(true);
        thread.start();

    }



}
