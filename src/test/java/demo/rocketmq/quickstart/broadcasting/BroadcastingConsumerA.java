package demo.rocketmq.quickstart.broadcasting;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by zn on 2019/5/5.
 */
public class BroadcastingConsumerA {

    private static String NAMESRV_ADDRESS="127.0.0.1:9876";


    public static void main(String[] args) throws MQClientException {
        //1、创建defaultmqpushconsumer
        DefaultMQPushConsumer consumer=new DefaultMQPushConsumer("Topic_Broadcasting_group");

        consumer.setNamesrvAddr(NAMESRV_ADDRESS);

        //默认是集群消费模式，即只有一个能消费到  改成广播模式
        consumer.setMessageModel(MessageModel.BROADCASTING);
        //设置消息拉取最大数
        consumer.setConsumeMessageBatchMaxSize(2);


        consumer.subscribe("Topic_Broadcasting_Demo",//指定要消费的主题
                "*");//过滤规则 消费所有标签*
        consumer.setMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                for(MessageExt msg:msgs){
                    String topic=msg.getTopic();
                    String tags=msg.getTags();
                    try {
                        String result=new String(msg.getBody(), RemotingHelper.DEFAULT_CHARSET);
                        System.out.println("COnsumer消费信息--topic："+topic+",tags:"+tags+",result:"+result);

                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                        return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                    }
                }
                //返回消息读取状态
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        consumer.start();
    }



}
