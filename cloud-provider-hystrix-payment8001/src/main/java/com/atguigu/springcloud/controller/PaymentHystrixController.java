package com.atguigu.springcloud.controller;

import cn.hutool.core.util.IdUtil;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentHystrixController {
    @Value("${server.port}")
    private String serverPort;

    @Resource
    PaymentService paymentService;

    @GetMapping(value = "/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        String result = paymentService.paymentInfo_OK(id);
        log.info("*********result: "+result);
        return result;
    }

    @GetMapping(value = "/payment/hystrix/timeout/{id}")
    public String paymentInfo_Timeout(@PathVariable("id") Integer id){
        String result = paymentService.paymentInfo_Timeout(id);
        log.info("*********result: "+result);
        return result;
    }

    @GetMapping(value = "/payment/circuit/{id}")
    public String paymentCircuitBreak(@PathVariable("id") Integer id){
        String result = paymentService.paymentCircuitBreak(id);
        log.info("*********result: "+result);
        return result;
    }
}
