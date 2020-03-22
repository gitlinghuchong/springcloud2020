package com.atguigu.springclolud.service.impl;

import com.atguigu.springclolud.service.ImessageProvider;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;
import java.awt.image.ImageConsumer;
import java.util.UUID;

/**
 * @Author: shihang
 * @Date: 2020/3/16 23:17
 */
@EnableBinding(Source.class) // 定义消息的推送管道
public class MessageProviderImpl implements ImessageProvider {

    @Resource
    private MessageChannel output;

    @Override
    public String send() {
        String serial = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(serial).build());
        System.out.println("******serial: " + serial);
        return null;
    }
}
