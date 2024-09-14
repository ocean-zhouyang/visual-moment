package com.sjsk.passjava;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KafkaSendTest {
    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate; //如果这里有红色波浪线，那是假错误
    @Test
    public void sendMsg(){
        String topic = "spring_test";
        kafkaTemplate.send(topic,"hello spring boot kafka!");
        System.out.println("发送成功.");
//        while (true){ //保存加载ioc容器
//        }
    }
}