package com.zc.mybackcrud.controller;

import com.zc.mybackcrud.mapper.EmpMapper;
import com.zc.mybackcrud.pojo.Emp;
import com.zc.mybackcrud.pojo.Result;
import com.zc.mybackcrud.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private EmpService empService;

    @PostMapping("/login")
    public Result login(@RequestBody Emp emp){
        Emp loginInfo  = empService.loginInfo(emp);
        if (loginInfo != null){
            return Result.success(loginInfo);
        }
        return Result.error("用户名或密码错误");
    }
}
