/*
 * Copyright (c) 2016 libing. All rights reserved.
 */
package cn.com.tcsl.erase;

import java.util.ArrayList;

/**
 * <P>
 * Description:
 * </p>
 * @author libing
 * @version 1.0
 * @Date 2016年5月24日下午11:32:35
 */
@SuppressWarnings("rawtypes")
public class EraseTest {

    public static void main(String[] args) {
        Class c1 = new ArrayList<Integer>().getClass();
        Class c2 = new ArrayList<String>().getClass();
        System.out.println(c1 == c2);
        System.out.println(c1 + "\n" + c2);
    }

}
