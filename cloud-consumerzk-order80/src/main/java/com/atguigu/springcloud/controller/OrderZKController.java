package com.atguigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Author:lzj
 * Create:2022-04-14-9:24
 * Describe:
 */
@RestController
public class OrderZKController {

    //public static final String PaymentSrv_URL = "http://localhost:8001";//单台服务提供者服务器
    public static final String INVOKE_URL = "http://cloud-provider-payment";//集群，cloud-provider-payment为集群服务名称
    //在集群中，url就只看微服务的名称就是服务提供者在服务注册中心注册的服务名称【具体可在服务提供者的核心配置文件中查看】

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/consumer/payment/zk")
    public String paymentInfo() {
        String result = restTemplate.getForObject(INVOKE_URL + "/payment/zk", String.class);//消费者跳转到服务提供者的/payment/zk业务层进行消费
        System.out.println("消费者调用支付服务(zookeeper)--->result:" + result);
        return result;
    }

}
