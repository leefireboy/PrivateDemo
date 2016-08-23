/*
 * Copyright (c) 2016 libing. All rights reserved.
 */
package cn.com.tcsl.springsourcedemo;

import java.io.IOException;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

/**
 * <P>
 * Description:
 * </p>
 * @author libing
 * @version 1.0
 * @Date 2016年5月20日下午2:56:17
 */
public class PatternResolverTest {

    public static void main(String[] args) throws IOException {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        // 加载所有类包 cn.com.tcsl (及子包)下的以xml为后缀的资源
        Resource[] resources = resolver.getResources("classpath*:cn/com/tcsl/**/*.xml");
        for (Resource resource : resources) {
            System.out.println(resource.getDescription());
        }
    }

}
