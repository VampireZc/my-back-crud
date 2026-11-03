package com.zc.mybackcrud;

import com.aliyun.oss.OSS;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;

@SpringBootTest
public class TokenTest { // 测试一下自动装配的类

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private OSS ossClient; // 可以直接使用自己手动注入的

    @Test
    public void testToken() {
//        System.out.println(messageSource);
//        System.out.println(ossClient);
    }
}
