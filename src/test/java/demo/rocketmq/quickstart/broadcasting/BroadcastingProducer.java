package demo.rocketmq.quickstart.broadcasting;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zn on 2019/5/3.
 */
public class BroadcastingProducer {

    private static String NAMESRV_ADDRESS="127.0.0.1:9876";

    public static void main(String[] args) throws InterruptedException, RemotingException, MQClientException, MQBrokerException, UnsupportedEncodingException {
        //1、创建DefaultMQProducer
        DefaultMQProducer producer =new DefaultMQProducer("Topic_Broadcasting_group");
        //2 设置nameserver地址
        producer.setNamesrvAddr(NAMESRV_ADDRESS);
        //3开启DefaultMqProducer
        producer.start();
        //创建消息message
        List<Message> messageList=new ArrayList<Message>();
        for(int i=0;i<10;i++) {
            Message message = new Message("Topic_Broadcasting_Demo",//主题
                    "TagA",//用于消息过滤
                    "KeyA",//消息的唯一值
                    ("hello!"+i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            messageList.add(message);
        }
        //5发送消息
        SendResult result =producer.send(messageList);
        System.out.println(result);
        producer.shutdown();

    }


}
