package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Author:lzj
 * Create:2022-04-14-19:03
 * Describe:
 */

@Component //注入bean管理
// 定义feign客户端
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")//服务提供者的微服务名称
public interface PaymentFeignService {
    @GetMapping(value = "/payment/get/{id}")
    CommonResult<Payment> getPaymentById(@PathVariable("id") Long id); //服务提供者业务方法

    @GetMapping(value = "/payment/feign/timeout")
    String paymentFeignTimeout();
}


