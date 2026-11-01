package com.zc.mybackcrud.controller;

import com.zc.mybackcrud.pojo.PageResult;
import com.zc.mybackcrud.pojo.Result;
import com.zc.mybackcrud.pojo.Student;
import com.zc.mybackcrud.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StuController {

    @Autowired
    private StuService stuService;

    @GetMapping("/students")
    public Result findAll(String name,Integer degree,Integer clazzId,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize)
    {
        PageResult list = stuService.getStus(name, degree, clazzId, page, pageSize);
        return Result.success(list);
    }


    @PostMapping("/students")
    public Result addStu(@RequestBody Student student){
        stuService.save(student);
        return Result.success();
    }

    @PutMapping("/students")
    public Result updateStu(@RequestBody Student student){
        stuService.update(student);
        return Result.success();
    }

    @DeleteMapping("/students/{id}")
    public Result deleteStu(@PathVariable Integer id){
        stuService.delete(id);
        return Result.success();
    }

    @GetMapping("/students/{id}")
    public Result findStu(@PathVariable Integer id){
        Student student = stuService.getStu(id);
        return Result.success(student);
    }

    @PutMapping("/students/violation/{id}/{score}")
    public Result addViolation(@PathVariable Integer id, @PathVariable Integer score){
        stuService.addViolation(id, score);
        return Result.success();
    }


}
