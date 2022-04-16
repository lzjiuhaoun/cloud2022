package com.atguigu.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Author:lzj
 * Create:2022-04-15-10:21
 * Describe:
 */
@Service
public class PaymentService {

    //===================================================服务降价fallback===================================================
    public String paymentInfo_OK(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + "paymentInfo_OK,id:  " + id + "\t" + "O(∩_∩)O哈哈~";
    }

    //启用 Hystrix服务
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler", commandProperties = {
            //设置自身调用超时时间的峰值，峰值内可以正常运行，超过峰值时间走兜底方法：paymentInfo_TimeOutHandler
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    }) //fallbackMethod:兜底方法名
    public String paymentInfo_TimeOut(Integer id) {
        //测试：延时timeNumber秒
        int timeNumber = 1;
        try {
            TimeUnit.SECONDS.sleep(timeNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池：" + Thread.currentThread().getName() + "paymentInfo_TimeOut,id:  " + id + "\t" + "噢哦┗|｀O′|┛ 嗷~~" + "耗时:" + timeNumber + "秒钟";
        //测试运行报错
//        int i=10/0;
//        return "线程池：" + Thread.currentThread().getName() + "paymentInfo_TimeOut,id:  " + id + "\t" + "噢哦┗|｀O′|┛ 嗷~~【运行报错】";
    }

    //兜底方法
    public String paymentInfo_TimeOutHandler(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + "系统繁忙或运行报错，请稍后再试！paymentInfo_TimeOutHandler,id:  " + id + "\t" + "┭┮﹏┭┮....【兜底方法】";
    }

    //===================================================服务熔断break===================================================
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),//是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),//请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),//时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "30"),//失败率达到多少后跳闸
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        if (id < 0) {
            throw new RuntimeException("******id 不能负数");
        }
        String serialNumber = IdUtil.simpleUUID();// UUID.randomUUID().toString()

        return Thread.currentThread().getName() + "\t" + "调用成功，流水号: " + serialNumber;
    }

    //服务熔断的兜底方法：
    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id) {
        return "id 不能负数，请稍后再试，/(ㄒoㄒ)/~~   id: " + id;
    }


}
