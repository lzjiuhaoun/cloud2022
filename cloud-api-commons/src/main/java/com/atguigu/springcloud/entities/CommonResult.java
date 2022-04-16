package com.atguigu.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author:lzj
 * Create:2022-04-10-11:14
 * Describe:返回给前端的json封装体
 */
@Data//生成set\get\toString方法
@AllArgsConstructor//生成有参构造方法
@NoArgsConstructor//生成无参构造方法
public class CommonResult<T>{
    private Integer code;//200,404,405...
    private String message;//提示信息
    private T data;//在前台页面上展示

    public CommonResult(Integer code,String message){
        this(code,message,null);
    }
}
