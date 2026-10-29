package com.zc.mybackcrud.controller;

import com.zc.mybackcrud.pojo.Dept;
import com.zc.mybackcrud.pojo.PageResult;
import com.zc.mybackcrud.pojo.Result;
import com.zc.mybackcrud.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class DeptController {

    //增加日志
    // private static final Logger log = LoggerFactory.getLogger(DeptController.class);

    @Autowired
    private DeptService deptService;

    // 限制只能get请求
    @GetMapping("/depts")
    public Result list(@RequestParam(required = false) String name, Integer page, Integer pageSize){

        if (name != null && !name.isEmpty()){ // 根据名字查询
            List<Dept> list = deptService.findByName(name);
            return   Result.success(list);
        }else {
            PageResult pageResult = deptService.findAll(page, pageSize);
            return Result.success(pageResult);
        }
    }

    // 删除部门 /depts?id=19
    @DeleteMapping("/depts")
    // 其实只要传递的参数名和方法参数名一致，@RequestParam可以省略
    public Result delete(@RequestParam(value = "id", required = true) Integer id){
        Integer row = deptService.deleteById(id);
        log.info("删除部门: {}", id);
        if (row == 1){
            return Result.success();
        }else {
            log.error("删除失败, 请检查id是否正确");
            return Result.error("删除失败, 请检查id是否正确");
        }
    }

    // 新增部门
    @PostMapping("/depts")
    public Result add(@RequestBody Dept dept){
        log.info("新增部门: {}", dept);
         deptService.save(dept);
        return Result.success();
    }

    //修改部门
    @PutMapping("/depts")
    public Result update(@RequestBody Dept dept){
        log.info("修改部门：{}", dept);
        deptService.update(dept);
        return Result.success();
    }

    // 根据ID查询 /depts/20
    @GetMapping("/depts/{id}")
    // 获取路径上的参数
    public Result getById(@PathVariable Integer id){
        log.info("查询部门：{}", id);
        Dept dept = deptService.getById(id);
        return Result.success(dept.getName());
    }
}
