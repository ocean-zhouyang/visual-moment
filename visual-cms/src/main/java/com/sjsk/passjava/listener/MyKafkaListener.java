package com.sjsk.passjava.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MyKafkaListener {
//    以下两种方法都行
//    指定监听的主题
//    @KafkaListener(topics = "spring_test")
//    public void receiveMsg(String message){
//        System.out.println("接收到的消息："+message);
//    }
    @KafkaListener(topics = "spring_test")
    public void handleMessage(ConsumerRecord<String, String> record) {
        System.out.println("接收到消息,偏移量为: " + record.offset() + " 消息为: " + record.value());
    }
}
