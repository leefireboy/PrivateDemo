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
 * @Date 2016年5月30日下午5:38:04
 */
public class ShutDown {

    public static void main(String[] args) throws InterruptedException {
        Runner r = new Runner();
        Thread thread = new Thread(r, "shutDownThread1");
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();

        Runner r2 = new Runner();
        Thread thread2 = new Thread(r2, "shutDownThread2");
        thread2.start();
        Thread.sleep(1000);
        r2.cancel();
    }

    static class Runner implements Runnable {

        private int count;
        private volatile boolean on = true;

        @Override
        public void run() {
            while (on && !Thread.currentThread().isInterrupted()) {
                count++;
            }
            System.out.println("count:" + count);
        }

        public void cancel() {
            on = false;
        }

    }

}
