package com.example.demo.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zhangyu
 * @className TopicRabbitTest
 * @description TODO
 * @date 2020-05-13 16:01
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TopicRabbitTest {


    @Autowired
    private SenderController senderController;

    @Test
    public void topicExchange() throws Exception {
        for (int i =0 ;i<10;i++) {
            senderController.send1();
            senderController.send2();
        }
    }
}
