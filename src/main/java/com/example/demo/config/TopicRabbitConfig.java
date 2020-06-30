package com.example.demo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhangyu
 * @className TopicRabbitConfig
 * @description topic 交换机配置类，topic 是RabbitMQ中最灵活的一种方式，可以根据routing_key自由的绑定不同的队列。
 * @date 2020-05-13 15:45
 */
@Configuration
public class TopicRabbitConfig {

    private final static String queue = "topic.queue1";
    private final static String queue2 = "topic.queue2";

    /**
     * 使用spring bean 创建消息队列1
     * @return
     */
    @Bean
    public Queue createQueue(){
        return new Queue(queue);
    }

    /**
     * 使用spring bean 创建消息队列2
     * @return
     */
    @Bean
    public Queue createQueue2(){
        return new Queue(queue2);
    }

    /**
     * 使用spring bean 创建topic交换机
     * @return
     */
    @Bean
    TopicExchange exchange() {
        return new TopicExchange("exchange");
    }

    /**
     * 将交换机和队列通过匹配符方式建立起关联，topic.queue1 匹配规则表示topic.queue1队列只能接收自己队列的消息。
     * @param createQueue
     * @param exchange
     * @return
     */
    @Bean
    Binding bindingExchangeMessage(Queue createQueue, TopicExchange exchange) {
        return BindingBuilder.bind(createQueue).to(exchange).with("topic.queue1");
    }

    /**
     * 将交换机和队列通过匹配符方式建立起关联，topic.# 匹配规则表示topic.queue2队列，能接受队列名称是topic开头的所有队列的消息。
     * @param createQueue2
     * @param exchange
     * @return
     */
    @Bean
    Binding bindingExchangeMessages(Queue createQueue2, TopicExchange exchange) {
        return BindingBuilder.bind(createQueue2).to(exchange).with("topic.#");
    }


}
