/*
 * Copyright (c) 2016 libing. All rights reserved.
 */
package cn.com.tcsl.createanddistoryobject;

import cn.com.tcsl.enumclass.Size;

/**
 * <P>
 * Description:实现单例的两种方式
 * </p>
 * @author libing
 * @version 1.0
 * @Date 2016年5月31日下午2:30:50
 */
public class ElvisSingleton {

//    // the method one to singleton
//    public static final ElvisSingleton INSTANCE = new ElvisSingleton();
//
//    private ElvisSingleton() {
//
//    }

    // the method two to singleton
    private static final ElvisSingleton INSTANCE = new ElvisSingleton();

    private ElvisSingleton() {

    }

    public static ElvisSingleton getInstance() {
        return INSTANCE;
    }

    private Object readResolve() {
        return INSTANCE;
    }

    public static void main(String[] args) {
        int i = 1_0000;
        System.out.println(i);
        System.out.println(2.1-1.2);
        System.out.println(Math.round(4.5));
        System.out.println(Size.LARGE);
        System.out.println("Java\u2122");
    }

}
