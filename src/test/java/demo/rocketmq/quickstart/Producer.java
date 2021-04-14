package demo.rocketmq.quickstart;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * Created by zn on 2019/5/3.
 */
public class Producer {

    private static String NAMESRV_ADDRESS="192.168.3.12:9876";

    public static void main(String[] args) throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        //1、创建DefaultMQProducer
        DefaultMQProducer producer =new DefaultMQProducer("demo_produce_group");
        //2 设置nameserver地址
        producer.setNamesrvAddr(NAMESRV_ADDRESS);
        //3开启DefaultMqProducer
        producer.start();
        //创建消息message
        Message message=new Message("TopicTest",//主题
                "TagA",//用于消息过滤
                "KeyA",//消息的唯一值
                "hello rocketmq".getBytes());
        //5发送消息
        SendResult result =producer.send(message);
        System.out.println(result);
        producer.shutdown();

    }


}
