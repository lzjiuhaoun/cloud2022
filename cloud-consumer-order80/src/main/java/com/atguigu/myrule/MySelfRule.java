package com.atguigu.myrule;

import com.netflix.loadbalancer.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Author:lzj
 * Create:2022-04-14-13:59
 * Describe:
 */
@Configuration
public class MySelfRule {
    @Bean
    public IRule myRule() {
        return new RandomRule();//（负载均衡）定义为【随机】算法
//        return new RetryRule();//（负载均衡）定义为【重试】算法
//        return new WeightedResponseTimeRule();//（负载均衡）定义为【权重选择】
//        return new BestAvailableRule();//（负载均衡）定义为【过滤，选择并发量最小的服务】
//        return new ZoneAvoidanceRule();//默认规则,复合判断server所在区域的性能和server的可用性选择服务器
    }
}
