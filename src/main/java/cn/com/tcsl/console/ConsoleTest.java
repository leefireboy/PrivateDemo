/*
 * Copyright (c) 2016 libing. All rights reserved.
 */
package cn.com.tcsl.console;

import java.io.Console;

/**
 * <P>
 * Description:
 * </p>
 * @author libing
 * @version 1.0
 * @Date 2016年6月1日下午10:59:25
 */
@SuppressWarnings("unused")
public class ConsoleTest {

    /**
     * <P>
     * Description:can not to run
     * </p>
     * @author libing
     * @version 1.0
     * @Date 2016年6月1日下午11:25:36
     * @param args
     */
    public static void main(String[] args) {
        Console cons = System.console();
        String username = cons.readLine("User name: ");
        char[] password = cons.readPassword("Password: ");
    }

}
