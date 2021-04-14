package com.zn.biz.controller;

import com.zn.biz.dao.AccountDao;
import com.zn.biz.dao.BookRepository;
import com.zn.biz.entity.AccountEntity;
import com.zn.cache.RedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.nio.ch.ThreadPool;

import javax.annotation.Resource;
import java.awt.print.Book;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

@Controller
public class SampleController {

    @Autowired
    AccountDao accountDao;

    @Resource
    BookRepository bookRepository;

    @Autowired
    private RedisUtils redisUtils;

    Logger logger = LoggerFactory.getLogger(SampleController.class);
    private final Executor executor =  Executors.newCachedThreadPool();

    @ResponseBody
    @RequestMapping(value = "/")
    String home() {
        return "Hello Docker World";
    }


    @ResponseBody
    @RequestMapping(value = "/redis")
    String redis() {
        for(int i=1;i<500;i++){
            int finalI = i;
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    long time=System.currentTimeMillis();
                    redisUtils.get(finalI +"");
                    redisUtils.get(finalI +"");
                    redisUtils.get(finalI +"");
                    redisUtils.get(finalI +"");
                    redisUtils.get(finalI +"");
                    long endtime=System.currentTimeMillis();
                    System.out.println(finalI+Thread.currentThread().getName()+":"+(endtime-time));
                }
            });
        }
        return "sucess";
    }



    @ResponseBody
    @RequestMapping(value = "/fuck")
    AccountEntity fuckyourself() {
        AccountEntity accountEntity = accountDao.queryAccountById(1);
        return accountEntity;
    }

    @ResponseBody
    @RequestMapping(value = "/test")
    String test(){
        String book= bookRepository.test("1");
        return book;
    }

}


