/*
 * Copyright (c) 2016 libing. All rights reserved.
 */
package cn.com.tcsl.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.com.tcsl.reflectdemo.Car;


/**
 * <P>
 * Description:
 * </p>
 * @author libing
 * @version 1.0
 * @Date 2016年5月20日下午5:25:24
 */
// 表示是一个配置信息提供类
@Configuration
public class Beans {

    // 定义一个 Bean
    @Bean(name = "car")
    public Car buildCar() {
        Car car = new Car();
        car.setBrand("红色CA72");
        car.setMaxSpeed(200);
        return car;
    }
}

