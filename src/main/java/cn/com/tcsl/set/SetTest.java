/*
 * Copyright (c) 2016 libing. All rights reserved.
 */
package cn.com.tcsl.set;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.com.tcsl.comparable.Name;

/**
 * <P>
 * Description:Set test
 *      Set 中最常见的操作是测试归属性，可以很容易的询问某个对象是否在
 *      某个 Set 中，查找是 Set 最重要的操作，通常选用 HashMap
 * </p>
 * @author libing
 * @version 1.0
 * @Date 2016年5月22日下午11:57:55
 */
public class SetTest {

    public static void main(String[] args) {
        Set<Object> s = new HashSet<Object>();
        s.add("hello");
        s.add("world");
        s.add(new Name("harry", "porter"));
        s.add(100);
        s.add(new Name("harry", "porter"));
        System.out.println(s);

        Map<String, List<Object>> map = new HashMap<String, List<Object>>();
        map.put("emp", new ArrayList<Object>());
        map.put("boss", new ArrayList<Object>());
        insertOrUpdateBatchByPlan(map);
    }

    public boolean insertOrUpdateBatch(List<Object> list, String tableName) {
        return false;
    }

    /**
     * <P>
     * Description:map.keySet() test method
     * </p>
     * @author libing
     * @version 1.0
     * @Date 2016年6月14日上午10:12:57
     * @param map
     * @return
     */
    public static boolean insertOrUpdateBatchByPlan(Map<String, List<Object>> map) {
        Set<String> tableNames =  map.keySet();
        for (String tableName : tableNames) {
            System.out.println(tableName);
        }
        return false;
    }

}
