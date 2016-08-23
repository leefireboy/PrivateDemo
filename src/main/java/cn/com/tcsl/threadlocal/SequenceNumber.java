/*
 * Copyright (c) 2016 libing. All rights reserved.
 */
package cn.com.tcsl.threadlocal;

/**
 * <P>
 * Description:ThreadLocal 实例
 * </p>
 * @author libing
 * @version 1.0
 * @Date 2016年5月27日下午5:05:18
 */
public class SequenceNumber {

    private static ThreadLocal<Integer> seqNum = new ThreadLocal<Integer>() {

        @Override
        public Integer initialValue() {
            return 1;
        }

    };

    public static int getNextNum() {
        seqNum.set(seqNum.get() + 1);
        return seqNum.get();
    }

    public static void main(String[] args) {
        SequenceNumber sn = new SequenceNumber();

        TestClient t1 = new TestClient(sn);
        TestClient t2 = new TestClient(sn);
        TestClient t3 = new TestClient(sn);
        t1.start();
        t2.start();
        t3.start();
    }

    @SuppressWarnings("static-access")
    private static class TestClient extends Thread {

        private SequenceNumber sn;

        public TestClient(SequenceNumber sn) {
            this.sn = sn;
        }

        @Override
        public void run() {
            for (int i = 0; i < 3; i++) {
                System.out.println("thread[" + Thread.currentThread().getName() + "]sn[" + sn.getNextNum() + "]");
            }
        }

    }

}
