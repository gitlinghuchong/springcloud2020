package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @Author: shihang
 * @Date: 2020/3/15 5:05
 */
@Component
public class PaymentFallbackService implements PaymentHystrixService{

    @Override
    public String paymentInfo_OK(Integer id) {
        return "paymentInfo_OK  ~~~~(>_<)~~~~  fallback";
    }

    @Override
    public String paymentInfo_Timeout(Integer id) {
        return "paymentInfo_Timeout ~~~~(>_<)~~~~  fallback";
    }
}
