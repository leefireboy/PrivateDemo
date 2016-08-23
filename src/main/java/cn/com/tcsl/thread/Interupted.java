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
 * @Date 2016年5月30日下午5:18:56
 */
public class Interupted {

    public static void main(String[] args) throws InterruptedException {
        Thread sleepThread = new Thread(new SleepRunner(), "sleepThread");
        Thread busyThread = new Thread(new BusyRunner(), "busyThread");
        sleepThread.setDaemon(true);
        busyThread.setDaemon(true);
        sleepThread.start();
        busyThread.start();
        Thread.sleep(3000);
        sleepThread.interrupt();
        busyThread.interrupt();
        System.out.println(sleepThread.isInterrupted());
        System.out.println(busyThread.isInterrupted());
        Thread.sleep(2000);
    }

    static class SleepRunner implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(10000);
                } catch (Exception e) {
                }
            }
        }

    }

    static class BusyRunner implements Runnable {

        @Override
        public void run() {
            while (true) {
            }
        }

    }

}
