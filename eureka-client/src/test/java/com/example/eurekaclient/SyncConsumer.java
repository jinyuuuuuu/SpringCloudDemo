package com.example.eurekaclient;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;

import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.util.List;

public class SyncConsumer {
    public static void main(String[] args) throws Exception{
        //设置Consumer的GroupName，RocketMQ会扫描该Group下的所有Consumer让他们去消费消息
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("ConsumerGroup");
        //只要该NameServer下有一个主或从的Producer发送的Broker就行
        consumer.setNamesrvAddr("192.168.10.1:9876");
        //从哪去消费，这里明确了在全新的ConsumerGroup中去消费
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        //设置消息模板
        consumer.setMessageModel(MessageModel.BROADCASTING);
        //订阅哪个Topic与Topic下的哪些Tag，假如Tag为空默认订阅全部
        consumer.subscribe("TopicTest","");
        //注册一个监听器，去获取Broker中的消息
        consumer.registerMessageListener((List<MessageExt> msgs,ConsumeConcurrentlyContext context) ->{
                System.out.println(Thread.currentThread().getName()+" Receive New Message:"+msgs+"%n");
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;

        });
        //开启实例
        consumer.start();
    }
}
