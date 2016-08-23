/*
 * Copyright (c) 2016 libing. All rights reserved.
 */
package cn.com.tcsl.thread;

/**
 * <P>
 * Description:通过实现 Runnable 接口来开启线程
 * </p>
 * @author libing
 * @version 1.0
 * @Date 2016年5月27日上午12:14:43
 */
public class MyRun implements Runnable {

    private int count = 0;

    public int getCount() {
        return count;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            count = count + i;
            System.out.println(Thread.currentThread().getName() + "--" + count);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[3];
        MyRun[] runs = new MyRun[3];
        for (int i = 0; i < 3; i++) {
            MyRun myRun = new MyRun();
            runs[i] = myRun;
            Thread thread = new Thread(myRun);
            threads[i] = thread;
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        for (MyRun myRun : runs) {
            System.out.println(myRun.getCount());
        }
    }

}
