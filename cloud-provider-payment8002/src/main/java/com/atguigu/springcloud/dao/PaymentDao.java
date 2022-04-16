package com.atguigu.springcloud.dao;

import com.atguigu.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Author:lzj
 * Create:2022-04-10-11:19
 * Describe:dao操作数据库接口增删改查【接口作用：规范写法】
 */
@Mapper
public interface PaymentDao {
    //增加
    int create(Payment payment);

    //增加测试
    int add();

    //根据id查询信息
    Payment getPaymentById(@Param("id") long id);


}
