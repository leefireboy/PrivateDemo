/*
 * Copyright (c) 2016 libing. All rights reserved.
 */
package cn.com.tcsl.console;

import java.util.Scanner;

/**
 * <P>
 * Description:
 * </p>
 * @author libing
 * @version 1.0
 * @Date 2016年6月1日下午10:47:10
 */
public class InputTest {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // get first input
        System.out.println("what is your name?");
        String name = in.nextLine();

        // get second input
        System.out.println("How old are you?");
        int age = in.nextInt();

        // display output on console
        System.out.println("Hello, " + name + ". Next year, you'll be " + (age + 1));
        in.close();
    }

}
