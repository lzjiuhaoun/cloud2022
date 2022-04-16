package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Author:lzj
 * Create:2022-04-15-11:30
 * Describe:
 */
@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class OrderHystirxController {
    @Resource //注入 PaymentHystrixService，就能使用在启动类中定义的feign客户端
    private PaymentHystrixService paymentHystrixService;

    //在服务消费者端调用服务提供者的业务方法
    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id) {
        String result = paymentHystrixService.paymentInfo_OK(id);
        return result;
    }

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    //开启Hystrix降级配置
    //使用指定的fallback兜底方法
//    @HystrixCommand(fallbackMethod = "paymentTimeOutFallbackMethod", commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2500") //客户端允许的等待峰值时间是1.5秒
//    })
    @HystrixCommand //使用全局的fallback兜底方法
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id) {

        //测试运行报错
        // int i = 10 / 0;
        //测试延迟
        String result = paymentHystrixService.paymentInfo_TimeOut(id);
        return result;
    }

    //服务消费者（客户端层）服务降级兜底方法
    public String paymentTimeOutFallbackMethod(@PathVariable("id") Integer id) {
        return "我是消费者80,对方支付系统繁忙请10秒钟后再试或者自己运行出错请检查自己,o(╥﹏╥)o";
    }

    //下面是全局的fallback兜底方法【当此类中有方法运行报错或超时时且没有单独指定fallback方法时，Hystirx就会调用这个全局的fallback方法】
    public String payment_Global_FallbackMethod() {
        return "Global异常处理信息，请稍后再试，/(ㄒoㄒ)/~~";
    }

}

