package com.zc.mybackcrud.exception;

import com.zc.mybackcrud.pojo.Result;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public Result handleException(Exception e){
        return Result.error("出错了，请联系管理员~");
    }

    // 截取重复的字段名和值的通用异常处理方法
    @ExceptionHandler
    public Result handleDupException(DuplicateKeyException e){
        String msg = e.getMessage();
        int i = msg.indexOf("Duplicate entry");
        String substr = msg.substring(i);
        String[] split = substr.split(" ");
        return Result.error(split[2] + "已存在");
    }

}
