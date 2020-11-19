package com.example.eurekaclient;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.junit.Test;


public class SyncProducer {
    @Test
    public static void main(String[] args) throws Exception{
        DefaultMQProducer producer = new DefaultMQProducer("ConsumerGroup");
        producer.setNamesrvAddr("192.168.79.130:9876");
        producer.start();
        for(int i = 0;i < 100;i++){
            Message msg = new Message("TopicTest","TagA",("HelloRocket"+i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            SendResult sendResult = producer.send(msg);
            System.out.println(sendResult);
        }
        producer.shutdown();
    }
}
