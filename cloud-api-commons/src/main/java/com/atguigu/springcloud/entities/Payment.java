package com.atguigu.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Author:lzj
 * Create:2022-04-10-11:05
 * Describe:数据库表对应实体类
 */
//idea安装lombok插件并在pom文件中引入依赖
@Data//可以省去 setter 与 getter、toString 方法的书写，在编译时，会自动添加到class中。
@AllArgsConstructor//生成一个参数为所有实例变量的构造方法
@NoArgsConstructor//生成的构造器无参数
public class Payment implements Serializable {//implements Serializable继承接口实现序列化 什么情况下需要序列化：1.当你想把的内存中的对象写入到硬盘的时候。2.当你想用套接字在网络上传送对象的时候。3.当你想通过RMI传输对象的时候。
    private Long id;
    private String serial;
}
