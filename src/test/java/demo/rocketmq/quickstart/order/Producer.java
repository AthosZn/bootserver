package demo.rocketmq.quickstart.order;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.List;

/**
 * Created by zn on 2019/5/3.
 */
public class Producer {

    private static String NAMESRV_ADDRESS="127.0.0.1:9876";

    public static void main(String[] args) throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        //1、创建DefaultMQProducer
        DefaultMQProducer producer =new DefaultMQProducer("demo_produce_group");
        //2 设置nameserver地址
        producer.setNamesrvAddr(NAMESRV_ADDRESS);
        //3开启DefaultMqProducer
        producer.start();

        //5发送消息
        //第一个参数：发送的消息信息
        //第二个参数：选择指定的消息队列对象（会将所有消息队列传入进来）
        //第三个参数：指定对应的队列下标
        for(int i=0;i<5;i++) {
            //创建消息message
            Message message=new Message("TopicTest",//主题
                    "TagA",//用于消息过滤
                    "KeyA",//消息的唯一值
                    ("hello rocketmq"+i).getBytes());
            SendResult result = producer.send(message, new MessageQueueSelector() {
                 @Override
                public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                    //获取队列的下标
                    Integer index = (Integer) arg;
                    //获取对应下标的队列
                    return mqs.get(index);
                }
            }, 1);
            System.out.println(result);
        }
        producer.shutdown();

    }


}
