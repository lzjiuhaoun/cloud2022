package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Author:lzj
 * Create:2022-04-10-14:02
 * Describe:
 */
@RestController//@Controller+@ResponseBody两个注解的结合，方法返回的json数据，直接显示在前端页面上
@Slf4j
public class PaymentController {
    @Resource//注意是从cloud包中导入
    private DiscoveryClient discoveryClient;//对于注册进eureka里面的微服务，可以通过服务发现来获得该服务的信息

    @Value("${server.port}")//@Value:获取配置文件中某个参数的值
    private String serverPort;

    @Resource
    private PaymentService paymentService;

    @PostMapping(value = "/payment/create")//@PostMapping:映射一个post请求。value：请求路径
    public CommonResult create(@RequestBody Payment payment) {//@RequestBody主要用来接收前端传递给后端的json字符串中的数据的(请求体中的数据的)，所以只能发送POST请求。
        int result = paymentService.create(payment);
        log.info("************插入结果：" + result);
        if (result > 0) {
            return new CommonResult(200, "插入数据库成功!，服务端口serverPort：" + serverPort, result);
        } else {
            return new CommonResult(444, "插入数据失败！", null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")//@PostMapping:映射一个post请求。value：请求路径
    public CommonResult<Payment> getPaymentById(@PathVariable("id") long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("************查询结果：" + payment);
        if (payment != null) {
            return new CommonResult(200, "查询成功!，服务端口serverPort:" + serverPort, payment);
        } else {
            return new CommonResult(444, "失败！[没有对应id：" + id + "]", null);
        }
    }


    @GetMapping(value = "/payment/add")//@PostMapping:映射一个post请求。value：请求路径
    public CommonResult add() {
        int result = paymentService.add();
        if (result > 0) {
            return new CommonResult(200, "插入数据库成功!，服务端口serverPort：" + serverPort, result);
        } else {
            return new CommonResult(444, "插入数据失败！", null);
        }
    }

    @GetMapping(value = "/payment/discovery")
    public Object discovery() {
        //获取服务列表的信息
        List<String> services = discoveryClient.getServices();
        //打印信息：
        for (String element : services) {
            log.info("*****element: " + element);
        }

        //根据微服务名称获取微服务的服务列表信息
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        //打印信息
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId() + "\t" + instance.getHost() + "\t" + instance.getPort() + "\t" + instance.getUri());
        }

        return this.discoveryClient;
    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLB() {
        return serverPort;
    }

}
