/*
 * Copyright (c) 2016 libing. All rights reserved.
 */
package cn.com.tcsl.thread;

/**
 * <P>
 * Description:
 * </p>
 * @author libing
 * @version 1.0
 * @Date 2016年5月30日下午6:01:32
 */
public class ThreadStateTest {

    public static void main(String[] args) {
        newState();
        runnableState();
    }

    private static void runnableState() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < Integer.MAX_VALUE; i++) {
                    System.out.println(i);
                }
            }
        };
        thread.start();
    }

    private static void newState() {
        Thread thread = new Thread("new");
        System.out.println(thread.getState());
    }

}
