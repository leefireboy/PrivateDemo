/*
 * Copyright (c) 2016 libing. All rights reserved.
 */
package cn.com.tcsl.thread;

/**
 * <P>
 * Description:继承 Thread 类实现线程
 * </p>
 * @author libing
 * @version 1.0
 * @Date 2016年5月26日下午11:53:13
 */
public class MyThread extends Thread {

    private int count = 0;

    public int getCount() {
        return count;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            count = count + i;
            System.out.println(Thread.currentThread().getName() + "-" + count);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyThread[] threads = new MyThread[3];
        for (int i = 0; i < 3; i++) {
            MyThread myThread = new MyThread();
            threads[i] = myThread;
            myThread.start();
        }
        for (MyThread myThread : threads) {
            myThread.join();
        }
        for (MyThread myThread : threads) {
            System.out.println(myThread.getCount());
        }
    }

}
