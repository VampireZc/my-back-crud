package com.zc.mybackcrud.controller;

import com.zc.mybackcrud.pojo.Dept;
import com.zc.mybackcrud.pojo.Result;
import com.zc.mybackcrud.service.DeptService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    // 限制只能get请求
    @GetMapping("/depts")
    public Result list(@RequestParam(required = false) String name){
        List<Dept> list;
        System.out.println("name：" + name);
        if (name != null && !name.isEmpty()){ // 根据名字查询
            list = deptService.findByName(name);
        }else {
            list = deptService.findAll();
        }
        return Result.success(list);
    }

    // 删除部门
    @DeleteMapping("/depts")
    public Result delete(Integer id){
        Integer row = deptService.deleteById(id);
        System.out.println("删除部门，row：" + row);
        if (row == 1){
            return Result.success();
        }else {
            return Result.error("删除失败, 请检查id是否正确");
        }
    }

    // 新增部门
    @PostMapping("/depts")
    public Result add(@RequestBody Dept dept){
         deptService.save(dept);
        return Result.success();
    }

    //修改部门
    @PutMapping("/depts")
    public Result update(@RequestBody Dept dept){
        deptService.update(dept);
        return Result.success();
    }

    // 根据ID查询
    @GetMapping("/depts/{id}")
    // 获取路径上的参数
    public Result getById(@PathVariable Integer id){
        Dept dept = deptService.getById(id);
        return Result.success(dept.getName());
    }
}
