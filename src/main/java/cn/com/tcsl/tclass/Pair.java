/*
 * Copyright (c) 2016 libing. All rights reserved.
 */
package cn.com.tcsl.tclass;

/**
 * <P>
 * Description:泛型类定义
 * </p>
 * @author libing
 * @version 1.0
 * @Date 2016年7月4日上午10:56:24
 */
public class Pair<T> {

    private T first;
    private T second;

    public Pair() {
        this.first = null;
        this.second = null;
    }

    public Pair(T first, T second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public T getSecond() {
        return second;
    }

    public void setSecond(T second) {
        this.second = second;
    }

}
