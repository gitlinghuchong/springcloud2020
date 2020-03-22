package com.atguigu.springclolud.controller;

import com.atguigu.springclolud.service.ImessageProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @Author: shihang
 * @Date: 2020/3/16 23:36
 */
@RestController
public class SendMessageController {

    @Resource
    ImessageProvider messageProvider;

    @GetMapping(value = "/sendMessage")
    public String sendMessage() {
        messageProvider.send();
        return "发送mq成功" + UUID.randomUUID();
    }
}
