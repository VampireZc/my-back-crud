package com.zc.mybackcrud.controller;

import com.zc.mybackcrud.pojo.Clazz;
import com.zc.mybackcrud.pojo.PageResult;
import com.zc.mybackcrud.pojo.Result;
import com.zc.mybackcrud.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClazzController {
    @Autowired
    private ClazzService clazzService;

    @GetMapping("/clazzs")
    public Result getClazz(
            String name,
            @DateTimeFormat(pattern = "yyyy-MM-dd") String begin,
            @DateTimeFormat(pattern = "yyyy-MM-dd") String end,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize
    ) {
        PageResult pageResult = clazzService.findAll(name, begin, end, page, pageSize);
        return Result.success(pageResult);
    }

    @DeleteMapping("/clazzs/{id}")
    public Result deleteClazz(@PathVariable Integer id){
        clazzService.deleteClazz(id);
        return Result.success();
    }

    @PostMapping("/clazzs")
    public Result addClazz(@RequestBody Clazz clazz){
        clazzService.addClazz(clazz);
        return Result.success();
    }

    @GetMapping("/clazzs/{id}")
    public Result findById(@PathVariable Integer id){
        Clazz clazz = clazzService.findById(id);
        return Result.success(clazz);
    }

    @PutMapping("/clazzs")
    public Result updateClazz(@RequestBody Clazz clazz){
        clazzService.updateClazz(clazz);
        return Result.success();
    }

}
