/*
 * Copyright (c) 2016 libing. All rights reserved.
 */
package cn.com.tcsl.synchronize;

/**
 * <P>
 * Description:
 * </p>
 * @author libing
 * @version 1.0
 * @param <E>
 * @Date 2016年5月31日上午10:22:28
 */
public interface SetObserver<E> {

    void added(ObservableSet<E> set, E element);

}
