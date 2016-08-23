/*
 * Copyright (c) 2016 libing. All rights reserved.
 */
package cn.com.tcsl.test;

import java.util.Date;

/**
 * <P>
 * Description:TODO
 * </p>
 * @author libing
 * @version 1.0
 * @Date 2016年6月21日上午9:36:13
 */
public class Test {

    public static void main(String[] args) {
        Object[] objs = new Object[3];
        objs[0] = 2;
        objs[1] = new Date();
        objs[2] = "1.2";
//        for (int i = 0; i < objs.length; i++) {
//            objs[i] = objs[i].toString();
//        }
        System.out.println("" + objs[0] + objs[1] + objs[2]);
        for (Object object : objs) {
            System.out.println(object.getClass());
            if (object.getClass().isAssignableFrom(Date.class)) {
                Date date = (Date) object;
                System.out.println(date.getTime());
            }
        }
    }

}
