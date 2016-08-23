/*
 * Copyright (c) 2016 libing. All rights reserved.
 */
package cn.com.tcsl.comparable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <P>
 * Description:
 * </p>
 * @author libing
 * @version 1.0
 * @Date 2016年5月22日上午9:31:14
 */
public class NameTest {

    public static void main(String[] args) {
        List<Name> list = new ArrayList<Name>();
        list.add(new Name("harry", "porter"));
        list.add(new Name("tom", "hanks"));
        list.add(new Name("peter", "zhang"));
        list.add(new Name("harry", "zhang"));
        Collections.sort(list);
        System.out.println(list);
    }

}

