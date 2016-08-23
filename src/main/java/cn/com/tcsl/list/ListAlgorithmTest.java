/*
 * Copyright (c) 2016 libing TV. All rights reserved.
 */
package cn.com.tcsl.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <P>
 * Description:测试 shuffle, sort, search, reverse, copy, fill
 * </p>
 * @author libing
 * @version 1.0
 * @Date 2016年5月21日上午11:22:51
 */
public class ListAlgorithmTest {

    public static void main(String[] args) {
        List<Integer> l1 = new ArrayList<Integer>(5);
        for (int i = 0; i < 5; i++) {
            l1.add(i);
        }
        List<Integer> l2 = new ArrayList<Integer>(5);
        for (int j = 0; j < 5; j++) {
            l2.add(0);
        }
        System.out.println("l1:" + l1);
        Collections.shuffle(l1);
        System.out.println("shuffle:" + l1);
        Collections.sort(l1);
        System.out.println("sort:" + l1);
        System.out.println("search:" + Collections.binarySearch(l1, 1));
        Collections.reverse(l1);
        System.out.println("reverse:" + l1);
        Collections.copy(l2, l1);
        System.out.println("l2:" + l2);
        Collections.fill(l2, 1);
        System.out.println("fill-l2:" + l2);
    }

}
