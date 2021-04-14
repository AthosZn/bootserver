package com.zn.config;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value=RedisListener.SERVICE_NAME)
public class RedisListener implements MessageListener {

    public static final String SERVICE_NAME="com.zn.demo.config.RedisListener";

    @Override
    @Transactional
    public void onMessage(Message message, byte[] pattern) {
        //获取过期的key
        String expireKey = new String(message.getBody());
        System.out.println(expireKey);
    }
}
