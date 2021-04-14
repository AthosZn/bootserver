package demo.rocketmq.quickstart.transaction;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by zn on 2019/5/3.
 */
public class TransactionProducer {

    private static String NAMESRV_ADDRESS="127.0.0.1:9876";

    public static void main(String[] args) throws InterruptedException, RemotingException, MQClientException, MQBrokerException, UnsupportedEncodingException {
        //1、创建DefaultMQProducer
        TransactionMQProducer producer=new TransactionMQProducer("demo_produce_transaction_group");
        //2 设置nameserver地址
        producer.setNamesrvAddr(NAMESRV_ADDRESS);
        //指定消息监听对象，用于执行本地事务和消息回滚
        TransactionListener transactionListener=new TransactionListenerImpl();
        producer.setTransactionListener(transactionListener);

        //线程池
        ExecutorService executorService=new ThreadPoolExecutor(2,5,100, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(2000),
                new ThreadFactory(){

                    @Override
                    public Thread newThread(Runnable r) {
                        Thread thread= new Thread(r);
                        thread.setName("client-transaction-msg-check-thread");
                        return null;
                    }
                }
          );
        producer.setExecutorService(executorService);

        //3开启DefaultMqProducer
        producer.start();
        //创建消息message
        Message message=new Message("Topic_Transaction_Demo",//主题
                "TagA",//用于消息过滤
                "Key_T",//消息的唯一值
                "hello transaction".getBytes(RemotingHelper.DEFAULT_CHARSET));
        //5发送事务消息
        SendResult result =producer.sendMessageInTransaction(message,"hello-transaction");
        System.out.println(result);
        producer.shutdown();

    }


}
