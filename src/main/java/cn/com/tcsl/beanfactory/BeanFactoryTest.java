/*
 * Copyright (c) 2016 libing. All rights reserved.
 */
package cn.com.tcsl.beanfactory;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import cn.com.tcsl.reflectdemo.Car;

/**
 * <P>
 * Description:BeanFactory Test
 * </p>
 * <P>
 * Notice:第一次访问某个 Bean 时才会初始化。初始化 BeanFactory 时，必须为其提供一种日志框架 (例：Log4J)，
 *      在类路径下提供其配置文件，这样启动 Spring 容器不会报错
 * </p>
 * @author libing
 * @version 1.0
 * @Date 2016年5月20日下午3:43:38
 */
@SuppressWarnings("deprecation")
public class BeanFactoryTest {

    public static void main(String[] args) {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource res = resolver.getResource("classpath:spring.xml");
        BeanFactory bf = new XmlBeanFactory(res);
        System.out.println("init BeanFactory------" + bf.toString());

        Car car = bf.getBean("car1", Car.class);
        System.out.println("car bean is ready for use!------" + car.toString());
    }

}
