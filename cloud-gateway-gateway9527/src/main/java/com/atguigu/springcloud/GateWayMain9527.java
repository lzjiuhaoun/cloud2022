package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Author:lzj
 * Create:2022-04-16-13:16
 * Describe:
 */
@SpringBootApplication
@EnableEurekaClient //激活注册中心的Eurekaf服务
public class GateWayMain9527 {
    public static void main(String[] args) {
        SpringApplication.run(GateWayMain9527.class, args);
    }
}
