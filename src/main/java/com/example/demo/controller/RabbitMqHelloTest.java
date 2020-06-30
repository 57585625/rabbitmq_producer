package com.example.demo.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zhangyu
 * @className RabbitMqHelloTest
 * @description TODO
 * @date 2020-05-13 10:05
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMqHelloTest {

    @Autowired
    private SenderController senderController;

    @Test
    public void hello() throws Exception {
        for (int i =0 ;i<100;i++)
            senderController.send();
    }


}
