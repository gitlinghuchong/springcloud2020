package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: shihang
 * @Date: 2020/3/15 3:47
 */
@RestController
@Slf4j
@DefaultProperties(defaultFallback = "paymentGlobalFallbackMethod")
public class OrderHystrixController {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping(value = "/consumer/payment/hystrix/ok/{id}")
    String paymentInfo_OK(@PathVariable("id") Integer id) {
        return paymentHystrixService.paymentInfo_OK(id);
    }

    @HystrixCommand
    @GetMapping(value = "/consumer/payment/hystrix/timeout/{id}")
    String paymentInfo_Timeout(@PathVariable("id") Integer id) {
        int a = 10/0;
        return paymentHystrixService.paymentInfo_Timeout(id);
    }

    public String paymentInfo_TimeoutHandler(Integer id) {
        return "线程池: " + Thread.currentThread().getName() + " 我是80消费者，对方支付系统繁忙,请稍后再试,id: " + id + "\t ~~~~(>_<)~~~~ ";
    }

    public String paymentGlobalFallbackMethod() {
        return "global 异常处理信息，请稍后重试" + "\t ~~~~(>_<)~~~~ ";
    }
}
