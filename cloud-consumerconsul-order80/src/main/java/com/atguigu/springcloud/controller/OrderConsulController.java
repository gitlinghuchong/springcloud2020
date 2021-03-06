package com.atguigu.springcloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class OrderConsulController {
    public static final String INVOKE_UTL = "http://consul-provider-payment";

    @Resource
    RestTemplate restTemplate;

    @GetMapping(value = "/consumer/payment/consul")
    public String paymentInfo(){
        return restTemplate.getForObject(INVOKE_UTL+"/payment/consul",String.class);
    }
}
