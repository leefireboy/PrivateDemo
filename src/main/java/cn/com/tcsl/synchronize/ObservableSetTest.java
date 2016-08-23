/*
 * Copyright (c) 2016 libing. All rights reserved.
 */
package cn.com.tcsl.synchronize;

import java.util.HashSet;

/**
 * <P>
 * Description:
 * </p>
 * @author libing
 * @version 1.0
 * @Date 2016年5月31日上午10:37:41
 */
public class ObservableSetTest {

    public static void main(String[] args) {
        ObservableSet<Integer> set = new ObservableSet<Integer>(new HashSet<Integer>());

        set.addObserver(new SetObserver<Integer>() {

            @Override
            public void added(ObservableSet<Integer> set, Integer element) {
                System.out.println(element);
            }

        });

        for (int i = 0; i < 100; i++) {
            set.add(i);
        }
    }

}
