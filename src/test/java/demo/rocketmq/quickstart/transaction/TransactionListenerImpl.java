package demo.rocketmq.quickstart.transaction;

import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zn on 2019/5/5.
 */
public class TransactionListenerImpl implements TransactionListener {

    //纯属对应事务的状态信息 key:事务ID，value:当前事务执行的状态
    private ConcurrentHashMap<String,Integer> localTrans=new ConcurrentHashMap<String,Integer>();

    /**
     * 用于执行本地事务
     * @param msg
     * @param arg
     * @return
     */
    @Override
    public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        //事务ID
        String transactionId= msg.getTransactionId();
        //业务执行
        try {
            System.out.println("处理本地事务 service"+transactionId);
            Thread.sleep(120000);
            System.out.println("正在执行本地事务---成功");
            localTrans.put(transactionId,1);
        } catch (InterruptedException e) {
            e.printStackTrace();
            localTrans.put(transactionId,2);
            return LocalTransactionState.ROLLBACK_MESSAGE;
        }

        return LocalTransactionState.COMMIT_MESSAGE;
    }

    /**
     * 消息回查 上面的事务执行成功后   未收到确认时 回查事务状态
     * @param msg
     * @return
     */
    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt msg) {
          //事务ID
        String transactionId= msg.getTransactionId();
        //获取事务id的状态信息
        Integer status=localTrans.get(transactionId);

        System.out.println("消息回查----transactionId"+transactionId+"status"+status);
        switch(status){
            case 0:
                return LocalTransactionState.UNKNOW;
            case 1:
                return LocalTransactionState.COMMIT_MESSAGE;
            case 2:
                return LocalTransactionState.ROLLBACK_MESSAGE;
        }
        return LocalTransactionState.ROLLBACK_MESSAGE;

    }
}
