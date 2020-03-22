package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author: shihang
 * @Date: 2020/3/18 1:04
 */
@SpringBootApplication
@EnableDiscoveryClient
public class NacosConfigCenterMain3377 {
    public static void main(String[] args) {
        SpringApplication.run(NacosConfigCenterMain3377.class,args);
    }
}
