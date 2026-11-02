package com.zc.mybackcrud.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.METHOD) //在什么地方生效
@Retention(RetentionPolicy.RUNTIME) // 运行时执行
// 自定义注解
public @interface LogOperation {
}
