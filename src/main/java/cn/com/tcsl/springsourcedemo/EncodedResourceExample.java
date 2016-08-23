/*
 * Copyright (c) 2016 libing. All rights reserved.
 */
package cn.com.tcsl.springsourcedemo;

import java.io.IOException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.util.FileCopyUtils;

/**
 * <P>
 * Description:资源加载时默认采用系统编码读取资源内容，如果资源文件采用特殊的编码格式，那么可以通过
 *      EncodedResource 对资源进行编码，以保证资源内容操作的正确性
 * </p>
 * @author libing
 * @version 1.0
 * @Date 2016年5月20日下午1:57:25
 */
public class EncodedResourceExample {

    public static void main(String[] args) throws IOException {
        Resource res = new ClassPathResource("123.txt");
        EncodedResource encRes = new EncodedResource(res, "UTF-8");
        String content = FileCopyUtils.copyToString(encRes.getReader());
        System.out.println(content);
    }

}
