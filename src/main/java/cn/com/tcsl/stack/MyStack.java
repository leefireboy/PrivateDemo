/*
 * Copyright (c) 2016 libing. All rights reserved.
 */
package cn.com.tcsl.stack;

import java.util.LinkedList;

/**
 * <P>
 * Description:使用 LinkedList 实现 Stack
 * </p>
 * @author libing
 * @version 1.0
 * @Date 2016年5月22日下午6:38:47
 */
@SuppressWarnings(value = {"unchecked", "rawtypes"})
public class MyStack {

    private final LinkedList storage = new LinkedList();

    public void push(Object o) {
        storage.addFirst(o);
    }

    public Object peek() {
        return storage.getFirst();
    }

    public Object pop() {
        return storage.removeFirst();
    }

    public boolean empty() {
        return storage.isEmpty();
    }

    @Override
    public String toString() {
        return storage.toString();
    }

    public static void main(String[] args) {

    }

}
