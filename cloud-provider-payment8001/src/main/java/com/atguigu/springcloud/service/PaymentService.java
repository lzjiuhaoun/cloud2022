package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * Author:lzj
 * Create:2022-04-10-13:55
 * Describe:业务层接口
 */
public interface PaymentService {
    //增加
    int create(Payment payment);

    //根据id查询信息
    Payment getPaymentById(@Param("id") long id);

    int add();
}
