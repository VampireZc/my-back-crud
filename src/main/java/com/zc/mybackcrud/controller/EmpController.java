package com.zc.mybackcrud.controller;

import com.zc.mybackcrud.pojo.Emp;
import com.zc.mybackcrud.pojo.PageResult;
import com.zc.mybackcrud.pojo.Result;
import com.zc.mybackcrud.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
public class EmpController {
    @Autowired
    private EmpService empService;

    // emps?name=张&gender=1&begin=2007-09-01&end=2022-09-01&page=1&pageSize=10
    @GetMapping("/emps")
    public Result pageQuery(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize,
            String name, Integer gender,
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
            @DateTimeFormat(pattern= "yyyy-MM-dd") LocalDate end
            ){
        PageResult res = empService.findAll(page, pageSize, name, gender, begin, end);
        return Result.success(res);
    }

    // ids=1,2,3
    @DeleteMapping("/emps")
    // @RequestParam("ids") List<Integer> ids 如果设置这样的话就不需要手动转换
    public Result delete(String ids){
        empService.deleteById(ids);
        return Result.success();
    }

    // 添加员工
    @PostMapping("/emps")
    public Result add(@RequestBody Emp emp){
        empService.insertEmp(emp);
        return Result.success();
    }

    // 修改员工
    @PutMapping("/emps")
    public Result update(@RequestBody Emp emp){
        empService.updateEmp(emp);
        return Result.success();
    }

    // 查询员工
    @GetMapping("/emps/{id}")
    public Result findById(@PathVariable Integer id){
        Emp emp = empService.findById(id);
        return Result.success(emp);
    }

    // 一次查员工信息与员工经历
    @GetMapping("/emps/exp/{id}")
    public Result findByIdWithExp(@PathVariable Integer id){
        Emp emp = empService.findByExp(id);
        return Result.success(emp);
    }

}
