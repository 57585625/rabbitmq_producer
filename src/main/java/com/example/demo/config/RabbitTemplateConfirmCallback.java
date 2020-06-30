package com.example.demo.config;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author zhangyu
 * @className RabbitTemplateConfirmCallback
 * @description TODO
 * @date 2020-05-14 15:49
 */
@Component
public class RabbitTemplateConfirmCallback {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void initRabbitTemplate() {
        // 设置生产者消息确认
    }

    public void confirm(CorrelationData correlationData, boolean b, String s) {
        System.out.println("ack："+s + b);
        if (b) {
            System.out.println("消息到达rabbitmq服务器");
        } else {
            System.out.println("消息可能未到达rabbitmq服务器");
        }
    }
}
