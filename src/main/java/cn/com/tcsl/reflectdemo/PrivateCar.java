/*
 * Copyright (c) 2016 libing. All rights reserved.
 */
package cn.com.tcsl.reflectdemo;

/**
 * <P>
 * Description:
 * </p>
 * @author libing
 * @version 1.0
 * @Date 2016年5月20日下午4:20:10
 */
public class PrivateCar {
    // private 成员变量：使用传统的类实例调用方式，只能在本类中访问
    private String color;
    // protected 方法：使用传统的类实例调用方式，只能在子类和本包中访问
    protected void drive() {
        System.out.println("drive private car! the color is:" + color);
    }
}

