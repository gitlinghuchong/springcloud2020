package com.atguigu.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: shihang
 * @Date: 2020/3/15 13:50
 */
@Configuration
public class GateConfig {
    @Bean
    public RouteLocator custonRouteLocator(RouteLocatorBuilder routeLocatorBuilder){
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        routes.route("path_routh_atguigu",r->r.path("/guonei").uri("https://news.baidu.com/guonei")).build();
        return routes.build();
    }
}
