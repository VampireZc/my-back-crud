package com.zc.mybackcrud.pojo;

import lombok.Data;

@Data
public class Result {
    private Integer code;
    private String msg;
    private Object data;

    // 成功无数据返回
    public static Result success(){
        Result res = new Result();
        res.code = 1;
        res.msg = "success";
        return res;
    }

    // 成功有数据返回
    public static Result success(Object obj){
        Result res = success();
        res.data = obj;
        return res;
    }

    // 失败的结果
    public static Result error(String msg){
        Result res = new Result();
        res.code = 0;
        res.msg = msg;
        return res;
    }
}
