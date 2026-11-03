package com.zc.mybackcrud.controller;

import com.zc.mybackcrud.mapper.OperateLogMapper;
import com.zc.mybackcrud.pojo.JobOption;
import com.zc.mybackcrud.pojo.OperateLog;
import com.zc.mybackcrud.pojo.Result;
import com.zc.mybackcrud.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class ReportController {

    @Autowired
    private ReportService reportService;

    @Autowired
    private OperateLogMapper operateLogMapper;

    @GetMapping("/report/empJob")
    public Result getEmpJobReport(){
       JobOption jobOption = reportService.getJobReport();
       return Result.success(jobOption);
    }

    @GetMapping("/report/empSex")
    public Result getEmpSexReport(){
        List<Map<String, Object>> list = reportService.getSexReport();
        return Result.success(list);
    }

    @GetMapping("/report/clazzNum")
    public Result getClazzNumReport(){
        JobOption list = reportService.getClazzNumReport();
        return Result.success(list);
    }

    @GetMapping("/report/gender")
    public Result getGenderReport(){
        List<Map<String, Object>> list = reportService.getGenderReport();
        return Result.success(list);
    }

    @GetMapping("/report/log")
    public Result getLogReport(){
        List<OperateLog> list = operateLogMapper.getLogReport();
        return Result.success(list);
    }
}
