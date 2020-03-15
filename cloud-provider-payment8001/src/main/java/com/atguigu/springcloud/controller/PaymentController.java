package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;


@RestController
@Slf4j
public class PaymentController {
    @Autowired
    PaymentService paymentService;

    @Value("${server.port}")
    private String servicePort;

    @Resource
    DiscoveryClient discoveryClient;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        System.out.println(servicePort + "*****插入结果:"+result);
        if(result > 0){
            return  new CommonResult(200,servicePort +"插入数据成功了",result);
        }else {
            return  new CommonResult(500,servicePort +"插入数据失败了啊",null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult get(@PathVariable("id")Long id){
        Payment payment = paymentService.getPaymentById(id);
        System.out.println(servicePort +"***查询结果:"+payment.toString());
        log.info("hello,i am log");
        if(payment != null){
            return  new CommonResult(200,servicePort +"查询成功了",payment);
        }else {
            return  new CommonResult(500,servicePort +"查询失败了",null);
        }
    }
    @GetMapping(value = "/payment/discovery")
    public Object discovey(){
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            System.out.println(service);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            System.out.println(instance);
        }

        return discoveryClient;
    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLB(){
        return servicePort;
    }

    @GetMapping(value = "payment/feign/timeout")
    public String paymentFeignTimeout(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return servicePort;
    }
}
