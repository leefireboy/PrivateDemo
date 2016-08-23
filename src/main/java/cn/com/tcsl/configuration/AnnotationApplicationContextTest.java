/*
 * Copyright (c) 2016 libing. All rights reserved.
 */
package cn.com.tcsl.configuration;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import cn.com.tcsl.reflectdemo.Car;

/**
 * <P>
 * Description:使用 AnnotationApplicationContext 启动 Spring 容器
 * </p>
 * @author libing
 * @version 1.0
 * @Date 2016年5月20日下午5:32:16
 */
public class AnnotationApplicationContextTest {

    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(Beans.class);
        Car car = ac.getBean("car", Car.class);
        System.out.println(car.toString());
        AbstractApplicationContext aac = (AbstractApplicationContext) ac;
        aac.close();
    }

}
