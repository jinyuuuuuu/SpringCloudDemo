package com.example.eurekaclient;

import org.apache.rocketmq.client.consumer.store.OffsetSerializeWrapper;
import org.apache.rocketmq.common.MixAll;
import org.apache.rocketmq.common.message.MessageQueue;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Author: JinYu
 * @Description: 此类用于在使用DefaultMQPullCONSUMER时对Offset信息进行持久化
 * @param
 * @Return: null
 * @Date: 19:27 2020/12/1
*/
public class LocalOffsetStoreExt {
    //
    private final String groupName;
    //持久化路径
    private final String storePath;
    //用于存储Offset
    private ConcurrentHashMap<MessageQueue,AtomicLong> offsetTable = new ConcurrentHashMap<>();
    //实例化时需要注入的属性
    public LocalOffsetStoreExt(String groupName, String storePath) {
        this.groupName = groupName;
        this.storePath = storePath;
    }
    /**
     * @Author: JinYu
     * @Description: 从本地加载Offset
     * @param
     * @Return: void
     * @Date: 14:14 2020/12/2
    */
    public void load(){
        OffsetSerializeWrapper offsetSerializeWrapper = this.readLocalOffset();
        if(offsetSerializeWrapper != null && offsetSerializeWrapper.getOffsetTable() != null){
            offsetTable.putAll(offsetSerializeWrapper.getOffsetTable());
            for(MessageQueue mq : offsetSerializeWrapper.getOffsetTable().keySet()){
                AtomicLong Offset = offsetSerializeWrapper.getOffsetTable().get(mq);
                System.out.printf("Load Consumer's Offset,{} {} {}\n",this.groupName,mq,Offset.get());
            }
        }
    }
    /**
     * @Author: JinYu
     * @Description: 更新Offset，如果存在旧的Offset就更新，不存在就说明MQ也不在OffsetTable中就放入
     * @param mq
     * @param offset
     * @Return: void
     * @Date: 14:14 2020/12/2
    */
    public void updateOffset(MessageQueue mq,long offset){
        if(mq != null){
            AtomicLong offsetOld = this.offsetTable.get(mq);
            if(null == offsetOld){
                this.offsetTable.putIfAbsent(mq,new AtomicLong(offset));
            }else {
                offsetOld.set(offset);
            }
        }
    }
    /**
     * @Author: JinYu
     * @Description: 获取该MQ的Offset
     * @param mq
     * @Return: long
     * @Date: 14:16 2020/12/2
    */
    public long readOffset(final MessageQueue mq){
        if(mq != null){
            AtomicLong offset = this.offsetTable.get(mq);
            if(offset != null){
                return offset.get();
            }
        }
        return 0;
    }
    /**
     * @Author: JinYu
     * @Description: 将目前存放的所有OffsetTable持久化
     * @param mqs
     * @Return: void
     * @Date: 14:16 2020/12/2
    */
    public void persistAll(Set<MessageQueue> mqs){
        if(null == mqs || mqs.isEmpty())
            return;
        OffsetSerializeWrapper offsetSerializeWrapper = new OffsetSerializeWrapper();
        //遍历offsetTable，将键值对一个个放入Wrapper中
        for(Map.Entry<MessageQueue,AtomicLong> entry : this.offsetTable.entrySet()){
            if(mqs.contains(entry.getKey())){
                AtomicLong offset = entry.getValue();
                offsetSerializeWrapper.getOffsetTable().put(entry.getKey(),offset);
            }
        }
        //将Wrapper中所有的都转化为Json然后持久化到指定路径去
        String jsonString = offsetSerializeWrapper.toJson(true);
        if(jsonString != null){
            try{
                MixAll.string2File(jsonString,this.storePath);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    /**
     * @Author: JinYu
     * @Description: 读取本地德Offset文件
     * @param
     * @Return: org.apache.rocketmq.client.consumer.store.OffsetSerializeWrapper
     * @Date: 14:18 2020/12/2
    */
    private OffsetSerializeWrapper readLocalOffset(){
        String content = null;
        try{
            content = MixAll.file2String(this.storePath);
        }catch (IOException e){
            e.printStackTrace();
        }
        if(null == content || content.length() == 0){
            return null;
        }else {
            OffsetSerializeWrapper offsetSerializeWrapper = null;
            try {
                offsetSerializeWrapper = OffsetSerializeWrapper.fromJson(content,OffsetSerializeWrapper.class);
            }catch (Exception e){
                e.printStackTrace();
            }
            return offsetSerializeWrapper;
        }
    }
}
