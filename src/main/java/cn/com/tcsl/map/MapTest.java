/*
 * Copyright (c) 2016 libing. All rights reserved.
 */
package cn.com.tcsl.map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

/**
 * <P>
 * Description:Map test
 * </p>
 * @author libing
 * @version 1.0
 * @Date 2016年5月22日下午9:03:59
 */
public class MapTest {

    // 统计随机数
    // 随机数生成器，生成100个数字，(0~4)，统计五中数字出现的次数
    public static void main(String[] args) {
        Random random = new Random();
        Map<Integer, Integer> integerMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < 100; i++) {
            Integer r = random.nextInt(5);
            Integer count = integerMap.get(r);
            integerMap.put(r, count == null ? 1 : (count + 1));
        }
        System.out.println(integerMap);
        Iterator<Entry<Integer, Integer>> iter1 = integerMap.entrySet().iterator();
        Iterator<Integer> iter2 = integerMap.keySet().iterator();
        while (iter1.hasNext()) {
            Entry<Integer, Integer> object = iter1.next();
            Integer key = object.getKey();
            Integer value = object.getValue();
            System.out.println("entrySet" + key + "-" + value);
        }
        while (iter2.hasNext()) {
            Integer key = iter2.next();
            Integer value = integerMap.get(key);
            System.out.println("keySet" + key + "-" + value);
        }
        integerMap.remove(0);
        System.out.println(integerMap);
    }

}

