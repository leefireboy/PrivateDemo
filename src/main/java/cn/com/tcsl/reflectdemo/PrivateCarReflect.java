/*
 * Copyright (c) 2016 libing. All rights reserved.
 */
package cn.com.tcsl.reflectdemo;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * <P>
 * Description:
 * </p>
 * @author libing
 * @version 1.0
 * @Date 2016年5月20日下午4:18:35
 */
@SuppressWarnings(value = {"unchecked", "rawtypes"})
public class PrivateCarReflect {

    public static void main(String[] args) throws Throwable {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Class clazz = loader.loadClass("cn.com.tcsl.reflectdemo.PrivateCar");
        PrivateCar pcar = (PrivateCar) clazz.newInstance();

        Field colorFld = clazz.getDeclaredField("color");
        // 取消 Java 语言访问检查以访问 private 变量
        colorFld.setAccessible(true);
        colorFld.set(pcar, "红色");

        Method driveMtd = clazz.getDeclaredMethod("drive", (Class[]) null);

        // 取消 Java 语言访问检查以访问 protected 方法
        driveMtd.setAccessible(true);
        driveMtd.invoke(pcar, (Object[]) null);
    }

}
