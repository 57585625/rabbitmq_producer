package com.example.demo.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.UUID;

/**
 * @author zhangyu
 * @className SenderController
 * @description 模拟消息发送者
 * @date 2020-05-12 17:58
 */
@Component
public class SenderController implements RabbitTemplate.ConfirmCallback{

    private static volatile int count = 0;

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public SenderController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        rabbitTemplate.setConfirmCallback(this);
    }

    public void send() {
        String context = "hello----" + count++;
        System.out.println("send:" + context);
        //往名称为 hello 的queue中发送消息
        this.rabbitTemplate.convertAndSend("hello", context);
    }


    public void send1() throws InterruptedException {
        String context = "hi, i am message 1";
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend("exchange", "topic.queue1", context,correlationData);
        Thread.sleep(2000);
    }

    public void send2() throws InterruptedException {
        String context = "hi, i am messages 2";
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend("exchange", "topic.queue2", context,correlationData);
        Thread.sleep(2000);

    }

    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {
        System.out.println("confirm: " + correlationData.getId() + ",s=" + s + ",b:" + b);
    }
}
