package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Author:lzj
 * Create:2022-04-14-18:51
 * Describe:
 */
@SpringBootApplication
@EnableFeignClients // 启动feign客户端
public class OrderFeignMain80 {

    public static void main(String[] args) {
        SpringApplication.run(OrderFeignMain80.class, args);
        System.out.println("启动成功");

    }

}
