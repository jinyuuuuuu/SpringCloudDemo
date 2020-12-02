package com.example.eurekaclient;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.junit.Test;


public class SyncProducer {
    @Test
    public static void main(String[] args) throws Exception{
        OderMessageQueueSelector queueSelector = new OderMessageQueueSelector();
        //设置Producer的GroupName，RocketMQ会扫描该Group下的所有Producer让他们去发送消息
        DefaultMQProducer producer = new DefaultMQProducer("ProducerGroup");
        producer.setNamesrvAddr("192.168.79.130:9876");
        //producer的实例名称，在一个JVM下使用时需设置，默认为Default
        producer.setInstanceName("instance1");
        producer.setRetryTimesWhenSendFailed(3);
        producer.start();
        for(int i = 0;i < 100;i++){
            Message msg = new Message("TopicTest","TagA",("HelloRocket"+i).getBytes(RemotingHelper.DEFAULT_CHARSET));
           //消息延时发送
            msg.setDelayTimeLevel(3);//1s/5s/10s/30s/1m/2m/3m/4m/5m/6m/7m/8m/9m/10m/20m/30m/1h/2h
            //通过MessageQueueSelector来确定该消息发送到哪个MessageQueue中去，
            // 这个send方法最终会调用MessageQueue的select方法
            //通过我们在select中实现的具体逻辑来自主选择MessageQueue
            producer.send(msg,queueSelector,1L);

            //同步发送
            SendResult sendResult = producer.send(msg);
            //异步发送
            /*producer.send(msg, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    System.out.println("Async send success");
                    sendResult.getSendStatus();
                }

                @Override
                public void onException(Throwable e) {
                    e.printStackTrace();
                }
            });*/
            System.out.println(sendResult);
        }
        producer.shutdown();
    }
}
