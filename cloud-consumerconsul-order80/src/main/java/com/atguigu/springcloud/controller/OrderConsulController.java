package com.atguigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Author:lzj
 * Create:2022-04-14-11:02
 * Describe:
 */
@RestController
public class OrderConsulController {
    public static final String INVOKE_URL = "http://consul-provider-payment"; //consul-provider-payment服务消费者在注册中心的服务名称
    //在集群中，url就只看微服务的名称就是服务提供者在服务注册中心注册的服务名称【具体可在服务提供者的核心配置文件中查看】

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value = "/consumer/payment/consul")
    public String paymentInfo() {
        String result = restTemplate.getForObject(INVOKE_URL + "/payment/consul", String.class);
        System.out.println("消费者调用支付服务(consule)--->result:" + result);
        return result;
    }
}

