package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * Author:lzj
 * Create:2022-04-15-14:46
 * Describe:
 */
@Component //注入bean管理【一定要注意添加】
public class PaymentFallbackService implements PaymentHystrixService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "**********来自PaymentFallbackService-【paymentInfo_OK】fall back 的服务降级兜底方法***********";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "**********来自PaymentFallbackService-【paymentInfo_TimeOut】fall back 的服务降级兜底方法***********";
    }
}
