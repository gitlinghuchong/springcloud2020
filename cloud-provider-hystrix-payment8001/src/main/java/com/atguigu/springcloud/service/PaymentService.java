package com.atguigu.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {
    /**
     * 正常访问，肯定ok
     *
     * @param id 请求id
     * @return 字符串
     */
    public String paymentInfo_OK(Integer id) {
        return "线程池: " + Thread.currentThread().getName() + " paymentInfo_OK,id: " + id + "\t O(∩_∩)O哈哈~";
    }

    // 服务降级
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    public String paymentInfo_Timeout(Integer id) {
//        int a = 5/0;
        int timeoutSecond = 3;
        try {
            TimeUnit.SECONDS.sleep(timeoutSecond);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池: " + Thread.currentThread().getName() + " paymentInfo_Timeout,id: " + id + "\t O(∩_∩)O哈哈~" + "耗时" + timeoutSecond + "秒钟";
    }

    public String paymentInfo_TimeoutHandler(Integer id) {
        return "线程池: " + Thread.currentThread().getName() + " 8001系统繁忙,请稍后再试,id: " + id + "\t ~~~~(>_<)~~~~ ";
    }

    // 服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreakFallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),// 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),// 请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),// 时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),// 失败率到多少跳闸
    })
    public String paymentCircuitBreak(Integer id) {
        if (id < 0) {
            throw new RuntimeException("------id不能为负数--------");
        }
        String serial = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "\t" + "调用成功,流水号：" + serial;
    }

    public String paymentCircuitBreakFallback(Integer id) {
        return "id不能为负数，请稍后重试~~~~(>_<)~~~~ id: " + id;
    }
}
