package com.example.eurekaclient;

import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;

import java.util.List;

public class OderMessageQueueSelector implements MessageQueueSelector {
    @Override
    public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
        Integer Id = Integer.parseInt(arg.toString());
        int idMainIndex = Id/100;
        int queueSize = mqs.size();
        int index = idMainIndex%queueSize;
        return mqs.get(index);
    }
}
