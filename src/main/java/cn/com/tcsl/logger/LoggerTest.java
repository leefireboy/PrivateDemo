/*
 * Copyright (c) 2016 libing. All rights reserved.
 */
package cn.com.tcsl.logger;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <P>
 * Description:
 * </p>
 * @author libing
 * @version 1.0
 * @Date 2016年6月13日下午5:00:11
 */
public class LoggerTest {

    public static void main(String[] args) {
        Logger.getGlobal().setLevel(Level.ALL);
        Logger.getGlobal().info("what is this?");
    }

}
