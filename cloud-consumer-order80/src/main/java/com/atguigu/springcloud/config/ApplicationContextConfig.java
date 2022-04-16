package com.atguigu.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Author:lzj
 * Create:2022-04-10-15:54
 * Describe:
 */
@Configuration
public class ApplicationContextConfig {
    @Bean
    //@LoadBalanced//使用@LoadBalanced注解赋予RestTemplate负载均衡的能力[指定使用服务提供者集群的某个服务器提供的服务，不然注册中心就不知道该使用哪个服务器]
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

